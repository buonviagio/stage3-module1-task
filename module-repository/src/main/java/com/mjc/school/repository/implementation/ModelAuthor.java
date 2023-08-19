package com.mjc.school.repository.implementation;

import java.util.Objects;

public class ModelAuthor {
    private int id;
    private String name;

    public ModelAuthor(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelAuthor modelAuthor = (ModelAuthor) o;
        return Objects.equals(id, modelAuthor.id) && Objects.equals(name, modelAuthor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
