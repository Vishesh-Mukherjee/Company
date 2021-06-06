package com.gdgu.company.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Department {
    @Id
    private int id;

    private String name;
    private String departmentHead;

    @OneToMany(mappedBy = "department")
    private List<Employee> employeeList;

    public Department() {

    }

    public Department(int id, String name, String departmentHead, List<Employee> employeeList) {
        this.id = id;
        this.name = name;
        this.departmentHead = departmentHead;
        this.employeeList = employeeList;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentHead() {
        return this.departmentHead;
    }

    public void setDepartmentHead(String departmentHead) {
        this.departmentHead = departmentHead;
    }

    public List<Employee> getEmployeeList() {
        return this.employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", departmentHead='" + getDepartmentHead() + "'" +
            ", employeeList='" + getEmployeeList() + "'" +
            "}";
    }

}
