package ch.schaub.leon.absentia.employee;

import jakarta.persistence.*;
import lombok.Data;

import ch.schaub.leon.absentia.department.Department;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Data
@Table(name = "tb_employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 30, nullable = false)
    @NotEmpty
    @Size(min = 2, max = 30)
    private String first_name;
    @Column(length = 30, nullable = false)
    @NotEmpty
    @Size(min = 2, max = 30)
    private String last_name;
    @ManyToOne(optional = false)
    @JoinColumn(name = "department_id")
    private Department department;
}
