package org.example.hexlet.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public final class Course {
    private int id;

    @ToString.Include
    private String name;
    private String description;

    public Course(int id,String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
