package com.example.pizzadelivery.transfer.pizza;

import lombok.Data;

@Data
public class PizzaInCartResponse {

    private long id;
    private String name;
    private String ingredients;
    private double price;
    private int quantity;
    private String imagePath;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PizzaInCartResponse that = (PizzaInCartResponse) o;

        if (id != that.id) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (quantity != that.quantity) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (ingredients != null ? !ingredients.equals(that.ingredients) : that.ingredients != null) return false;
        return imagePath != null ? imagePath.equals(that.imagePath) : that.imagePath == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (ingredients != null ? ingredients.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + quantity;
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        return result;
    }
}
