package org.Controllers;

import org.Models.CPU;
import org.Models.GPU;
import org.Models.Hardware;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class BuildPC {
    //TODO: Use leftover value to recalculate new product based on weight to utilize full budget

    Map<Integer, List<GPU>> gpuCatalog;
    private int budget;
    private double gpuWeight;

    private int leftoverBudget;
    private double cpuWeight;
    Map<Integer, List<CPU>> cpuCatalog;
    public BuildPC(Map<Integer, List<GPU>> gpuCatalog, Map<Integer, List<CPU>> cpuCatalog, int budget, double gpuWeight, double cpuWeight){
       this.cpuCatalog =  cpuCatalog;
       this.gpuCatalog = gpuCatalog;
        this.budget = budget;
        this.gpuWeight = gpuWeight;
        this.cpuWeight = cpuWeight;
    }
    public GPU getBestGPU(int gpuPrice){

        Boolean priceFound = false;
        GPU bestGPU = null;
        while (!priceFound){
            try {

                if (gpuCatalog.containsKey(gpuPrice)){

                    bestGPU = gpuCatalog.get(gpuPrice).get(0);
                    for(int x = gpuPrice; x>= 0; ){
                        x--;
                        List<GPU> gpuList = gpuCatalog.get(gpuPrice);
                        if (gpuList.size() > 1){
                            bestGPU = compareBenchmark(gpuList);
                        }
                        if (gpuCatalog.containsKey(x)){
                            List<GPU> gpuListNext = gpuCatalog.get(x);
                            GPU gpuNext = compareBenchmark(gpuListNext);
                            if (gpuNext.getBenchmark() > bestGPU.getBenchmark()){
                                gpuPrice = gpuNext.getPrice();
                                bestGPU = gpuNext;
                            }

                        }

                        //}
                    }


                    priceFound = true;

                }
                else{
                    if (gpuPrice>0){
                        gpuPrice--;
                    }else {
                        System.out.println("GPU within price range could not be found");
                        return bestGPU;
                    }

                }

            }catch (Exception e){
                System.out.println(e);
                System.out.println("GPU within price range could not be found");
            }
        }
       // int leftover = (int) ((gpuWeight)*budget) - gpuPrice;
       // if (leftover > 0){
         //   leftoverBudget += leftover;
         //   System.out.println("left over budget GPU: "+leftover);
       // }
        return bestGPU;
    }

    private <T extends Hardware> T compareBenchmark(List<T> hardwareList) {
        if (hardwareList.isEmpty()) {
            return null;
        }

        T bestHardware = hardwareList.get(0);
        for (int i = 1; i < hardwareList.size(); i++) {
            T hardware = hardwareList.get(i);
            if (hardware.getBenchmark() > bestHardware.getBenchmark()) {
                bestHardware = hardware;
            }
        }
        return bestHardware;
    }

    public CPU getBestCPU(int cpuPrice){

        CPU bestCPU = null;
        Boolean priceFound = false;
        while (!priceFound){
            try {

                if (cpuCatalog.containsKey(cpuPrice)){
                    bestCPU = cpuCatalog.get(cpuPrice).get(0);
                    for(int x = cpuPrice; x>= 0; ){
                        x--;
                        List<CPU> cpuList = cpuCatalog.get(cpuPrice);
                        if (cpuList.size() > 1){
                            bestCPU = compareBenchmark(cpuList);
                        }
                        if (cpuCatalog.containsKey(x)){
                            List<CPU> cpuListNext = cpuCatalog.get(x);
                            CPU cpuNext = compareBenchmark
                                    (cpuListNext);
                            if (cpuNext.getBenchmark() > bestCPU.getBenchmark()){
                                cpuPrice = cpuNext.getPrice();
                                bestCPU = cpuNext;
                            }

                        }

                        //}
                    }

                    priceFound = true;

                }
                else{
                    if (cpuPrice>0){
                        cpuPrice--;
                    }else {
                        System.out.println("CPU within price range could not be found");
                        return bestCPU;
                    }

                }

            }catch (Exception e){
                System.out.println(e);
                System.out.println("CPU within price range could not be found");
            }
        }
        //int leftover = (int) ((cpuWeight)*budget) - cpuPrice;
        //if (leftover > 0){
            //leftoverBudget += leftover;
         //   System.out.println("left over budget CPU: "+leftover);
       // }
        return bestCPU;
    }
    public List<Hardware> build(){

        int gpuPrice = (int) (gpuWeight*budget);
        int cpuPrice = (int) (cpuWeight*budget);

        GPU bestGPU = getBestGPU(gpuPrice);
        leftoverBudget += gpuPrice - bestGPU.getPrice();
        CPU bestCPU = getBestCPU(cpuPrice);
        leftoverBudget += cpuPrice - bestCPU.getPrice();

        GPU betterGPU = findBetterComponent(gpuWeight,(bestGPU.getPrice()+leftoverBudget), bestGPU);
        CPU betterCPU = findBetterComponent(cpuWeight,(bestCPU.getPrice()+leftoverBudget), bestCPU);

        if (betterGPU != null) {
            bestGPU = betterGPU;
        }

        if (betterCPU != null) {
            bestCPU = betterCPU;
        }

        System.out.println("Selected GPU: " + bestGPU.toString());
        System.out.println("Selected CPU: " + bestCPU.toString());
        System.out.println("Leftover Budget: " + leftoverBudget);
        return null;
    }

    public <T extends Hardware> T findBetterComponent(double weight,int price, T bestComponent) {
        if (price <= 0) {
            return null;
        }

        T betterComponent = bestComponent instanceof GPU ? (T) getBestGPU(price) : (T) getBestCPU(price);
        if (betterComponent != null && betterComponent.getBenchmark() > bestComponent.getBenchmark()) {
            int betterComponentPrice = betterComponent.getPrice();
            leftoverBudget = price - betterComponentPrice;
            return betterComponent;
        } else {
            return null;
        }
    }


}
