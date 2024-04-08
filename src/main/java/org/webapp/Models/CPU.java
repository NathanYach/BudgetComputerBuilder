package org.webapp.Models;


import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * Represents a Central Processing Unit (CPU) hardware component.
 * Implements the Hardware interface.
 */
@Table(name = "CPUs")
@Entity
@EnableAutoConfiguration
public class CPU implements Hardware {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    // Name of the CPU
    private String name;
    // Link to CPU details
    private String cpuLink;
    // Link to purchase the CPU
    private String buyLink;
    // Price of the CPU
    private int price;
    // Rating of the CPU
    private int rating;
    // Benchmark score of the CPU
    private int benchmark;
    // Power consumption of the CPU
    private String power;

    private String type = "CPU";

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    /**
     * Retrieves the power consumption of the CPU.
     *
     * @return The power consumption of the CPU.
     */
    public String getPower() {
        return power;
    }

    /**
     * Sets the power consumption of the CPU.
     *
     * @param power The power consumption of the CPU.
     */
    public void setPower(String power) {
        this.power = power;
    }

    /**
     * Retrieves the name of the CPU.
     *
     * @return The name of the CPU.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the CPU.
     *
     * @param name The name of the CPU.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the link to detailed information about the CPU.
     *
     * @return The link to CPU details.
     */
    public String getCpuLink() {
        return cpuLink;
    }

    /**
     * Sets the link to detailed information about the CPU.
     *
     * @param cpuLink The link to CPU details.
     */
    public void setCpuLink(String cpuLink) {
        this.cpuLink = cpuLink;
    }

    /**
     * Retrieves the link to purchase the CPU.
     *
     * @return The link to purchase the CPU.
     */
    public String getBuyLink() {
        return buyLink;
    }

    /**
     * Sets the link to purchase the CPU.
     *
     * @param buyLink The link to purchase the CPU.
     */
    public void setBuyLink(String buyLink) {
        this.buyLink = buyLink;
    }

    /**
     * Retrieves the price of the CPU.
     *
     * @return The price of the CPU.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price of the CPU.
     *
     * @param price The price of the CPU.
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Retrieves the rating of the CPU.
     *
     * @return The rating of the CPU.
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets the rating of the CPU.
     *
     * @param rating The rating of the CPU.
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Retrieves the benchmark score of the CPU.
     *
     * @return The benchmark score of the CPU.
     */
    public int getBenchmark() {
        return benchmark;
    }

    /**
     * Sets the benchmark score of the CPU.
     *
     * @param benchmark The benchmark score of the CPU.
     */
    public void setBenchmark(int benchmark) {
        this.benchmark = benchmark;
    }

    /**
     * Generates a string representation of the CPU object.
     *
     * @return A string representation of the CPU object.
     */
    @Override
    public String toString() {
        return
                name +
                ", price=" + price +
                ", cpuLink='" + cpuLink +
                ", power='" + power +
                ", benchmark=" + benchmark +
                ", buyLink='" + buyLink +
                ", rating=" + rating;
    }
}
