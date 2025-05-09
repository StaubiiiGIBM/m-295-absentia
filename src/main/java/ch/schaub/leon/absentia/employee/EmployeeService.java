package ch.schaub.leon.absentia.employee;

import ch.schaub.leon.absentia.base.MessageResponse;
import ch.schaub.leon.absentia.department.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee getEmployee(int id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Employee with id " + id + " not found")
        );
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }


    public Employee updateEmployee(Employee employee, int id) {
        Employee originalEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Employee with id " + id + " not found")
        );

        originalEmployee.setFirst_name(employee.getFirst_name());
        originalEmployee.setLast_name(employee.getLast_name());
        originalEmployee.setDepartment(employee.getDepartment());

        return employeeRepository.save(originalEmployee);
    }

    public MessageResponse deleteEmployee(int id) {
        employeeRepository.deleteById(id);
        return new MessageResponse("Employee " + id + " deleted");
    }
}
