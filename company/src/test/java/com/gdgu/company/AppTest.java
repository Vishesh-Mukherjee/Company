package com.gdgu.company;

import java.util.Arrays;

import com.gdgu.company.dao.DepartmentDao;
import com.gdgu.company.dao.DepartmentDaoJpaImpl;
import com.gdgu.company.dao.EmployeeDao;
import com.gdgu.company.dao.EmployeeDaoJpaImpl;
import com.gdgu.company.entity.Department;
import com.gdgu.company.entity.Employee;
import com.gdgu.company.entity.Gender;
import com.gdgu.company.util.ToDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppTest {
    
    EmployeeDao<Employee> empDao;
    DepartmentDao<Department> deptDao;
    Department productionDepartment;
    Employee emp1;
    Employee emp2;
    
    @BeforeEach
    public void setup() {
        empDao = new EmployeeDaoJpaImpl();
        deptDao = new DepartmentDaoJpaImpl(); 

        productionDepartment = new Department();
        productionDepartment.setId(10001);
        productionDepartment.setName("Production");
        productionDepartment.setDepartmentHead("Jim");

        emp1 = new Employee(101L, "Jack", (byte) 19, Gender.MALE, "9846839753", "jack123@gmail.com", 30000, ToDate.stringToDate("12-08-2020"), productionDepartment); 
        emp2 = new Employee(102L, "Lily", (byte) 22, Gender.FEMALE, "5896389021", "lily121@gmail.com", 35000, ToDate.stringToDate("07-02-2019"), productionDepartment); 
        
        productionDepartment.setEmployeeList(Arrays.asList(emp1, emp2));

        deptDao.addDepartment(productionDepartment);

        empDao.addEmployee(emp1);
        empDao.addEmployee(emp2);
    }

    @Test 
    public void addingDataIntoTheDatabase() {
        Assertions.assertEquals(2, empDao.getEmployeeList().size());
        Assertions.assertEquals(1, deptDao.getDepartmentList().size());
    }

    @Test
    public void fetchingDataFromTheDatabase() {
        Assertions.assertTrue(empDao.getEmployee(101).isPresent());
        Assertions.assertEquals(emp1, empDao.getEmployee(101).get());
        Assertions.assertTrue(empDao.getEmployee(103).isEmpty());
    }

    @Test
    public void updateingDataInTheDatabase() {
        Assertions.assertThrows(RuntimeException.class, () -> empDao.updateEmployee(101, new String[]{"Jack", "984683975", "jack123@gmail.com", "32000"}));
        empDao.updateEmployee(101, new String[]{"Jack", "9846839753", "jack123@gmail.com", "32000"});
        Assertions.assertEquals(32000, empDao.getEmployee(101).get().getSalary());
    }

    @Test
    public void removingDataFromTheDatabase() {
        Assertions.assertTrue(empDao.getEmployee(102).isPresent());
        empDao.removeEmployee(102);
        Assertions.assertTrue(empDao.getEmployee(102).isEmpty());
    }
}
