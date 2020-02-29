import java.util.Arrays;
import java.util.Scanner;

public class aa {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int numTrees = s.nextInt();
        double aveLength = s.nextInt();
        double aveWidth = s.nextInt();

        double spacing = aveLength/((numTrees/2)-1);
        //System.out.println(spacing);
        int[] trees = new int[numTrees];

        for(int i = 0; i < numTrees; i++){
            trees[i] = s.nextInt();
        }

        Arrays.sort(trees);

        double table[][] = new double[numTrees/2 + 1][numTrees/2 + 1];
        table[0][0] = 0.0;

        //set first row
        for(int j = 1; j < table[0].length; j++){
            double intendedLeft = (j-1) * spacing;
            table[0][j] = table[0][j-1] + calculateDistance(intendedLeft, aveWidth, trees[j-1], false);
        }

        //set first column
        for(int i = 1; i < table.length; i++){
            double intendedRight = (i-1) * spacing;
            table[i][0] = table[i-1][0] + calculateDistance(intendedRight, aveWidth, trees[i-1], true);
        }
        
        for(int i = 1; i < table.length; i++){
            for(int j = 1; j < table[0].length; j++){
                //current tree is the ith + jth tree
                double intendedLeft = (j-1) * spacing;
                double intendedRight = (i-1) * spacing;
                double prevWasLeft = table[i-1][j] + calculateDistance(intendedRight, aveWidth, trees[i+j-1], true);
                double prevWasRight = table[i][j-1] + calculateDistance(intendedLeft, aveWidth, trees[i+j-1], false);
                table[i][j] = Math.min(prevWasLeft, prevWasRight);
            }
        }

        double output = table[numTrees/2][numTrees/2];
        System.out.println(String.format("%.10f", output));

        // System.out.println(String.format("Spacing: %d", spacing));
         //printTable(table, 0, table.length);

        s.close();
    }

    public static double calculateDistance(double intendedLocation, double aveWidth, int currentTreeLocation, boolean moveAcross){
        if(moveAcross != true){ //don't move to right side
            return Math.abs(intendedLocation - currentTreeLocation);
        } else { //move to right side
            double aveLength = Math.abs(intendedLocation - currentTreeLocation);
            return Math.sqrt(Math.pow(aveLength, 2) + Math.pow(aveWidth, 2));
        }
    }

    public static void printTable(double[][] table, int startCol, int endColumn){
        for(int i = 0; i < table.length; i++){
            String line = "";
            for(int j = startCol; j < endColumn ; j++){
                if(j != table[0].length - 1) {line += table[i][j] + " ";}
                else {line += table[i][j];}
            }
            System.out.println(line);
        }
    }
}