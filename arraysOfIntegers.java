import java.lang.Math;
public class arraysOfIntegers {
    public static int[] getRandomArray(int n){
        int pos = 0;
        int[] returnArray = new int[n];
        while(pos != n) {
            int repetitionSwitch = 0;
            int ran = (int)(Math.random() * n * 10);
            for (int i = 0; i < returnArray.length; i++) {
                if (returnArray[i] == ran) {
                    repetitionSwitch = 1;
                }
            }
            if (repetitionSwitch == 0) {
                returnArray[pos] = ran;
                pos++;
            }
        }
        return returnArray;
    }

    public static int[] getSortedArray(int n){
        int[] returnArray = new int[n];
        int size  = n;
        for(int i = 0; i < n; i++){
            returnArray[i] = size;
            size--;
        }
        return returnArray;
    }
    public static void main(String[] args){
        int[] runny = getSortedArray(100);
        for(int i = 0; i < runny.length; i++){
            System.out.println(runny[i]);
        }
    }
}
