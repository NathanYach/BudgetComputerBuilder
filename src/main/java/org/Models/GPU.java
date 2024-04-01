package org.Models;

public class GPU implements Hardware {

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public int getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(int benchmark) {
        this.benchmark = benchmark;
    }

    public String getVram() {
        return vram;
    }

    public void setVram(String vram) {
        this.vram = vram;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    private String link;
    private int price;
    private String name;
    private String power;
    private int benchmark;
    private String vram;
    private int rating;

    @Override
    public String toString() {
        return
                name +
                ", price=" + price +
                ", link='" + link  +
                ", power='" + power  +
                ", benchmark=" + benchmark +
                ", vram='" + vram +
                ", rating=" + rating +
                '}';
    }
}
