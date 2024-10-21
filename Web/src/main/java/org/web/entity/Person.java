package org.web.entity;

import jakarta.persistence.*;

import java.util.LinkedList;
import java.util.List;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private int age;
    @Column
    private String gender;
    @Column
    private String links;
    @Column
    private String linkToAva;

    public Person(String name, String surname, int age, String gender, String links, String linkToAva) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.links = links;
        this.linkToAva = linkToAva;
    }

    public Person() {

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public String getLinkToAva() {
        return linkToAva;
    }

    public void setLinkToAva(String linkToAva) {
        this.linkToAva = linkToAva;
    }

}
