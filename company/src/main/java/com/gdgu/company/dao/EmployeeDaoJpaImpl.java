package com.gdgu.company.dao;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.gdgu.company.entity.Employee;

public class EmployeeDaoJpaImpl implements EmployeeDao<Employee>{
    
    private EntityManager entityManager;

    public EmployeeDaoJpaImpl() {
        entityManager = Persistence.createEntityManagerFactory("companyDatabase")
                                   .createEntityManager();
    }

    @Override
    public Optional<Employee> getEmployee(long employeeId) {
        return Optional.ofNullable(entityManager.find(Employee.class, employeeId));
    }

    @Override
    public List<Employee> getEmployeeList() {
        return entityManager.createQuery("FROM Employee").getResultList();
    }

    @Override
    public void addEmployee(Employee employee) {
        validateDetails(employee);
        executeInsideTransaction(entityManager -> entityManager.persist(employee));
    }

    @Override
    public void updateEmployee(long employeeId, String[] params) {
        Employee employee = entityManager.find(Employee.class, employeeId);
        employee.setName(params[0]);
        employee.setPhoneNo(params[1]);
        employee.setEmail(params[2]);
        employee.setSalary(Float.valueOf(params[3]));
        validateDetails(employee);
        executeInsideTransaction(entityManager -> entityManager.merge(employee));
    }

    @Override
    public void removeEmployee(long employeeId) {
        Employee employee = entityManager.find(Employee.class, employeeId);
        System.out.println(employee);
        executeInsideTransaction(entityManager -> entityManager.remove(employee));
    }

    private void validateDetails(Employee employee) {
        employee.validateName();
        employee.validatePhoneNo();
        employee.validateEmail();
        employee.validateSalary();
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        action.accept(entityManager);
        tx.commit();
    }

}