package ch.schaub.leon.absentia.absence;

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
@Tag(name = "Absenzen", description = "Absenzen ansehen, erstellen, editieren und löschen")
public class AbsenceController {

    private final AbsenceService absenceService;

    @GetMapping("/absence/{id}")
    @Operation(summary = "Gibt eine spezifische Absenz zurück")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<Absence> getAbsence(
            @Parameter(description = "Die Id der zu suchende Absenz")
            @PathVariable
            int id
    ) {
        return ResponseEntity.ok(absenceService.getAbsence(id));
    }

    @GetMapping("/absence")
    @Operation(summary = "Gibt alle Absenzen zurück")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<List<Absence>> getAllAbsences() {
        List<Absence> absenceList = absenceService.getAllAbsences();
        return ResponseEntity.ok(absenceList);
    }

    @PostMapping("/absence/add")
    @Operation(summary = "Fügt eine Absenz hinzu")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<Absence> addAbsence(
            @Parameter(description = "Der Inhalt der Absenz")
            @Validated
            @RequestBody
            Absence absence
    ) {
        Absence newAbsence = absenceService.addAbsence(absence);
        return ResponseEntity.ok(newAbsence);
    }

    @PutMapping("/absence/edit/{id}")
    @Operation(summary = "Editiert eine spezifische Absenz")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<Absence> editAbsence(
            @Parameter(description = "Der neue Inhalt der Absenz")
            @Validated
            @RequestBody
            Absence absence,
            @Parameter(description = "Die Id der alten Absenz")
            @PathVariable
            int id
    ) {
        return ResponseEntity.ok(absenceService.updateAbsence(absence, id));
    }

    @DeleteMapping("/absence/delete/{id}")
    @Operation(summary = "Löscht eine spezifische Absenz")
    @ApiResponse(responseCode = "200")
    public String deleteAbsence(
            @Parameter(description = "Die Id der zu löschenden Absenz")
            @PathVariable
            int id
    ) {
        return absenceService.deleteAbsence(id);
    }
}
