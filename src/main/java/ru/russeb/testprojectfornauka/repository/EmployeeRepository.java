package ru.russeb.testprojectfornauka.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.russeb.testprojectfornauka.entity.Employee;
import ru.russeb.testprojectfornauka.model.EmployeeNamesCount;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // Найти пользователя по ID
    @Query(value = "SELECT * FROM t_employee WHERE t_employee.id = ?1", nativeQuery = true)
    Optional<Employee> findById(Integer id);

    // Сгруппировать пользователей по именам
    @Query(value = "SELECT t_employee.c_name as name, COUNT(t_employee.c_name) as count FROM t_employee GROUP BY t_employee.c_name", nativeQuery = true)
    List<Object[]> groupByName();

    // Поиск между датами
    @Query(value = "SELECT * FROM t_employee WHERE DATE_PART('Year', t_employee.c_date_of_birth) BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Employee> findBetween(int startYear, int endYear);
}
