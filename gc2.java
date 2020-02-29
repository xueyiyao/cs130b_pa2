import java.util.*;

public class gc2 {
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

            double[] table = new double[151];
            table[0] = 1.0;

            for(int i = 0; i < numParties; i++){
                for(int j = 150; j>=map.get(i).get(0); j--){
                    table[j] = Math.max(table[j], table[j-map.get(i).get(0)] * (map.get(i).get(1) / 100.0));
                }
            }

            double maxVal = 0.0;
            for(int i = 76; i < 151; i++){
                if(table[i] > maxVal) { maxVal = table[i]; }
            }

            maxVal *= 100;

            String str = String.format("%.6f", maxVal);
            str = str.contains(".") ? str.replaceAll("0*$","").replaceAll("\\.$","") : str;
            
            if(!str.contains(".")) { str += ".0"; }
            System.out.println(str);

            numTests--;
        }
        s.close();
    }
}