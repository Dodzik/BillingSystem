package com.example.webapp.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    private String firstName;
    private String lastName;
    private String mainNumber;
    private int transferUsed;

    @ManyToMany
    private Set<Call> listOfCalls;

    public User(String firstName, String lastName, String mainNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mainNumber = mainNumber;
        this.listOfCalls = new HashSet<>();
    }

    public void addCall(Call call) {
        listOfCalls.add(call);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return transferUsed == user.transferUsed &&
                id.equals(user.id) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(mainNumber, user.mainNumber) &&
                Objects.equals(listOfCalls, user.listOfCalls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, mainNumber, transferUsed, listOfCalls);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", number='" + mainNumber + '\'' +
                ", transferUsed=" + transferUsed +
                ", listOfCalls=" + listOfCalls +
                '}';
    }
}