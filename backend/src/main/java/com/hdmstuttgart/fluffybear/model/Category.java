package com.hdmstuttgart.fluffybear.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Category {
    @JsonProperty("breakfast")
    BREAKFAST("breakfast"),
    @JsonProperty("lunch")
    LUNCH("lunch"),
    @JsonProperty("dinner")
    DINNER("dinner");

    private static Map<String, Category> FORMAT_MAP = Stream
            .of(Category.values())
            .collect(Collectors.toMap(s -> s.text, Function.identity()));

    private final String text;

    Category(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    @JsonCreator // This is the factory method and must be static
    public static Category fromString(String string) {
        return Optional
                .ofNullable(FORMAT_MAP.get(string))
                .orElseThrow(() -> new IllegalArgumentException(string));
    }
}
