package com.example.demo.domain;

public class City {
    private String name;
    private int km;

    public City(String name, int km) {
        this.name = name;
        this.km = km;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }
}
