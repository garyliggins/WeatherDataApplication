
package com.kenzie.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Scanner;

// create custom exceptions InvalidWeekException and InvalidDayOfWeekException first





public class Application {
    static final String INPUT_FILENAME = "austin_weather.csv";
    // removes the CSV header from the array of lines
    // DO NOT MODIFY THIS METHOD
    public static String[] removeCSVHeader(String[] originalArray){
        String[] newArray = new String[originalArray.length - 1];

        for (int i = 0, j = 0; i < originalArray.length; i++) {
            if (i != 0) {
                newArray[j++] = originalArray[i];
            }
        }
        return newArray;
    }

    public static String readCSVLines(String filename) throws IOException{
        Path filePath = Path.of(filename);
       return Files.readString(filePath);

    }

    public static String[][] saveToMultidimensionalArray(String csvData, int numRows, int numColumns){
        String[] csvLines = csvData.split("\n");
        String[] csvDataWithoutHeaders = removeCSVHeader(csvLines);
        String[][] arr2D = new String[numRows][numColumns];
        int idx = 0;
        for(int i = 0; i < arr2D.length; i++)
        {
            for(int j = 0; j < arr2D[0].length; j++)
            {
                arr2D[i][j] = csvDataWithoutHeaders[idx];
                idx++;
            }
        }
        // create a multidimensional array with numRows rows and numColumns columns

        // use a nested loop to loop through each line of the CSV to put in the multidimensional array

        // return the multidimensional array
        return arr2D;
    }

    public static String getWeekToSelectFromData(Scanner scanner){
        System.out.print("Enter a week number between 1 and 10 with information you want: ");
        String weekToSelect = scanner.nextLine();
        return weekToSelect;
    }

    public static String getDayToSelectFromData(Scanner scanner){
        System.out.print("Enter a day number between 1 and 7 with information you want: ");
        String dayToSelect = scanner.nextLine();
        return dayToSelect;
    }

    public static void main(String[] args) throws IOException {
        String csvContent = readCSVLines(INPUT_FILENAME);
        String[][] csvData = saveToMultidimensionalArray(csvContent, 10, 7);

        Scanner scanner = new Scanner(System.in);
        String weekToSelect;
        int weekNumberToSelect = 0;

        String dayToSelect;
        int dayNumberToSelect = 0;

        String integerExceptionDayOfWeekMessage = "You have to enter an integer for the day!";
        String integerExceptionWeekMessage = "You have to enter an integer for the week!";

        String invalidNumberExceptionDayOfWeekMessage = "You need to enter a number between 1 and 7 for the day!";
        String invalidNumberExceptionWeekMessage = "You need to enter a number between 1 and 10 for the week!";

        // do not change code in main() above this line


        do {
            // TODO: wrap the code inside the do-part of the do-while loop with a try/catch block

            try{
                weekToSelect = getWeekToSelectFromData(scanner);
                weekNumberToSelect = Integer.parseInt(weekToSelect);

                if (weekNumberToSelect < 1  || weekNumberToSelect > 10) {
                    throw new InvalidWeekException(invalidNumberExceptionWeekMessage);
                }
                // when the exception is caught, print the message from variable integerExceptionWeekMessage above

            }
            catch (InvalidWeekException e ) {
                System.out.println(e.getMessage());
                return;

            }
            catch (NumberFormatException e)
            {
                System.out.println(integerExceptionWeekMessage);

            }
        }
        while(weekNumberToSelect == 0);




        // TODO: Throw an InvalidWeekException if the week entered is greater than 10 or less than 1.
        // Use a try/catch block here to catch the exception and print the message before adding an empty return statement.
        // When throwing the exception, pass in the message from variable invalidNumberExceptionWeekMessage.

        do {
            // TODO: wrap the code inside the do-part of the do-while loop with a try/catch block
            // when the exception is caught, print the message from variable integerExceptionDayOfWeekMessage above
            try {
                dayToSelect = getDayToSelectFromData(scanner);
                dayNumberToSelect = Integer.parseInt(dayToSelect);

                if (dayNumberToSelect > 7 || dayNumberToSelect < 1) {
                    throw new InvalidDayOfWeekException(invalidNumberExceptionDayOfWeekMessage);
                }

            }
            catch (InvalidDayOfWeekException e ) {
                System.out.println(e.getMessage());
                return;

            }
            catch (NumberFormatException e) {
                System.out.println(integerExceptionDayOfWeekMessage);
            }

        }

        while(dayNumberToSelect == 0);
        // TODO: Throw an InvalidDayOfWeekException if the day of week entered is greater than 7 or less than 1.
        // Use a try/catch block here to catch the exception and print the message before adding an empty return statement.
        // When throwing the exception, pass in the message from variable invalidNumberExceptionDayOfWeekMessage.





        // do not change code in main() below this line

        String specificDayInfo = csvData[weekNumberToSelect - 1][dayNumberToSelect - 1];

        String[] dayInfoArray = specificDayInfo.split(",");
        System.out.println("Date: " + dayInfoArray[0].strip());
        System.out.println("Day Of Week: " + dayInfoArray[1].strip());
        System.out.println("High temperature (degrees F): " + dayInfoArray[2].strip());
        System.out.println("Low temperature (degrees F): " + dayInfoArray[4].strip());
        System.out.println("Average temperature (degrees F): " + dayInfoArray[3].strip());
    }
}

