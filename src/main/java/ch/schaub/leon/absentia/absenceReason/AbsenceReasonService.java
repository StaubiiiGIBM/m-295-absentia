package ch.schaub.leon.absentia.absenceReason;

import ch.schaub.leon.absentia.base.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AbsenceReasonService {

    private final AbsenceReasonRepository absenceReasonRepository;

    public AbsenceReason getAbsenceReason(int id) {
        return absenceReasonRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Absence Reason with id " + id + " not found")
        );
    }

    public List<AbsenceReason> getAllAbsenceReasons() {
        return absenceReasonRepository.findAll();
    }

    public AbsenceReason addAbsenceReason(AbsenceReason absenceReason) {
        return absenceReasonRepository.save(absenceReason);
    }

    public AbsenceReason updateAbsenceReason(AbsenceReason absenceReason, int id) {
        AbsenceReason originalAbsenceReason = absenceReasonRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Absence Reason with id " + id + " not found")
        );

        originalAbsenceReason.setAbsence_reason(absenceReason.getAbsence_reason());

        return absenceReasonRepository.save(originalAbsenceReason);
    }
    public MessageResponse deleteAbsenceReason(int id) {
        absenceReasonRepository.deleteById(id);
        return new MessageResponse("Absence Reason " + id + " deleted");
    }
}
