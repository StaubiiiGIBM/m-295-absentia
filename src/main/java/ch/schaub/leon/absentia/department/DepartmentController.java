package ch.schaub.leon.absentia.department;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Validated
@Tag(name = "Abteilungen", description = "Abteilungen ansehen, erstellen, editieren und löschen")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/department/{id}")
    @Operation(summary = "Gibt eine spezifische Abteilung zurück")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<Department> getDepartment(
            @Parameter(description = "Die Id der zu suchenden Abteilung")
            @PathVariable
            int id
    ) {
        Department department = departmentService.getDepartment(id);
        return ResponseEntity.ok(department);
    }

    @GetMapping("/department")
    @Operation(summary = "Gibt alle Abteilungen zurück")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @PostMapping("/department/add")
    @Operation(summary = "Fügt eine Abteilung hinzu")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<Department> addDepartment(
            @Parameter(description = "Der Inhalt der Abteilung")
            @Validated
            @RequestBody
            Department department
    ) {
        Department newDepartment = departmentService.addDepartment(department);
        return ResponseEntity.ok(newDepartment);
    }

    @PutMapping("/department/edit/{id}")
    @Operation(summary = "Editiert eine spezifische Abteilung")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<Department> editDepartment(
            @Parameter(description = "Der neue Inhalt der Abteilung")
            @Validated
            @RequestBody
            Department department,
            @Parameter(description = "Die Id der alten Abteilung")
            @PathVariable
            int id
    ) {
        return ResponseEntity.ok(departmentService.updateDepartment(department, id));
    }

    @DeleteMapping("/department/delete/{id}")
    @Operation(summary = "Löscht eine spezifische Abteilung")
    @ApiResponse(responseCode = "200")
    public String deleteDepartment(
            @Parameter(description = "Die Id der zu löschenden Abteilung")
            @PathVariable
            int id
    ) {
        return departmentService.deleteDepartment(id);
    }
}
