package ru.russeb.testprojectfornauka.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "t_employee")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "c_name")
    private String name;

    @Column(name = "c_surname")
    private String surname;

    @Column(name = "c_date_of_birth")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date_of_birth;

    @Column(name = "c_department")
    private String department;

    @Column(name = "c_salary")
    private BigDecimal salary;


    public Employee(String name, String surname, LocalDate date_of_birth, String department, BigDecimal salary) {
        this.name = name;
        this.surname = surname;
        this.date_of_birth = date_of_birth;
        this.department = department;
        this.salary = salary;
    }

    public Employee() {

    }
}
