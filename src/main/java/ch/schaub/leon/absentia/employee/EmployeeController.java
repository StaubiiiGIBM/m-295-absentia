package ch.schaub.leon.absentia.employee;

import ch.schaub.leon.absentia.base.MessageResponse;
import ch.schaub.leon.absentia.security.Roles;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
@Tag(name = "Mitarbeiter", description = "Mitarbeiter ansehen, erstellen, editieren und löschen")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employee/{id}")
    @Operation(summary = "Gibt einen spezifischen Mitarbeiter zurück")
    @ApiResponse(responseCode = "200")
    @RolesAllowed(Roles.User)
    public ResponseEntity<Employee> getEmployee(
            @Parameter(description = "Die id des zu suchenden Mitarbeiter")
            @PathVariable
            int id
    ) {
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }

    @GetMapping("/employee")
    @Operation(summary = "Gibt alle Mitarbeiter zurück")
    @ApiResponse(responseCode = "200")
    @RolesAllowed(Roles.User)
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> employeeList = employeeService.getAllEmployees();
        return ResponseEntity.ok(employeeList);
    }

    @PostMapping("/employee/add")
    @Operation(summary = "Fügt einen Mitarbeiter hinzu")
    @ApiResponse(responseCode = "200")
    @RolesAllowed(Roles.User)
    public ResponseEntity<Employee> addEmployee(
            @Parameter(description = "Der Inhalt des Mitarbeiter")
            @Validated
            @RequestBody
            Employee employee
    ) {
        Employee newEmployee = employeeService.addEmployee(employee);
        return ResponseEntity.ok(newEmployee);
    }

    @PutMapping("/employee/edit/{id}")
    @Operation(summary = "Editiert einen spezifische Mitarbeiter")
    @ApiResponse(responseCode = "200")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Employee> editEmployee(
            @Parameter(description = "Der neue Inhalt des Mitarbeiter")
            @Validated
            @RequestBody
            Employee employee,
            @Parameter(description = "Die Id des alten Mitarbeiter")
            @PathVariable
            int id
    ) {
        return ResponseEntity.ok(employeeService.updateEmployee(employee, id));
    }

    @DeleteMapping("/employee/delete/{id}")
    @Operation(summary = "Löscht einen spezifische Mitarbeiter")
    @ApiResponse(responseCode = "200")
    @RolesAllowed(Roles.Admin)
    public MessageResponse deleteEmployee(
            @Parameter(description = "Die Id des zu löschenden Mitarbeiter")
            @PathVariable
            int id
    ) {
        return employeeService.deleteEmployee(id);
    }
}
