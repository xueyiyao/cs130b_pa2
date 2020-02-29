import java.util.ArrayList;
import java.util.Scanner;

public class gc {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int numTests = s.nextInt();

        while(numTests != 0){

            int numParties = s.nextInt();
            ArrayList<ArrayList<Integer>> map = new ArrayList<>();

            for(int i = 0; i < numParties; i++){
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(s.nextInt());
                temp.add(s.nextInt());
                map.add(temp);
            }

            double[][] table = new double[numParties][151];
            double maxVal = 0.0;

            for(int i = 0; i < table.length; i++){
                //if(i-1 >=0) table[i][0] = Math.max(map.get(i).get(1)/100.0, table[i-1][0]);
                //else table[i][0] = map.get(i).get(1)/100.0;
                table[i][map.get(i).get(0)] = map.get(i).get(1) / 100.0;
            }

            for(int i = 0; i < table.length; i++){
                for(int j = 1; j < table[0].length; j++){
                    ArrayList<Integer> item = map.get(i);
                    if(j-item.get(0) >= 0 && i-1 >= 0) {
                        if (table[i-1][j] != 0.0 || (item.get(1)/100.0) * table[i-1][j-item.get(0)] != 0.0) {
                            double temp = Math.max((item.get(1)/100.0) * table[i-1][j-item.get(0)], table[i-1][j]);
                            table[i][j] = Math.max(table[i][j] ,temp);
                        }  
                    }
                    if(j > 75 && table[i][j] > maxVal) maxVal = table[i][j];
                }
            }

            //printTable(table, 0, 19);

            // for(int i = 1; i < table.length; i++){
            //     for(int j = 0; j < table[0].length; j++){
            //         ArrayList<Integer> item = map.get(i-1);
            //         //take max of either taking the item, or not taking the item
            //         if(j-item.get(0) >= 0) {
            //             //if not taking current item returns 0
            //             if(table[i-1][j-item.get(0)] == 0.0 && j == item.get(0)){
            //                 table[i][j] = item.get(1);
            //             } else {
            //                 table[i][j] = Math.max(table[i-1][j], table[i-1][j-item.get(0)] * item.get(1)/100.0);
            //             }
            //         } else {
            //             table[i][j] = table[i-1][j];
            //         }

            //         if(j > 75 && table[i][j] > maxVal){
            //             maxVal = table[i][j];
            //         }
            //     }
            // }

            //printTable(table, 0, 19);

            String str = String.format("%.6f", maxVal);
            System.out.println(str);

            numTests--;
        }

        s.close();
    }

    public static void print(ArrayList<ArrayList<Integer>> map){
        for(int i = 0; i < map.size(); i++){
            System.out.println(map.get(i).get(0) + " " + map.get(i).get(1));
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

// ArrayList<Integer> item = map.get(i-1);
            //         if(j-item.get(0) >= 0){
            //             double previousTaken = table[i-1][j-item.get(0)];
            //             double previousNotTaken = table[i-1][j];
            //             if(previousTaken == 0.0 && j == item.get(0)){
            //                 table[i][j] = item.get(1);
            //                 if(j > 75 && table[i][j] > maxVal){
            //                     maxVal = table[i][j];
            //                 }
            //                 continue;
            //             }
            //             table[i][j] = Math.max(previousTaken * (item.get(1)/100.0), previousNotTaken);
            //         } else {
            //             table[i][j] = table[i-1][j];
            //         }

            //         if(j > 75 && table[i][j] > maxVal){
            //             maxVal = table[i][j];
            //         }