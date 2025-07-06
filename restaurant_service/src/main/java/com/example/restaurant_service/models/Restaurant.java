package com.example.restaurant_service.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private long ownerId;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Menu> menuItems = new ArrayList<>();

    public Restaurant() {}

    public Restaurant(Long id, String name, String address, long ownerId, List<Menu> menuItems) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.ownerId = ownerId;
        this.menuItems = menuItems;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public List<Menu> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<Menu> menuItems) {
        this.menuItems = menuItems;
    }

    public static class Builder {
        private Long id;
        private String name;
        private String address;
        private long ownerId;
        private List<Menu> menuItems = new ArrayList<>();

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder ownerId(long ownerId) {
            this.ownerId = ownerId;
            return this;
        }

        public Builder menuItems(List<Menu> menuItems) {
            this.menuItems = menuItems;
            return this;
        }

        public Builder addMenuItem(Menu menuItem) {
            this.menuItems.add(menuItem);
            return this;
        }

        public Restaurant build() {
            Restaurant restaurant = new Restaurant();
            restaurant.setId(this.id);
            restaurant.setName(this.name);
            restaurant.setAddress(this.address);
            restaurant.setOwnerId(this.ownerId);
            restaurant.setMenuItems(this.menuItems);
            return restaurant;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
