package org.example.hexlet.model;

import lombok.*;

@Getter
@ToString
@Setter
@NoArgsConstructor
public  class User {
    private int id;

    @ToString.Include
    private  String firstName;
    private  String lastName;
    private  String password;

    public User(int id, String firstName, String lastName, String password){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public User(String firstName, String lastName, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }
    /*
    public String setFirstName(String name) {
        firstName = name;
    }*/


}
