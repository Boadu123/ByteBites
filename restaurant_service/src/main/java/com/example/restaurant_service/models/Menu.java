package com.example.restaurant_service.models;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "menu_items")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal price;
    private String description;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    public Menu() {}

    public Menu(Long id, String name, BigDecimal price, String description, Restaurant restaurant) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.restaurant = restaurant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public static class Builder {
        private Long id;
        private String name;
        private BigDecimal price;
        private String description;
        private Restaurant restaurant;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder restaurant(Restaurant restaurant) {
            this.restaurant = restaurant;
            return this;
        }

        public Menu build() {
            Menu menuItem = new Menu();
            menuItem.setId(this.id);
            menuItem.setName(this.name);
            menuItem.setPrice(this.price);
            menuItem.setDescription(this.description);
            menuItem.setRestaurant(this.restaurant);
            return menuItem;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
