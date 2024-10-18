package ru.russeb.testprojectfornauka;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.russeb.testprojectfornauka.entity.Employee;
import ru.russeb.testprojectfornauka.model.EmployeeNamesCount;
import ru.russeb.testprojectfornauka.service.EmployeeService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class GroupByNameTests {
    private final EmployeeService employeeService;

    @Autowired
    public GroupByNameTests(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @Test
    void test(){
        employeeService.deleteAll();
        List<Employee> employeeList = createEmployeeList();
        employeeList.forEach(employeeService::save);

        List<EmployeeNamesCount> resultEmployeeNamesCounts = employeeService.groupByName();
        System.out.println(Collections.singletonList(resultEmployeeNamesCounts));

        List<EmployeeNamesCount> expectedEmployeeNamesCounts = calculateEmployeeNamesCount(employeeList);

        assertTrue(compareLists(expectedEmployeeNamesCounts,resultEmployeeNamesCounts));
    }


    private static boolean compareLists(List<EmployeeNamesCount> list1, List<EmployeeNamesCount> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
        return list1.containsAll(list2);
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

    private static List<EmployeeNamesCount> calculateEmployeeNamesCount(List<Employee> employeeList) {
        List<EmployeeNamesCount> employeeNamesCounts = new ArrayList<>();
        Map<String, Long> nameCounts = new HashMap<>();

        for (Employee employee : employeeList) {
            String name = employee.getName();
            nameCounts.put(name, nameCounts.getOrDefault(name, 0L) + 1);
        }

        for (Map.Entry<String, Long> entry : nameCounts.entrySet()) {
            employeeNamesCounts.add(new EmployeeNamesCount(entry.getKey(), entry.getValue()));
        }

        return employeeNamesCounts;
    }

}
