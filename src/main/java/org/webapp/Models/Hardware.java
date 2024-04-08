package org.webapp.Models;

/**
 * An interface representing hardware components.
 */
public interface Hardware {
    /**
     * Retrieves the price of the hardware component.
     *
     * @return The price of the hardware component.
     */
    int getPrice();

    /**
     * Sets the price of the hardware component.
     *
     * @param price The price to set for the hardware component.
     */
    void setPrice(int price);

    /**
     * Retrieves the name of the hardware component.
     *
     * @return The name of the hardware component.
     */
    String getName();

    /**
     * Sets the name of the hardware component.
     *
     * @param name The name to set for the hardware component.
     */
    void setName(String name);

    /**
     * Retrieves the power information of the hardware component.
     *
     * @return The power information of the hardware component.
     */
    String getPower();

    /**
     * Sets the power information of the hardware component.
     *
     * @param power The power information to set for the hardware component.
     */
    void setPower(String power);

    /**
     * Retrieves the benchmark value of the hardware component.
     *
     * @return The benchmark value of the hardware component.
     */
    int getBenchmark();

    /**
     * Sets the benchmark value of the hardware component.
     *
     * @param benchmark The benchmark value to set for the hardware component.
     */
    void setBenchmark(int benchmark);

    /**
     * Retrieves the rating of the hardware component.
     *
     * @return The rating of the hardware component.
     */
    int getRating();

    /**
     * Sets the rating of the hardware component.
     *
     * @param rating The rating to set for the hardware component.
     */
    void setRating(int rating);

}