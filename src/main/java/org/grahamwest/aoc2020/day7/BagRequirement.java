package org.grahamwest.aoc2020.day7;

public class BagRequirement {
    public BagRequirement(String color, int quantity) {
        this.color = color;
        this.quantity = quantity;
    }

    String color;
    int quantity;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
