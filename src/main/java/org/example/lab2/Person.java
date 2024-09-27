package org.example.lab2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Objects;

public class Person {
    private final String firstName;
    private final String lastName;
    private final int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Person another)) return false;
        if(this.firstName == null || this.lastName == null) return false;

        return this.age == another.age
                && Objects.equals(this.firstName, another.firstName)
                && Objects.equals(this.lastName, another.lastName);
    }

    public static void main(String[] args) {

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        Person p1 = new Person("John", "Doe", 69);
        Person p2 = new Person("John", "Doe", 69);

        String res = gson.toJson(p2);

        p2 = gson.fromJson(res, Person.class);
        System.out.println(p1.equals(p2));


    }
}
