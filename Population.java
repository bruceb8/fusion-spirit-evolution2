import java.util.*;
public class Population {
   Genome mostFit;
   Random myRand = new Random();
   int myLength;
   ArrayList<Genome> myPop = new ArrayList<Genome>();
   
   Population(int numGenomes, double mutationRate) {
      int i = 0;
      myLength = numGenomes;
      for(i = 0; i < numGenomes; i++) {
         myPop.add(new Genome(mutationRate));
      }
      mostFit = myPop.get(0);
   }
   
   void day() {
      int i = 0;
      //sorts the population
      Collections.sort(myPop);
      
      //gets the most fit member of the population
      mostFit = myPop.get(0);
      Genome tempG;
      int chance = 0;
      for(i = myLength/2; i < myLength; i++) {
            chance = myRand.nextInt(2);
            tempG = new Genome(myPop.get(myRand.nextInt(i)));
            if(chance == 1) {
               tempG.mutate();
               myPop.set(i, tempG);
            }else{
               tempG.crossover(myPop.get(myRand.nextInt(i)));
               myPop.set(i, tempG);
            }
         }
      }
   
   
   
}