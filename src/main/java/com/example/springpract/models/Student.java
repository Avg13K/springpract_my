package com.example.springpract.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name, address, phone_num, email;
    private double avg_academic_performance;

    public Student(){

    }

    public Student(String name, String address, String phone_num, String email, double avg_academic_performance) {
        this.name=name;
        this.address=address;
        this.phone_num=phone_num;
        this.email=email;
        this.avg_academic_performance=avg_academic_performance;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getAvg_academic_performance() {
        return avg_academic_performance;
    }

    public void setAvg_academic_performance(double avg_academic_performance) {
        this.avg_academic_performance = avg_academic_performance;
    }
}
