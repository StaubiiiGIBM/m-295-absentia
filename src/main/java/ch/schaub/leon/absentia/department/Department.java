package ch.schaub.leon.absentia.department;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false, length = 3)
    @Size(min = 2, max = 3)
    @NotEmpty
    private String department_code;
    @Column(length = 255)
    private String description;
}
