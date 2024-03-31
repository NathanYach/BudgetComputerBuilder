package org.Controllers;

import org.Models.CPU;
import org.Models.GPU;

import java.util.List;
import java.util.Map;

public class buildPC {
    //TODO: Use leftover value to recalculate new product based on weight to utilize full budget

    Map<Integer, List<GPU>> gpuCatalog;
    Map<Double, List<CPU>> cpuCatalog;
    public buildPC(Map<Integer, List<GPU>> gpuCatalog, Map<Double, List<CPU>> cpuCatalog){
       this.cpuCatalog =  cpuCatalog;
       this.gpuCatalog = gpuCatalog;
    }
    public void getBestGpu(int budget, double weight){
        int gpuPrice = (int) ((weight)*budget);
        System.out.println(gpuPrice);
        Boolean priceFound = false;
        while (!priceFound){
            try {
                if (gpuCatalog.containsKey(gpuPrice)){
                    priceFound = true;
                    System.out.println(gpuCatalog.get(gpuPrice));
                    int leftover = (int) ((weight)*budget) - gpuPrice;
                    if (leftover > 0){
                        System.out.println("left over budget: "+leftover);
                    }

                }
                else{
                    if (gpuPrice>0){
                        gpuPrice--;
                    }else {
                        System.out.println("GPU within price range could not be found");
                        return;
                    }

                }
            }catch (Exception e){
                System.out.println("GPU within price range could not be found");
            }
        }



    }
}
