package ru.russeb.testprojectfornauka.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.russeb.testprojectfornauka.entity.Employee;
import ru.russeb.testprojectfornauka.model.EmployeeNamesCount;
import ru.russeb.testprojectfornauka.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService{

    private final EmployeeRepository employeeRepository;

    public Optional<Employee> findById(Integer id){
        return employeeRepository.findById(id);
    }

    public List<EmployeeNamesCount> groupByName(){
        List<EmployeeNamesCount> employeeNamesCounts = new ArrayList<>();

//        for(Object[] objects : employeeRepository.groupByName()){
//            employeeNamesCounts.add(new EmployeeNamesCount((String) objects[0],(Long)objects[1]));
//        }
        employeeRepository.groupByName().forEach(employeeName->employeeNamesCounts.add(new EmployeeNamesCount((String) employeeName[0],(Long)employeeName[1])));
        return employeeNamesCounts;
    }

    public List<Employee> findBetween(int startYear, int endYear){
        if(startYear<0 || endYear<0){
            throw new IllegalArgumentException("startYear and endYear must be greater than or equal to 0");
        }
        if(startYear > endYear){
            throw new IllegalArgumentException("startYear must be greater than endYear");
        }
        if(startYear == endYear){
            return Collections.emptyList();
        }
        return employeeRepository.findBetween(startYear, endYear);
    }

    public Integer save(Employee employee){
        return employeeRepository.save(employee).getId();
    }

    public void deleteAll(){
        employeeRepository.deleteAll();
    }

}
