package ch.schaub.leon.absentia.absenceReason;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsenceReasonRepository extends JpaRepository<AbsenceReason, Integer> {

}
