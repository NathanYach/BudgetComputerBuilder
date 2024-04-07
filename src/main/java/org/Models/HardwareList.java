package org.Models;

import org.Controllers.GPUScrapper;

public class HardwareList {


    public GPU getGpu() {
        return gpu;
    }

    public void setGpu(GPU gpu) {
        this.gpu = gpu;
    }

    public CPU getCpu() {
        return cpu;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public int getLeftover() {
        return leftover;
    }

    public void setLeftover(int leftover) {
        this.leftover = leftover;
    }

    private GPU gpu;
    private CPU cpu;
    private int leftover;
}
