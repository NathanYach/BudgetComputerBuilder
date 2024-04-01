import org.Controllers.CPUScrapper;
import org.Controllers.GPUScrapper;
import org.Controllers.BuildPC;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

    private static final double cpuWeight = 0.4;
    private static final double gpuWeight = 0.6;
    static int budget;

   public static void main(String[] args) {


       //TODO: ADD TAXES


       menu();

       GPUScrapper GPU = new GPUScrapper();
       GPU.initializeSearch();
       GPU.searchGPUS();
       CPUScrapper CPU = new CPUScrapper();
       CPU.initializeSearch();
       CPU.searchCPUS();

       BuildPC builder =  new BuildPC(GPU.getGpuCatalog(), CPU.getCpuCatalog(),budget,gpuWeight,cpuWeight);
       builder.build();
    }


    private static void menu() {

        while (true){
            Scanner userInput = new Scanner(System.in);
            System.out.print("Please Enter Your Budget: $");
            if(userInput.hasNextInt()){
                budget = userInput.nextInt();
                userInput.close();
                return;
            }
            else{
                System.out.println("Please Enter An Integer");
            }
        }

    }

}
