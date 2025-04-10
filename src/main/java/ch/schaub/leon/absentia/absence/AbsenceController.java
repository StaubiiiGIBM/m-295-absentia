package ch.schaub.leon.absentia.absence;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Validated
public class AbsenceController {

    private final AbsenceService absenceService;

    @GetMapping("/absence/{id}")
    public ResponseEntity<Absence> getAbsence(@PathVariable int id) {
        return ResponseEntity.ok(absenceService.getAbsence(id));
    }

    @GetMapping("/absence")
    public ResponseEntity<List<Absence>> getAllAbsences() {
        List<Absence> absenceList = absenceService.getAllAbsences();
        return ResponseEntity.ok(absenceList);
    }

    @PostMapping("/absence/add")
    public ResponseEntity<Absence> addAbsence(@Validated @RequestBody Absence absence) {
        Absence newAbsence = absenceService.addAbsence(absence);
        return ResponseEntity.ok(newAbsence);
    }

    @PutMapping("/absence/edit/{id}")
    public ResponseEntity<Absence> editAbsence(
            @Validated @RequestBody Absence absence, @PathVariable int id
    ) {
        return ResponseEntity.ok(absenceService.updateAbsence(absence, id));
    }

    @DeleteMapping("/absence/delete/{id}")
    public String deleteAbsence(@PathVariable int id) {
        return absenceService.deleteAbsence(id);
    }
}
