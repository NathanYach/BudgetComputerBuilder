package org.Models;

public interface Hardware {
    int getPrice();
    void setPrice(int price);
    String getName();
    void setName(String name);
    String getPower();
    void setPower(String power);
    int getBenchmark();
    void setBenchmark(int benchmark);
    int getRating();
    void setRating(int rating);
}