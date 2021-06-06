package com.gdgu.company.dao;

import java.util.List;

public interface DepartmentDao<T> {
    public List<T> getDepartmentList();
    public void addDepartment(T department);
}
