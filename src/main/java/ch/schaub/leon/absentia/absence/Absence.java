package ch.schaub.leon.absentia.absence;

import ch.schaub.leon.absentia.absenceReason.AbsenceReason;

import ch.schaub.leon.absentia.employee.Employee;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "tb_absences")
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @Column(nullable = false)
    private Date from_date;
    @Column(nullable = false)
    private Date to_date;
    @Column(nullable = false, length = 255)
    private String description;
    @ManyToOne
    @JoinColumn(name = "absenceReason_id")
    private AbsenceReason absenceReason;
}
