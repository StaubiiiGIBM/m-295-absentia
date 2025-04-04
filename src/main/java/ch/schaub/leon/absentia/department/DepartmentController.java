package ch.schaub.leon.absentia.department;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Validated
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/department/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable int id) {
        Department department = departmentService.getDepartment(id);
        return ResponseEntity.ok(department);
    }

    @GetMapping("/department")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @PostMapping("/department/add")
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        Department newDepartment = departmentService.addDepartment(department);
        return ResponseEntity.ok(newDepartment);
    }

    @PutMapping("/department/edit/{id}")
    public ResponseEntity<Department> editDepartment(
            @RequestBody Department department, @PathVariable int id
    ) {
        return ResponseEntity.ok(departmentService.updateDepartment(department, id));
    }

    @DeleteMapping("/department/delete/{id}")
    public String deleteDepartment(@PathVariable int id) {
        return departmentService.deleteDepartment(id);
    }
}
