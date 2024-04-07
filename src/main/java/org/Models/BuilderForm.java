package org.Models;

public class BuilderForm {

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

    private int budget;
    private double cpuWeight;

    private double gpuWeight;

    private int tax;
}
