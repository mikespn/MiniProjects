package org.project1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import java.util.Scanner;

/**
 * A small app that will read from a file some numbers(1-49) till it reach the number(-1)
 * then populates an array with these numbers and sorts them.
 *After that the app produces all the possible unique 6-number combinations and filters them to some
 * specific filters. The final combinations are printed in a txt file.
 *
 */
public class Project1 {
    public static void main(String[] args) {
        int[] arr = new int[0];

        arr = Project1.FileReaderPopulateArray(arr);
        Arrays.sort(arr);

        sixIntCombinations(arr);
    }

    /**
     * Reads the file with the number. The app will read till it reaches -1.
     * @param arr an empty array that the numbers will be putted
     * @return The array filled with the numbers.
     */
    public static int[] FileReaderPopulateArray(int[] arr) {
        try {
            int[] helperArr = new int[50];
            int i = 0;
            Scanner in = new Scanner(new File("C:\\tmp\\test01.txt"));

            while (in.hasNextInt()) {
                int num = in.nextInt();
                if (num == -1) {
                    break;
                }
                helperArr[i] = num;
                i++;
            }

            arr = Arrays.copyOf(helperArr, i);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    /**
     * A method to print the items of an array in a line
     * @param arr
     */
    public static void printArray(int[] arr) {
        for (int item : arr) {
            System.out.println(item + " ");
        }
    }

    /**
     * A method that will produce all the possible 6-number combination from an array of an unknown length.
     * Then it will continue by filtering it using the static methods
     * isSameEnding()
     * hasLessThan2Contiguous()
     * hasLessThan4EvenOr4odd()
     * hasLessThan4inTheSame10s()
     * and finally print the results to a txt file.
     * @param arr array of ints
     *
     */
    public static void sixIntCombinations(int[] arr) {
        int l = arr.length;
        int[] arr2 = new int[6];

        for (int i = 0; i < l - 5; i++) {
            arr2[0] = arr[i];
            for (int j = i + 1; j < l - 4; j++) {
                arr2[1] = arr[j];
                for (int k = j + 1; k < l - 3; k++) {
                    arr2[2] = arr[k];
                    for (int m = k + 1; m < l - 2; m++) {
                        arr2[3] = arr[m];
                        for (int n = m + 1; n < l - 1; n++) {
                            arr2[4] = arr[n];
                            for (int o = n + 1; o < l; o++) {
                                arr2[5] = arr[o];
                                if (isSameEnding(arr2) && hasLessThan2Contiguous(arr2) &&
                                        hasLessThan4EvenOr4odd(arr2) && hasLessThan4inTheSame10s(arr2)) {
                                    printArrayToFile(arr2);
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    /**
     * A filter method that checks an array for the number of even and odd numbers in it.
     * @param arr array of ints
     * @return True if there are less than or equal to 4 even and 4 odd numbers
     * @return False if there are more than 4 even or odd numbers.
     */
    public static boolean hasLessThan4EvenOr4odd (int[] arr){
        int k = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] % 2 == 0){
                k++;
            }
        }
        if(k >= 5){
            return false;
        }else if(k <= 1){
            return false;
        }else{
            return true;
        }
    }

    /**
     * A filter method that checks an array for the continuity of its numbers.
     * @param arr array of ints
     * @return True if there are 2 or less continuous numbers
     * @return False if there are more than 2 continuous numbers
     *
     */
    public static boolean hasLessThan2Contiguous(int[] arr){
        for(int i = 0; i < arr.length -2; i++){
            if(arr[i + 1] - arr[i] == 1){
                if(arr[i + 2] - arr[i + 1] == 1){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * A filter method to check if the module of the numbers in the array are the same.
     * @param arr array of ints
     * @return True if there are 3 or less numbers of the same module
     * @return False if there are more than 3 numbers with the same module
     */
    public static boolean isSameEnding(int[] arr){
        int k = 0;
        int[] helper = new int[6];
        for(int i = 0; i < arr.length; i++){
            helper[i] = arr[i]%10;
        }
        for(int i = 0; i < arr.length -2; i++){
            for(int j = i+1; j < arr.length; j++){
                if(helper[i] == helper[j]){
                    k++;
                }
            }
            if(k >= 4){
                return false;
            }
        }
        return true;
    }

    /**
     * A filter method that checks if the numbers are on the same
     * @param arr array of ints
     * @return True if there are less than 4 numbers in the same "tens"
     * @return False if there are more or equal to 4 numbers in the same "tens"
     */
    public static boolean hasLessThan4inTheSame10s(int[] arr) {
        int[] helper = new int[6];
        for (int i = 0; i < arr.length; i++) {
            helper[i] = arr[i] / 10;
        }

        for (int i = 0; i < arr.length - 3; i++) {
            if (helper[i + 1] == helper[i] && helper[i + 2] == helper[i + 1]
                    && helper[i + 3] == helper[i + 2]) {
                return false;
            }
        }
        return true;
    }

    /**
     * A method to print an array of ints into a file.
     * Note that you will have to input your preferable path.
     * @param arr Array of ints
     */
    public static void printArrayToFile(int[] arr){
        String filepath = "C:\\tmp\\number.txt";

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, true))){
            for(int number : arr){
                writer.write(number + " ");
            }
            writer.newLine();

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}


