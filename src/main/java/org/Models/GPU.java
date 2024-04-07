package org.Models;


/**
 * Represents a Graphics Processing Unit (GPU) hardware component.
 * Implements the Hardware interface.
 */
public class GPU implements Hardware {

    private int price;          // Price of the GPU
    private String name;        // Name of the GPU
    private String power;       // Power consumption of the GPU
    private int benchmark;      // Benchmark score of the GPU
    private String vram;        // Video RAM (VRAM) of the GPU
    private int rating;         // Rating of the GPU
    private String gpuLink;        // Link to GPU details

    /**
     * Retrieves the price of the GPU.
     *
     * @return The price of the GPU.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price of the GPU.
     *
     * @param price The price of the GPU.
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Retrieves the name of the GPU.
     *
     * @return The name of the GPU.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the GPU.
     *
     * @param name The name of the GPU.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the power consumption of the GPU.
     *
     * @return The power consumption of the GPU.
     */
    public String getPower() {
        return power;
    }

    /**
     * Sets the power consumption of the GPU.
     *
     * @param power The power consumption of the GPU.
     */
    public void setPower(String power) {
        this.power = power;
    }

    /**
     * Retrieves the benchmark score of the GPU.
     *
     * @return The benchmark score of the GPU.
     */
    public int getBenchmark() {
        return benchmark;
    }

    /**
     * Sets the benchmark score of the GPU.
     *
     * @param benchmark The benchmark score of the GPU.
     */
    public void setBenchmark(int benchmark) {
        this.benchmark = benchmark;
    }

    /**
     * Retrieves the Video RAM (VRAM) of the GPU.
     *
     * @return The Video RAM (VRAM) of the GPU.
     */
    public String getVram() {
        return vram;
    }

    /**
     * Sets the Video RAM (VRAM) of the GPU.
     *
     * @param vram The Video RAM (VRAM) of the GPU.
     */
    public void setVram(String vram) {
        this.vram = vram;
    }

    /**
     * Retrieves the rating of the GPU.
     *
     * @return The rating of the GPU.
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets the rating of the GPU.
     *
     * @param rating The rating of the GPU.
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Retrieves the link to detailed information about the GPU.
     *
     * @return The link to GPU details.
     */
    public String getGpuLink() {
        return gpuLink;
    }

    /**
     * Sets the link to detailed information about the GPU.
     *
     * @param link The link to GPU details.
     */
    public void setLink(String link) {
        this.gpuLink = link;
    }

    /**
     * Generates a string representation of the GPU object.
     *
     * @return A string representation of the GPU object.
     */
    @Override
    public String toString() {
        return
                name +
                ", price=" + price +
                ", link='" + gpuLink +
                ", power='" + power +
                ", benchmark=" + benchmark +
                ", vram='" + vram +
                ", rating=" + rating;
    }
}
