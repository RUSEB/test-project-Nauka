package ru.russeb.testprojectfornauka;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.russeb.testprojectfornauka.entity.Employee;
import ru.russeb.testprojectfornauka.service.EmployeeService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest
public class FindEmployeeByIdTests {

    private final EmployeeService employeeService;

    @Autowired
    public FindEmployeeByIdTests(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Test
    void findById_shouldReturnEmployee_whenEmployeeExists() {
        List<Employee> employees = createEmployeeList();
        List<Integer> ids = new ArrayList<>();
        employees.forEach(employee -> ids.add(employeeService.save(employee)));

        int id = ids.get((int)(Math.random()*ids.size()));

        Optional<Employee> foundEmployee = employeeService.findById(id);

        assertTrue(foundEmployee.isPresent());
        assertNotNull(foundEmployee.get());
        assertEquals(id, foundEmployee.get().getId());
        employeeService.deleteAll();
    }

    @Test
    void findById_shouldReturnEmptyOptional_whenEmployeeDoesNotExist() {
        List<Employee> employees = createEmployeeList();
        employees.forEach(employeeService::save);

        int nonExistingEmployeeId = -1;
        Optional<Employee> foundEmployee = employeeService.findById(nonExistingEmployeeId);
        assertFalse(foundEmployee.isPresent());

        employeeService.deleteAll();
    }


    private static List<Employee> createEmployeeList() {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee("Иван", "Петров", LocalDate.of(1990, 1, 15), "IT", new BigDecimal(50000)));
        employees.add(new Employee("Мария", "Иванова", LocalDate.of(1985, 7, 20), "HR", new BigDecimal(45000)));
        employees.add(new Employee("Алексей", "Сидоров", LocalDate.of(1995, 3, 10), "Sales", new BigDecimal(60000)));
        employees.add(new Employee("Ольга", "Кузнецова", LocalDate.of(1988, 11, 5), "Marketing", new BigDecimal(55000)));
        employees.add(new Employee("Дмитрий", "Соколов", LocalDate.of(1992, 9, 25), "Finance", new BigDecimal(70000)));
        employees.add(new Employee("Екатерина", "Смирнова", LocalDate.of(1987, 5, 12), "IT", new BigDecimal(50000)));
        employees.add(new Employee("Сергей", "Попов", LocalDate.of(1991, 2, 18), "HR", new BigDecimal(45000)));
        employees.add(new Employee("Ольга", "Козлова", LocalDate.of(1994, 8, 8), "Sales", new BigDecimal(60000)));
        employees.add(new Employee("Андрей", "Васильев", LocalDate.of(1989, 10, 22), "Marketing", new BigDecimal(55000)));
        employees.add(new Employee("Елена", "Федорова", LocalDate.of(1993, 6, 16), "Finance", new BigDecimal(70000)));

        return employees;
    }
}
