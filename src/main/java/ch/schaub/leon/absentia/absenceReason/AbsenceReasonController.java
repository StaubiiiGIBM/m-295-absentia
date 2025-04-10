package ch.schaub.leon.absentia.absenceReason;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Validated
public class AbsenceReasonController {

    private final AbsenceReasonService absenceReasonService;

    @GetMapping("/absenceReason/{id}")
    public ResponseEntity<AbsenceReason> getAbsenceReason(@PathVariable int id) {
        return ResponseEntity.ok(absenceReasonService.getAbsenceReason(id));
    }

    @GetMapping("/absenceReason")
    public ResponseEntity<List<AbsenceReason>> getAllAbsenceReason() {
        List<AbsenceReason> absenceReasonList = absenceReasonService.getAllAbsenceReasons();
        return ResponseEntity.ok(absenceReasonList);
    }

    @PostMapping("/absenceReason/add")
    public ResponseEntity<AbsenceReason> addAbsenceReason(@Validated @RequestBody AbsenceReason absenceReason) {
        AbsenceReason newAbsenceReason = absenceReasonService.addAbsenceReason(absenceReason);
        return ResponseEntity.ok(newAbsenceReason);
    }

    @PutMapping("/absenceReason/edit/{id}")
    public ResponseEntity<AbsenceReason> editAbsenceReason(
            @Validated @RequestBody AbsenceReason absenceReason, @PathVariable int id
    ) {
        return ResponseEntity.ok(absenceReasonService.updateAbsenceReason(absenceReason, id));
    }

    @DeleteMapping("/absenceReason/delete/{id}")
    public String deleteAbsenceReason(@PathVariable int id) {
        return absenceReasonService.deleteAbsenceReason(id);
    }
}
