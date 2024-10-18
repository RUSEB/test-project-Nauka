package ru.russeb.testprojectfornauka.model;


import lombok.Data;

import java.util.Objects;

@Data
public class EmployeeNamesCount {

    private String name;

    private Long count;


    public EmployeeNamesCount(String name,Long count) {
        this.count = count;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeNamesCount that = (EmployeeNamesCount) o;
        return count == that.count && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString(){
        return "{%s: %d}".formatted(name,count);
    }
}
