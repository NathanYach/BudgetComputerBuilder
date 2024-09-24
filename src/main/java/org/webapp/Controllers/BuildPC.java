/**
 * The BuildPC class facilitates the process of selecting the best GPU and CPU components for building a PC within a specified budget.
 * It compares the benchmarks of available components and selects the ones with the highest performance.
 * This class includes methods to retrieve the best GPU and CPU components based on the provided budget,
 * calculate leftover budget after component selection, and find better components within the remaining budget.
 * It also defines a generic method for comparing benchmarks of hardware components.
 *
 * @author Nathan Yach
 */
package org.webapp.Controllers;

import org.webapp.Models.CPU;
import org.webapp.Models.GPU;
import org.webapp.Models.Hardware;
import org.webapp.Models.HardwareList;

import java.util.List;
import java.util.Map;

public class BuildPC {
    //TODO: Use leftover value to recalculate new product based on weight to utilize full budget

    //The catalog containing GPU components organized by their price.
    Map<Integer, List<GPU>> gpuCatalog;

    ///The budget allocated for building the PC.
    private int budget;

    //The weight assigned to GPUs in the PC building algorithm.
    private double gpuWeight;

    //The remaining budget after selecting GPU and CPU components.
    private int leftoverBudget;

    //The weight assigned to CPUs in the PC building algorithm.
    private double cpuWeight;

    //The catalog containing CPU components organized by their price.
    Map<Integer, List<CPU>> cpuCatalog;

    double tax;

    /**
     * Initializes a BuildPC object with the provided GPU catalog, CPU catalog, budget, GPU weight, and CPU weight.
     *
     * @param gpuCatalog The catalog containing GPU components organized by their price.
     * @param cpuCatalog The catalog containing CPU components organized by their price.
     * @param budget The budget allocated for building the PC.
     * @param gpuWeight The weight assigned to GPUs in the PC building algorithm.
     * @param cpuWeight The weight assigned to CPUs in the PC building algorithm.
     */
    public BuildPC(Map<Integer, List<GPU>> gpuCatalog, Map<Integer, List<CPU>> cpuCatalog, int budget, double gpuWeight, double cpuWeight,double tax){
       this.cpuCatalog =  cpuCatalog;
       this.gpuCatalog = gpuCatalog;
       this.budget = budget;
       this.gpuWeight = gpuWeight;
       this.cpuWeight = cpuWeight;
       this.tax = (tax/100) +1;
    }

    /**
     * Builds a PC by selecting the best GPU and CPU components within the allocated budget.
     *
     * @return A list of hardware components representing the built PC.
     */
    public HardwareList build(){

        int gpuPrice = (int) Math.round((gpuWeight*budget)/tax);
        int cpuPrice = (int) Math.round((cpuWeight*budget)/tax);
        //TODO NULL ERROR

        GPU betterGPU = null;
        CPU betterCPU = null;
        HardwareList hardwareList = new HardwareList();
        GPU bestGPU = getBestComponent(gpuPrice,gpuCatalog);
        CPU bestCPU = getBestComponent(cpuPrice, cpuCatalog);
        if (bestCPU != null && bestGPU != null){

            leftoverBudget += cpuPrice - bestCPU.getPrice();
            leftoverBudget += gpuPrice - bestGPU.getPrice();
            betterGPU = findBetterComponent((bestGPU.getPrice()+leftoverBudget),bestGPU);
            betterCPU = findBetterComponent((bestCPU.getPrice()+leftoverBudget), bestCPU);
            hardwareList.setGpu(bestGPU);
            hardwareList.setCpu(bestCPU);
            hardwareList.setLeftover(leftoverBudget);
            System.out.println("Selected GPU: " + bestGPU.toString());
            System.out.println("Selected CPU: " + bestCPU.toString());
            System.out.println("Leftover Budget: " + leftoverBudget);

            if (betterGPU != null) {
                bestGPU = betterGPU;
            }

            if (betterCPU != null) {
                bestCPU = betterCPU;
            }
            return hardwareList;
        }

        return hardwareList;
    }
    /**
     * Compares the benchmark of hardware components in the provided list and returns the one with the highest benchmark.
     *
     * @param <T> The type of hardware component.
     * @param hardwareList The list of hardware components to compare.
     * @return The hardware component with the highest benchmark, or null if the list is empty.
     */
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
    /**
     * Retrieves the best hardware component from the catalog based on the specified price.
     * @param <T> The type of hardware component.
     * @param componentPrice The price of the hardware component to search for.
     * @param catalog The catalog containing hardware components organized by their price.
     * @return The best hardware component found within the specified price range, or null if none is found.
     */
    public <T extends Hardware> T getBestComponent(int componentPrice, Map<Integer,List<T>> catalog){
        T bestComponent = null;
        Boolean priceFound = false;
        while (!priceFound){
            try {

                if (catalog.containsKey(componentPrice)){
                    bestComponent = catalog.get(componentPrice).get(0);
                    for(int x = componentPrice; x>= 0; ){
                        x--;
                        List<T> list = (List<T>) catalog.get(componentPrice);
                        if (list.size() > 1){
                            bestComponent = compareBenchmark(list);
                        }
                        if (catalog.containsKey(x)){
                            List<T> listNext = catalog.get(x);
                            T componentNext = compareBenchmark
                                    (listNext);
                            if (componentNext.getBenchmark() > bestComponent.getBenchmark()){
                                componentPrice = componentNext.getPrice();
                                bestComponent = componentNext;
                            }

                        }

                        //}
                    }

                    priceFound = true;

                }
                else{
                    if (componentPrice>0){
                        componentPrice--;
                    }else {
                        System.out.println("CPU within price range could not be found");
                        return bestComponent;
                    }

                }

            }catch (Exception e){
                System.out.println(e);
                System.out.println("CPU within price range could not be found");
            }
        }
        return bestComponent;
    }

    /**
     * Finds a better hardware component within the provided price range based on the benchmark comparison.
     * @param <T> The type of hardware component.
     * @param price The price range to search within.
     * @param bestComponent The current best hardware component.
     * @return The better hardware component found within the specified price range, or null if none is found.
     */
    public <T extends Hardware> T findBetterComponent(int price, T bestComponent) {
        if (price <= 0) {
            return null;
        }

        T betterComponent = bestComponent instanceof GPU ? (T) getBestComponent(price, gpuCatalog) : (T) getBestComponent(price, cpuCatalog);
        if (betterComponent != null && betterComponent.getBenchmark() > bestComponent.getBenchmark()) {
            int betterComponentPrice = betterComponent.getPrice();
            leftoverBudget = price - betterComponentPrice;
            return betterComponent;
        } else {
            return null;
        }
    }


}
