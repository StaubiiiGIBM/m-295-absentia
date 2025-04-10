package ch.schaub.leon.absentia.department;

import ch.schaub.leon.absentia.AbsentiaApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = {AbsentiaApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc api;

    @Autowired
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void beforeEach() {
        departmentRepository.deleteAll();
        departmentRepository.save(new Department("DEV", "The Dev Department"));
        departmentRepository.save(new Department("GL", "The Company lead"));
    }

    @Test
    @Order(1)
    void testGetDepartment() throws Exception {
        String accessToken = obtainAccessToken();

        int firstId = departmentRepository.findAll().getFirst().getId();

        api.perform(get("/department/" + firstId).header("Authorization", "Bearer " + accessToken)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("DEV")));
    }

    @Test
    @Order(2)
    void testGetAllDepartments() throws Exception {
        String accessToken = obtainAccessToken();

        api.perform(get("/department").header("Authorization", "Bearer " + accessToken)
                .with(csrf()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("DEV")));
    }

    @Test
    @Order(3)
    void testAddDepartment() throws Exception {
        String accessToken = obtainAccessToken();

        Department department = new Department("HR", "Human Resources");
        String body = new ObjectMapper().writeValueAsString(department);

        // Save the amount of Departments before adding a new one
        int departmentAmount = departmentRepository.findAll().size();

        api.perform(post("/department/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
                .header("Authorization", "Bearer " + accessToken)
                .with(csrf()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Human Resources")));

        // Check if the Amount increased
        Assertions.assertNotEquals(departmentAmount, departmentRepository.findAll().size());
    }

    @Test
    @Order(4)
    void editDepartment() throws Exception {
        String accessToken = obtainAccessToken();

        Department newDepartment = new Department("DEV", "The Development Department");
        String body = new ObjectMapper().writeValueAsString(newDepartment);
        int firstId = departmentRepository.findAll().getFirst().getId();

        api.perform(put("/department/edit/" + firstId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
                .header("Authorization", "Bearer " + accessToken)
                .with(csrf()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("The Development Department")));
    }

    @Test
    @Order(5)
    void deleteDepartment() throws Exception {
        String accessToken = obtainAccessToken();
        Assertions.assertEquals(2, departmentRepository.findAll().size());
        int firstId = departmentRepository.findAll().getFirst().getId();

        api.perform(delete("/department/delete/" + firstId)
                .header("Authorization", "Bearer " + accessToken)
                .with(csrf()));

        Assertions.assertEquals(1, departmentRepository.findAll().size());
    }

    private String obtainAccessToken() {

        RestTemplate rest = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "client_id=absentia&" +
                "grant_type=password&" +
                "scope=openid profile roles offline_access&" +
                "username=admin&" +
                "password=admin";

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> resp = rest.postForEntity("http://localhost:8080/realms/Absentia/protocol/openid-connect/token", entity, String.class);

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resp.getBody()).get("access_token").toString();
    }
}