package com.jasonfly.helloworld.param;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class WebUserParam {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long id;

    @NotEmpty(message="name should not be empty")
    private String userName;

    @Max(value = 100, message = "age should not be larger than 100")
    @Min(value = 18, message = "age should be larger than 18")
    private int age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @NotEmpty(message="password should not be empty")
    @Length(min=6, message = "password length should not be less than 6")
    private String passWord;
}
