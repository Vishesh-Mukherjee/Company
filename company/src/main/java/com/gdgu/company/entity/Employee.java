package com.gdgu.company.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Employee {
    
    @Id
    private long id;
    
    private String name;
    private byte age;
    private Gender gender;
    private String phoneNo;
    private String email;
    private float salary;
    private Date joiningDate;

    @ManyToOne
    private Department department;

    public Employee() {
    }

    public Employee(long id, String name, byte age, Gender gender, String phoneNo, String email, float salary, Date joiningDate, Department department) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phoneNo = phoneNo;
        this.email = email;
        this.salary = salary;
        this.joiningDate = joiningDate;
        this.department = department;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void validateName() {
        if (name.length() <= 3) {
            throw new RuntimeException("Name should be greater than 2");
        }
    }

    public void validatePhoneNo() {
        if (phoneNo.length() != 10) {
            throw new RuntimeException("Phone number should be 10 digit long");
        }
        if (this.phoneNo.matches("//d+")) {
            throw new RuntimeException("Phone number should contain only digits");
        }
    }

    public void validateEmail() {
        if (!email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
            throw new RuntimeException("Invalid email");
        }
    }

    public void validateSalary() {
        if (salary < 10000F) {
            throw new RuntimeException("Salary should be greater than 10000");
        }
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", age='" + getAge() + "'" +
            ", gender='" + getGender() + "'" +
            ", phoneNo='" + getPhoneNo() + "'" +
            ", email='" + getEmail() + "'" +
            ", salary='" + getSalary() + "'" +
            ", joiningDate='" + getJoiningDate() + "'" +
            "}";
    }

}