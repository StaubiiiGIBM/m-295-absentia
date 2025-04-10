package ch.schaub.leon.absentia;

import ch.schaub.leon.absentia.department.Department;
import ch.schaub.leon.absentia.department.DepartmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = AbsentiaApplication.class)
public class DBTests {

    @Autowired
    DepartmentRepository departmentRepository;

    @BeforeEach
    public void beforeAll() {
        departmentRepository.deleteAll();
    }

    //Test the save method
    @Test
    public void create() {
        Department department = new Department("123", "1234");
        departmentRepository.save(department);
        Assertions.assertEquals(1, departmentRepository.findAll().size());
    }

    //Test the findAll method
    @Test
    public void findAll() {
        departmentRepository.save(new Department("123", "1234"));
        departmentRepository.save(new Department("456", "4567"));
        List<Department> departments = departmentRepository.findAll();
        Assertions.assertEquals(2, departments.size());
    }

    //Test the findById method
    @Test
    public void findById() {
        Department department = new Department("123", "1234");
        departmentRepository.save(department);
        Assertions.assertNotNull(departmentRepository.findById(department.getId()).orElse(null));
    }

    //Test the delete Method
    @Test
    public void delete() {
        Department department = new Department("123", "1234");
        departmentRepository.save(department);
        departmentRepository.delete(department);
        Assertions.assertEquals(0, departmentRepository.findAll().size());
    }

    //JpaRepositories don't have an update function, it is defined in the service class.
    //Hence, it is not tested here.

}
