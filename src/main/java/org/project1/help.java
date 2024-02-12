package org.project1;

public class help {
    public static void main(String[] args) {
        int i = 16;
        int j = 25;
        int k = 19;
        int m = 6;

        System.out.println("The results are" +k/10 +" "+ j/10 + " " +  i/10+ " " + m/10);
    }

    public static boolean isSameEnding(int[] arr){

        int[] helper = new int[6];
        for(int i = 0; i < arr.length; i++){
            helper[i] = arr[i]%10;
        }
        for(int i = 0; i < arr.length -2; i++){
            int k = 0;
            for(int j = i+1; j < arr.length; j++){

                if(helper[j] ==  helper[i]){
                    k++;
                }
                if(k >= 4){
                    return false;
                }
            }
        }
      return true;
    }
}
