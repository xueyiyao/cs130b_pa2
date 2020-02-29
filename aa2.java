import java.util.Arrays;
import java.util.Scanner;
import java.awt.Point;

public class aa2 {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int numTrees = s.nextInt();
        int aveLength = s.nextInt();
        int aveWidth = s.nextInt();

        int spacing = aveLength/((numTrees/2)-1);
        //System.out.println(spacing);
        int[] trees = new int[numTrees];

        for(int i = 0; i < numTrees; i++){
            trees[i] = s.nextInt();
        }
        Arrays.sort(trees);
        
        Point[] treePoints = new Point[numTrees];
        for(int i = 0; i <numTrees; i++){
            treePoints[i] = new Point(0, trees[i]);
        }

        Point[] expectedLeft = new Point[numTrees/2];
        Point[] expectedRight = new Point[numTrees/2];
        for(int i = 0; i < numTrees/2; i++){
            expectedLeft[i] = new Point(0, i*spacing);
            expectedRight[i] = new Point(aveWidth, i*spacing);
        }
         System.out.println(Arrays.toString(expectedLeft));
         System.out.println(Arrays.toString(expectedRight));

        double table[][] = new double[numTrees/2 + 1][numTrees/2 + 1];
        table[0][0] = 0.0;

        for (int i = 1;i < numTrees/2 + 1; i++){
            table[i][0] = table[i-1][0] + calculateDistance(treePoints[i-1], expectedLeft[i-1]);
        }
        for (int j = 1;j < numTrees/2 + 1; j++){
            table[0][j] = table[0][j-1] + calculateDistance(treePoints[j-1], expectedRight[j-1]);
        }
        for (int i = 1; i < numTrees/2 + 1; i++){
            for (int j = 1; j < numTrees/2 + 1; j++){
                table[i][j] = Math.min(table[i-1][j] + calculateDistance(treePoints[i+j-1], expectedLeft[i-1]), 
                                        table[i][j-1] + calculateDistance(treePoints[i+j-1], expectedRight[j-1]));
            }
        }

        double output = table[numTrees/2][numTrees/2];
        System.out.println(String.format("%.10f", output));

        s.close();
    }

    public static double calculateDistance(Point a, Point b){
        double c = Math.sqrt(  (a.getX()-b.getX()) * (a.getX()-b.getX())   +   (a.getY()-b.getY()) * (a.getY()-b.getY())   );
        return c;
    }
}