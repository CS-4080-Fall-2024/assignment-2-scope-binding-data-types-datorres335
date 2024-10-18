import java.util.Objects;
import java.util.Scanner;

public class RubiksCube {
    public enum Colors { RED, BLUE, GREEN, WHITE, ORANGE, YELLOW };

    // prompting the user for the size of the Rubik's cube (NxN)
    public static int getN(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("How large of a Rubik's cube would you like to see? Type in a whole number: ");
        n = scanner.nextInt();
        return n;
    }
    // variable to store the size of the Rubik's cube
    public static int n = getN();

    // initializing each face of the Rubik's cube with specified colors
    static Colors[][] side1 = fillFaces(n, Colors.RED);
    static Colors[][] side2 = fillFaces(n, Colors.BLUE);
    static Colors[][] side3 = fillFaces(n, Colors.GREEN);
    static Colors[][] side4 = fillFaces(n, Colors.WHITE);
    static Colors[][] side5 = fillFaces(n, Colors.ORANGE);
    static Colors[][] side6 = fillFaces(n, Colors.YELLOW);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // displaying the initial state of the rubik's cube
        System.out.println("Here is the starting rubik's cube: ");
        printFaces(side1);
        printFaces(side2);
        printFaces(side3);
        printFaces(side4);
        printFaces(side5);
        printFaces(side6);

