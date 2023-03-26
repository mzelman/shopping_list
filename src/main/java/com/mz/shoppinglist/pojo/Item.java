package com.mz.shoppinglist.pojo;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;



public class Item {
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 20, message = "Length of name cannot be more than 20 characters")
    private String name;
    private boolean crossedOut;
    private String id;

    public Item() {
        this.crossedOut = false;
        this.id = UUID.randomUUID().toString();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean isCrossedOut() {
        return this.crossedOut;
    }

    public void crossOut() {
        this.crossedOut = true;
    }

    public void uncross() {
        this.crossedOut = false;
    }

    public String getId() {
        return this.id;
    }

}
