package ch.schaub.leon.absentia.absence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AbsenceService {

    private final AbsenceRepository absenceRepository;

    public Absence getAbsence(int id) {
        return absenceRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Absence with id " + id + " not found")
        );
    }

    public List<Absence> getAllAbsences() {
        return absenceRepository.findAll();
    }

    public Absence addAbsence(Absence absence) {
        return absenceRepository.save(absence);
    }

    public Absence updateAbsence(Absence absence, int id) {
        Absence originalAbsence = absenceRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Absence with id " + id + " not found")
        );

        originalAbsence.setEmployee(absence.getEmployee());
        originalAbsence.setFrom_date(absence.getFrom_date());
        originalAbsence.setTo_date(absence.getTo_date());
        originalAbsence.setDescription(absence.getDescription());
        originalAbsence.setAbsenceReason(absence.getAbsenceReason());

        return absenceRepository.save(originalAbsence);
    }
    public String deleteAbsence(int id) {
        absenceRepository.deleteById(id);
        return "Absence with id " + id + " deleted";
    }
}
