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

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class FindBetweenTest {


    private final EmployeeService employeeService;

    @Autowired
    public FindBetweenTest(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Test
    void findBetween_shouldThrowIllegalArgumentException_whenStartYearIsNegative() {
        // Arrange
        int startYear = -1;
        int endYear = 2023;

        assertThrows(IllegalArgumentException.class, () -> employeeService.findBetween(startYear, endYear));
    }

    @Test
    void findBetween_shouldThrowIllegalArgumentException_whenEndYearIsNegative() {

        int startYear = 2020;
        int endYear = -1;

        assertThrows(IllegalArgumentException.class, () -> employeeService.findBetween(startYear, endYear));
    }

    @Test
    void findBetween_shouldThrowIllegalArgumentException_whenStartYearGreaterThanEndYear() {

        int startYear = 2023;
        int endYear = 2020;

        assertThrows(IllegalArgumentException.class, () -> employeeService.findBetween(startYear, endYear));
    }

    @Test
    void findBetween_shouldReturnEmptyList_whenStartYearEqualsEndYear() {
        int startYear = 2023;
        int endYear = 2023;

        List<Employee> result = employeeService.findBetween(startYear, endYear);

        assertTrue(result.isEmpty());
    }

    @Test
    void findBetween_shouldCallRepository_whenStartYearLessThanEndYear() {
        employeeService.deleteAll();

        int startYear = 1990;
        int endYear = 2000;

        List<Employee> expectedEmployees = new ArrayList<>();
        expectedEmployees.add(new Employee("Иван", "Петров", LocalDate.of(1991, 1, 15), "IT", new BigDecimal(50000)));
        expectedEmployees.add(new Employee("Мария", "Иванова", LocalDate.of(1995, 7, 20), "HR", new BigDecimal(45000)));
        expectedEmployees.forEach(employeeService::save);

        List<Employee> result = employeeService.findBetween(startYear, endYear);

        assertEquals(expectedEmployees.size(), result.size());
    }


}
