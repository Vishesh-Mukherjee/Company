package com.gdgu.company.dao;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao<T> {

    public Optional<T> getEmployee(long employeeId);
    public List<T> getEmployeeList();
    public void addEmployee(T employee);
    public void updateEmployee(long employeeId, String[] params);
    public void removeEmployee(long employeeId);
    
}
