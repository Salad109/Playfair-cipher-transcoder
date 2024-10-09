package zlosnik.jp.lab01;

import java.util.Random;

public class SquareMaker {
    final int squareLength = 5;
    String[][] square;
    char[] alphabet;
    Random rng;

    SquareMaker() {
        rng = new Random();

        // Build the alphabet
        alphabet = new char[26];
        char letter = 'A';
        for (int i = 0; i < alphabet.length; i++) {
            alphabet[i] = letter++;
            if(letter == 'J')
                letter++;
        }


        square = new String[squareLength][squareLength];
        int alphabet_iterator = 0;
        for (int column = 0; column < squareLength; column++) {
            for (int row = 0; row < squareLength; row++) {
                if (alphabet_iterator >= alphabet.length)
                    alphabet_iterator = 0;
                square[column][row] = String.valueOf(alphabet[alphabet_iterator++]);
            }
        }
    }

    public String[][] getSquare() {
        return square;
    }

    public void printSquare(String[][] square) {
        for (int i = 0; i < square.length; i++) {
            for (int j = 0; j < square.length; j++) {
                System.out.print(square[i][j] + " ");
            }
            System.out.println();
        }
    }

    /*
    public String[][] shuffleSquare(String[][] square) {
        String[][] newSquare = new String[square.length][];
        for (int i = 0; i < square.length; i++) {
            newSquare[i] = Arrays.copyOf(square[i], square[i].length);
        }

        for (int i = 0; i < newSquare.length; i++) {
            for (int j = 0; j < newSquare.length; j++) {
                int randomNumber1 = rng.nextInt(newSquare.length);
                int randomNumber2 = rng.nextInt(newSquare.length);
                String temp = newSquare[i][j];
                newSquare[i][j] = newSquare[randomNumber1][randomNumber2];
                newSquare[randomNumber1][randomNumber2] = temp;
            }
        }
        return newSquare;
    }
    */
}