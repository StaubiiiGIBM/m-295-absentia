package ch.schaub.leon.absentia.absenceReason;

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
@Tag(name = "Absenzgründe", description = "Absenzgründe ansehen, erstellen, editieren und löschen")
public class AbsenceReasonController {

    private final AbsenceReasonService absenceReasonService;

    @GetMapping("/absenceReason/{id}")
    @Operation(summary = "Gibt einen spezifischen Absenzgrund zurück")
    @ApiResponse(responseCode = "200")
    @RolesAllowed(Roles.User)
    public ResponseEntity<AbsenceReason> getAbsenceReason(
            @Parameter(description = "Die Id des zu suchenden Absenzgrund")
            @PathVariable
            int id
    ) {
        return ResponseEntity.ok(absenceReasonService.getAbsenceReason(id));
    }

    @GetMapping("/absenceReason")
    @Operation(summary = "Gibt alle Absenzgründe zurück")
    @ApiResponse(responseCode = "200")
    @RolesAllowed(Roles.User)
    public ResponseEntity<List<AbsenceReason>> getAllAbsenceReason() {
        List<AbsenceReason> absenceReasonList = absenceReasonService.getAllAbsenceReasons();
        return ResponseEntity.ok(absenceReasonList);
    }

    @PostMapping("/absenceReason/add")
    @Operation(summary = "Fügt einen Absenzgrund hinzu")
    @ApiResponse(responseCode = "200")
    @RolesAllowed(Roles.User)
    public ResponseEntity<AbsenceReason> addAbsenceReason(
            @Parameter(description = "Der inhalt des Absenzgrund")
            @Validated
            @RequestBody
            AbsenceReason absenceReason
    ) {
        AbsenceReason newAbsenceReason = absenceReasonService.addAbsenceReason(absenceReason);
        return ResponseEntity.ok(newAbsenceReason);
    }

    @PutMapping("/absenceReason/edit/{id}")
    @Operation(summary = "Editiert einen spezifischen Absenzgrund")
    @ApiResponse(responseCode = "200")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<AbsenceReason> editAbsenceReason(
            @Parameter(description = "Der neue Inhalt des Absenzgrund")
            @Validated
            @RequestBody
            AbsenceReason absenceReason,
            @Parameter(description = "Die Id des alten Absenzgrund")
            @PathVariable
            int id
    ) {
        return ResponseEntity.ok(absenceReasonService.updateAbsenceReason(absenceReason, id));
    }

    @DeleteMapping("/absenceReason/delete/{id}")
    @Operation(summary = "Löscht einen spezifischen Absenzgrund")
    @ApiResponse(responseCode = "200")
    @RolesAllowed(Roles.Admin)
    public String deleteAbsenceReason(
            @Parameter(description = "Die Id des zu löschenden Absenzgrund")
            @PathVariable
            int id
    ) {
        return absenceReasonService.deleteAbsenceReason(id);
    }
}
