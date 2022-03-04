package org.demo.po;

import java.io.Serializable;

/**
 * @author Shawn
 * @version 1.0
 * @description: TODO
 * @date 2022/2/17 19:53
 */
public class Employee implements Serializable {

    private Integer id;
    private String lastName;
    private String email;
    private String gender;
    private Department department;


    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", department=" + department +
                '}';
    }
    //getter and setter and toString()

    public Integer getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }
}