        // infinite loop to continuously get user input for cube manipulation
        while(true){
            System.out.println("Enter your input ");
            System.out.println("Available options are:");
            System.out.println("row 0.." + (n-1) + " [right | left]"); //ex: row 0 right, row 5 left
            System.out.println("col 0.." + (n-1) + " [up | down]"); // ex: col 1 up, col 9 down

            String firstVal = scanner.next(); // "row" or "col"
            int secondVal = scanner.nextInt(); // row/column index
            String direction = scanner.next(); // direction: "right", "left", "up", "down"
            int x;
            int y;

            // determine if the rotation is row-based or column-based
            if (Objects.equals(firstVal, "row")){
                x = secondVal;
                y = 0;
            }
            else {
                x = 0;
                y = secondVal;
            }

            // perform the rotation
            rotate(x, y, direction);

            //print updated faces
            printFaces(side1);
            printFaces(side2);
            printFaces(side3);
            printFaces(side4);
            printFaces(side5);
            printFaces(side6);
        }
    }

    // Fills a face of the Rubik's cube with the specified color
    public static Colors[][] fillFaces(int n, Colors color){
        Colors[][] colors = new Colors[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                colors[i][j] = color;
            }
        }
        return colors;
    }

    public static void printFaces(Colors[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void rotate(int row, int col, String direction){
        // FIRST EDGE HORIZONTAL ROW ROTATION TO THE RIGHT
        if(row == 0 && col == 0 && Objects.equals(direction, "right")){
            for (int i = 0; i < n; i++) {
                Colors temp2 = side2[row][i];
                Colors temp3 = side3[row][i];
                Colors temp5 = side5[row][i];
                Colors temp4 = side4[row][i];

                side2[row][i] = temp4;
                side3[row][i] = temp2;
                side5[row][i] = temp3;
                side4[row][i] = temp5;

                // must rotate entire adjacent face by 90 degrees if user rotates the first row to the right
                rotateCounterClockwise(side1);
            }
        }
        // FIRST EDGE HORIZONTAL ROW ROTATION TO THE LEFT
        if(row == 0 && col == 0 && Objects.equals(direction, "left")){
            for (int i = 0; i < n; i++) {
                Colors temp2 = side2[row][i];
                Colors temp3 = side3[row][i];
                Colors temp5 = side5[row][i];
                Colors temp4 = side4[row][i];

                side2[row][i] = temp3;
                side3[row][i] = temp5;
                side5[row][i] = temp4;
                side4[row][i] = temp2;

                // must rotate entire adjacent face by -90 degrees if user rotates the first row to the left
                rotateClockwise(side1);
            }
        }
        // HORIZONTAL ROW ROTATION TO THE RIGHT
        if(row > 0  && row < n-1 && col == 0 && Objects.equals(direction, "right")){ // TEST CODE, WORKS!!!
            for (int i = 0; i < n; i++) {
                Colors temp2 = side2[row][i];
                Colors temp3 = side3[row][i];
                Colors temp5 = side5[row][i];
                Colors temp4 = side4[row][i];

                side2[row][i] = temp4;
                side3[row][i] = temp2;
                side5[row][i] = temp3;
                side4[row][i] = temp5;
            }
        }
        // HORIZONTAL ROW ROTATION TO THE LEFT
        if(row > 0  && row < n-1 && col == 0 && Objects.equals(direction, "left")){
            for (int i = 0; i < n; i++) {
                Colors temp2 = side2[row][i];
                Colors temp3 = side3[row][i];
                Colors temp5 = side5[row][i];
                Colors temp4 = side4[row][i];

                side2[row][i] = temp3;
                side3[row][i] = temp5;
                side5[row][i] = temp4;
                side4[row][i] = temp2;
            }
        }
        // LAST EDGE HORIZONTAL ROW ROTATION TO THE RIGHT
        if(row == n-1 && col == 0 && Objects.equals(direction, "right")){
            for (int i = 0; i < n; i++) {
                Colors temp2 = side2[row][i];
                Colors temp3 = side3[row][i];
                Colors temp5 = side5[row][i];
                Colors temp4 = side4[row][i];

                side2[row][i] = temp4;
                side3[row][i] = temp2;
                side5[row][i] = temp3;
                side4[row][i] = temp5;

                // must rotate entire adjacent face by 90 degrees if user rotates the last row to the right
                rotateCounterClockwise(side6);
            }
        }
        // LAST EDGE HORIZONTAL ROW ROTATION TO THE LEFT
        if(row == n-1 && col == 0 && Objects.equals(direction, "left")){
            for (int i = 0; i < n; i++) {
                Colors temp2 = side2[row][i];
                Colors temp3 = side3[row][i];
                Colors temp5 = side5[row][i];
                Colors temp4 = side4[row][i];

                side2[row][i] = temp3;
                side3[row][i] = temp5;
                side5[row][i] = temp4;
                side4[row][i] = temp2;

                // must rotate entire adjacent face by -90 degrees if user rotates the last row to the left
                rotateClockwise(side6);
            }
        }

        // VERTICAL ROTATION **********************************************************************************
        // FIRST EDGE VERTICAL COL ROTATION UPWARD
        if(row == 0 && col == 0 && Objects.equals(direction, "up")){ //WORKS CORRECTLY
            for (int i = 0; i < n; i++) {
                Colors temp1 = side1[i][col] ;
                Colors temp5 = side5[i][col] ;
                Colors temp6 = side6[i][col] ;
                Colors temp2 = side2[i][col] ;

                side1[i][col] = temp2;
                side5[i][col] = temp1;
                side6[i][col] = temp5;
                side2[i][col] = temp6;

                rotateCounterClockwise(side4);
            }
        }
        // FIRST EDGE VERTICAL COL ROTATION DOWNWARD
        if(row == 0 && col == 0 && Objects.equals(direction, "down")){ // WORKS CORRECTLY
            for (int i = 0; i < n; i++) {
                Colors temp1 = side1[i][col] ;
                Colors temp5 = side5[i][col] ;
                Colors temp6 = side6[i][col] ;
                Colors temp2 = side2[i][col] ;

                side1[i][col] = temp5;
                side5[i][col] = temp6;
                side6[i][col] = temp2;
                side2[i][col] = temp1;

                rotateCounterClockwise(side4);
            }
        }
        // VERTICAL COL ROTATION UPWARD
        if(row == 0 && col > 0  && col < n-1 && Objects.equals(direction, "up")){ // TEST CODE, WORKS!!!
            for (int i = 0; i < n; i++) {
                Colors temp1 = side1[i][col] ;
                Colors temp5 = side5[i][col] ;
                Colors temp6 = side6[i][col] ;
                Colors temp2 = side2[i][col] ;

                side1[i][col] = temp2;
                side5[i][col] = temp1;
                side6[i][col] = temp5;
                side2[i][col] = temp6;
            }
        }
        // VERTICAL COL ROTATION DOWNWARD
        if(row == 0 && col > 0  && col < n-1 && Objects.equals(direction, "down")){ // WORKS CORRECTLY
            for (int i = 0; i < n; i++) {
                Colors temp1 = side1[i][col] ;
                Colors temp5 = side5[i][col] ;
                Colors temp6 = side6[i][col] ;
                Colors temp2 = side2[i][col] ;

                side1[i][col] = temp5;
                side5[i][col] = temp6;
                side6[i][col] = temp2;
                side2[i][col] = temp1;
            }
        }
        // LAST EDGE VERTICAL COL ROTATION UPWARD
        if(row == 0 && col == n-1 && Objects.equals(direction, "up")){ // WORKS CORRECTLY
            for (int i = 0; i < n; i++) {
                Colors temp1 = side1[i][col] ;
                Colors temp5 = side5[i][col] ;
                Colors temp6 = side6[i][col] ;
                Colors temp2 = side2[i][col] ;

                side1[i][col] = temp2;
                side5[i][col] = temp1;
                side6[i][col] = temp5;
                side2[i][col] = temp6;

                rotateCounterClockwise(side3);
            }
        }
        // LAST EDGE VERTICAL COL ROTATION DOWNWARD
        if(row == 0 && col == n-1 && Objects.equals(direction, "down")){ // WORKS CORRECTLY
            for (int i = 0; i < n; i++) {
                Colors temp1 = side1[i][col] ;
                Colors temp5 = side5[i][col] ;
                Colors temp6 = side6[i][col] ;
                Colors temp2 = side2[i][col] ;

                side1[i][col] = temp5;
                side5[i][col] = temp6;
                side6[i][col] = temp2;
                side2[i][col] = temp1;

                rotateCounterClockwise(side3);
            }
        }
    } //rotate

    // Function to rotate a face of the cube counterclockwise (90 degrees)
    public static Colors[][] rotateCounterClockwise(Colors[][] matrix) {
        int n = matrix.length;
        Colors[][] rotated = new Colors[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotated[i][j] = matrix[j][n - 1 - i];
            }
        }
        return rotated;
    }  // rotateCounterClockwise

    // Function to rotate a face of the cube clockwise (90 degrees)
    public static Colors[][] rotateClockwise(Colors[][] matrix) {
        int n = matrix.length;
        Colors[][] rotated = new Colors[n][n];

        // Transpose the matrix and reverse each row to achieve 90-degree clockwise rotation
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotated[i][j] = matrix[n - 1 - j][i];
            }
        }
        return rotated;
    } //rotateClockwise
}
