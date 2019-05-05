package com.yang.petstore.service.Model;

import java.io.Serializable;
import java.util.Date;

public class UserModel implements Serializable {

    private static final long serialVersionUID = -7248153408990849870L;

    private Integer id;

    private Integer gender;

    private String name;

    private Integer age;

    private String telphone;

    private String email;

    private Date registerDate;

    private String encrptPassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getEncrptPassword() {
        return encrptPassword;
    }

    public void setEncrptPassword(String encrptPassword) {
        this.encrptPassword = encrptPassword;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", gender=" + gender +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", telphone='" + telphone + '\'' +
                ", email='" + email + '\'' +
                ", registerDate=" + registerDate +
                ", encrptPassword='" + encrptPassword + '\'' +
                '}';
    }
}
