package org.webapp.Models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class BuilderForm {

    public BuilderForm(double cpuWeight, double gpuWeight, int tax, int budget){
        this.cpuWeight = cpuWeight;
        this.gpuWeight = gpuWeight;
        this.tax = tax;
        this.budget = budget;
    }

    public double getCpuWeight() {
        return cpuWeight;
    }

    public void setCpuWeight(double cpuWeight) {
        this.cpuWeight = cpuWeight;
    }

    public double getGpuWeight() {
        return gpuWeight;
    }

    public void setGpuWeight(double gpuWeight) {
        this.gpuWeight = gpuWeight;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    @Pattern(regexp = "\\d+",message = "Budget cannot be empty")
    private int budget;

    @Pattern(regexp = "\\d+",message = "CPU Weight cannot be empty")
    private double cpuWeight;

    @Pattern(regexp = "\\d+",message = "GPU Weight cannot be empty")
    private double gpuWeight;

    @NotNull(message = "Tax cannot be empty")
    private int tax;
}
