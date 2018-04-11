import java.util.*;
public class Main {
   
   /**
    * The driver for the Population and genome program
    * @param args The command line arguments
    */
	public static void main(String[] args) {
      //testGenome();
      //testPopulation();
      long time = System.currentTimeMillis();
      Population tPop = new Population(100, 0.05);
      int i = 0;
      for(i = 0; tPop.mostFit.myFitness != 0 ; i++) {
         tPop.day();
         System.out.println("Day: " + i + " " + tPop.mostFit);
      }
      System.out.println("Run time of " + (System.currentTimeMillis() - time) + "ms");
   }
   
   
   
   public static void testPopulation() {
     Population tPop = new Population(100, 0.05);
     int i = 0;
     for(i = 0; i < 1000; i++) {
        tPop.day();
        System.out.println("Iteration: " + i + " " + tPop.mostFit);
     }
     System.out.println(tPop.mostFit); 
   }
   public static void testGenome() {
      Genome temp = new Genome(0.4);
      Genome temp2 = new Genome(0.4);
      int i = 0;
      for( i = 0; i < 100; i++){
         temp.mutate();
         
      }
      System.out.println(temp.myGene);
      for( i = 0; i < 100; i++){
         temp2.mutate();
         
      }
      System.out.println(temp2.myGene);
      temp.crossover(temp2);
      
      System.out.println(temp.myGene);
      
      System.out.println(temp2);
      System.out.println(temp);
      
   }
}