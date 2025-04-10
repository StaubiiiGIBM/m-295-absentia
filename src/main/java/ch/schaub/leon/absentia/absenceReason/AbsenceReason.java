package ch.schaub.leon.absentia.absenceReason;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name= "tb_reasons")
public class AbsenceReason {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 20, nullable = false)
    @Size(max = 20, min = 3)
    @NotEmpty
    private String absence_reason;
}
