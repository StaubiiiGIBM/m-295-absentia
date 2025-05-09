package ch.schaub.leon.absentia.department;

import ch.schaub.leon.absentia.base.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public Department getDepartment(int id) {
        return departmentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Department with id " + id + " not found")
        );
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Department department, int id) {
        Department originalDepartment = departmentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Department with id " + id + " not found")
        );

        originalDepartment.setDepartment_code(department.getDepartment_code());
        originalDepartment.setDescription(department.getDescription());

        return departmentRepository.save(originalDepartment);
    }
    public MessageResponse deleteDepartment(int id) {
        departmentRepository.deleteById(id);
        return new MessageResponse("Department " + id + " deleted");
    }

}
