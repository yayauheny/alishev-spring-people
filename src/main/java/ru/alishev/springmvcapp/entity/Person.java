package ru.alishev.springmvcapp.entity;


import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class Person {

    private Long id;
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, max = 32, message = "Name should be between 2 and 32 characters")
    private String name;
    @Positive(message = "Age cannot be less than 0")
    private int age;
    @Email
    private String email;

    public Person() {
    }

    public Person(Long id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
