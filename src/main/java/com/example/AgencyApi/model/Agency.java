package com.example.AgencyApi.model;

import jakarta.validation.constraints.NotBlank;

public class Agency {
    @NotBlank(message = "ID cannot be null or blank.")
    private String id;
    @NotBlank(message = "Name cannot be null or blank.")
    private String name;
    @NotBlank(message = "Code cannot be null or blank.")
    private String code;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
