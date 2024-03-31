package org.Models;

public class CPU {

    private String name;
    private String cpuLink;
    private String buyLink;
    private double price;
    private double rating;
    private int benchmark;

    private String power;
    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpuLink() {
        return cpuLink;
    }

    public void setCpuLink(String cpuLink) {
        this.cpuLink = cpuLink;
    }

    public String getBuyLink() {
        return buyLink;
    }

    public void setBuyLink(String buyLink) {
        this.buyLink = buyLink;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(int benchmark) {
        this.benchmark = benchmark;
    }

    @Override
    public String toString() {
        return
                name + '\'' +
                ", price=" + price +
                ", Cpu-link='" + cpuLink + '\'' +
                ", power='" + power + '\'' +
                ", benchmark=" + benchmark +
                ", buy-Link='" + buyLink + '\'' +
                ", rating=" + rating +
                '}';
    }
}
