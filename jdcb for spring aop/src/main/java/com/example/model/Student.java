package com.example.model;

public class Student {
    private int id;
    private String name;
    private String email;

    public Student() {
        System.out.println("[MODEL] Student no-arg constructor called");
    }

    public Student(String name, String email) {
        System.out.println("[MODEL] Student(String, String) constructor called");
        this.name = name;
        this.email = email;
    }

    public Student(int id, String name, String email) {
        System.out.println("[MODEL] Student(int, String, String) constructor called");
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        System.out.println("[MODEL] getId called");
        return id;
    }

    public void setId(int id) {
        System.out.println("[MODEL] setId called");
        this.id = id;
    }

    public String getName() {
        System.out.println("[MODEL] getName called");
        return name;
    }

    public void setName(String name) {
        System.out.println("[MODEL] setName called");
        this.name = name;
    }

    public String getEmail() {
        System.out.println("[MODEL] getEmail called");
        return email;
    }

    public void setEmail(String email) {
        System.out.println("[MODEL] setEmail called");
        this.email = email;
    }

    @Override
    public String toString() {
        System.out.println("[MODEL] toString called");
        return "Student {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
