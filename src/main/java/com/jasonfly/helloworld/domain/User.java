package com.jasonfly.helloworld.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

public class User implements Serializable {
    @NotEmpty(message="name should not be empty")
    private String name;

    @Max(value = 100, message = "age should not be larger than 100")
    @Min(value = 18, message = "age should be larger than 18")
    private int age;

    @NotEmpty(message="password should not be empty")
    @Length(min=6, message = "password length should not be less than 6")
    private String pass;

    public User(String name, int age, String pass) {
        this.name = name;
        this.age = age;
        this.pass = pass;
    }

    public User() {
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
