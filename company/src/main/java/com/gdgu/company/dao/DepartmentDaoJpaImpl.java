package com.gdgu.company.dao;

import java.util.List;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.gdgu.company.entity.Department;

public class DepartmentDaoJpaImpl implements DepartmentDao<Department>{

    private EntityManager entityManager;

    public DepartmentDaoJpaImpl() {
        entityManager = Persistence.createEntityManagerFactory("companyDatabase")
                                   .createEntityManager();
    }

    @Override
    public List<Department> getDepartmentList() {
        return entityManager.createQuery("FROM Department").getResultList();
    }

    @Override
    public void addDepartment(Department department) {
        executeInsideTransaction(entityManager -> entityManager.persist(department));
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        action.accept(entityManager);
        tx.commit();
    }
    
}
