package org.Models;

public class CPU implements Hardware {

    private String name;
    private String cpuLink;
    private String buyLink;
    private int price;
    private int rating;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
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
                name +
                ", price=" + price +
                ", Cpu-link='" + cpuLink +
                ", power='" + power +
                ", benchmark=" + benchmark +
                ", buy-Link='" + buyLink +
                ", rating=" + rating +
                '}';
    }
}
