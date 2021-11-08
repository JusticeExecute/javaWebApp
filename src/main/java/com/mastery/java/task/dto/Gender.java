package com.mastery.java.task.dto;

import java.util.Arrays;

public enum Gender {
    MALE(1),
    FEMALE(2);

    private final int id;

    Gender(int id) {
        this.id = id;
    }

    public static Gender byId(int id) {
        return Arrays.stream(Gender.values()).filter(d -> d.getId() == id).findFirst().orElse(null);
    }

    public int getId() {
        return id;
    }
}
