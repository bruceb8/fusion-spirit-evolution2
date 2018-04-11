// Other sources I used, oracle documentation.

import java.util.*;
public class Genome implements Comparable<Genome>{
   final char[] testChar = {'A', 'B', 'C', 'D', 'E', 'F', 
        'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q' 
      , 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '-', '\''};//length is 29
   final String target =  "CHRISTOPHER PAUL MARRIOTT";
   Random myRand = new Random();
   String myGene;
   double myMutRate;
   int myFitness;
   
   Genome (double mutationRate) {
      while (mutationRate >= 1) {
            mutationRate-= 1;
         }
      myMutRate = mutationRate;
      myGene = "A";
      fitness();
   }
   
   Genome (Genome gene) {
      myGene = gene.myGene;
      myMutRate = gene.myMutRate;
      myFitness = gene.myFitness;
   }
   
   void mutate(){
      //A string "dude" has 1d2u3d4e5 different spaces of inserstion
      //so any sort of insertion has to be on the string length unmodified
      // it should be dude = length 4  0d1u2d3e4
      double chance = myRand.nextDouble();
      //System.out.println(chance);
      int place = 0;
      int geneLength = myGene.length();
      char letter;
      //mutation for adding character
      if (chance <= myMutRate) {
         place = myRand.nextInt(geneLength+1);
         letter = testChar[myRand.nextInt(29)];
         
         myGene = myGene.substring(0,place) + letter + myGene.substring(place, geneLength);
         geneLength++;
      }
      //System.out.println(myGene);
      chance = myRand.nextDouble();

      //mutation for taking out a character
      
      if (chance <= myMutRate && geneLength >= 2) {
         place = myRand.nextInt(geneLength);
         myGene = myGene.substring(0, place) + myGene.substring(place + 1);
         geneLength--;
      }
      //System.out.println(myGene);
      //change each charcter mutation
      int i;
      for( i = 0; i < geneLength; i++) {
         chance = myRand.nextDouble();
         if(chance <= myMutRate) {
             letter = testChar[myRand.nextInt(29)];
             myGene = myGene.substring(0,i) + letter + myGene.substring(i + 1, geneLength);
         }
         
      }
      fitness();
         
   }
   
   void crossover(Genome other) {
      int cross = myRand.nextInt(2);
      int i = 0;
      int length = myGene.length();
      String temp = "";
      for( i = 0; i < length; i++) {
         if (cross == 1) {
            if( i >= myGene.length() ) {
               break;
            }
            temp = temp + myGene.charAt(i);
            
         } else {
            if( i >= other.myGene.length() ) {
               break;
            }
            temp = temp + other.myGene.charAt(i);
            
         }
         cross = myRand.nextInt(2);
      }
      myGene = temp;
      fitness();     
   }
   
   
   public Integer fitness() {
      int n = myGene.length();
      int m = target.length();
      
      int l = Math.max(n,m);
      int min = Math.min(n,m);
      int f = Math.abs(m - n);
      f = f * 2;
      
      int i = 0;
      for (i = 0; i < min; i ++) {
         if(myGene.charAt(i) != target.charAt(i)) {
            f++;
         }
      }
      
      myFitness = f;
      return f;
      
   }
   
   public Integer levenSshteinFitness() {
      int n = myGene.length();
      int m = target.length();
      int[][] D = new int[n+1][m+1];
      int i = 0;
      int j = 0;
      
      for(i = 0; i < n; i++) {
         D[i][0] = i;
      }
      for(j = 0; j < m; j++) {
         D[0][j] = j;
      }
      
      for( i = 1; i <= n; i++) {
         for(j = 1; j <= m; j++) {
             if(myGene.charAt(i-1) == target.charAt(j-1)){
               D[i][j] = D[i-1][j-1];
             } else {
                D[i][j] = Math.min(D[i-1][j] + 1, Math.min(D[i][j-1] + 1, D[i-1][j-1] + 1));
             }
          }
       }
      
      myFitness = D[n][m] + (Math.abs(n-m) + 1)/2;
      return myFitness;
      
      
      
   }
   
   public int compareTo(final Genome theGenome) {
      return (myFitness - theGenome.myFitness);
   }
      
   public String toString() {
      return "Gene: " + myGene + " Fitness: " + myFitness;
      
      
   }
      
   
}
   

