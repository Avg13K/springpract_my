package com.example.springpract.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    private String professors_name;
    private int num;
    private double cost;

    public Course(){

    }

    public Course(String name, String professors_name,int num, double cost) {
        this.professors_name = professors_name;
        this.name = name;
        this.num = num;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfessors_name() {
        return professors_name;
    }

    public void setProfessors_name(String professors_name) {
        this.professors_name = professors_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
