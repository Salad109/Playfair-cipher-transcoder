package zlosnik.jp.lab01;

public class Main {
    public static void main(String[] args) {
        final int squareLength = 6;
        String[][] square = new String[squareLength][squareLength];

        char letter = 'A';
        for(int i = 0; i < square.length; i++){
            for(int j = 0; j < square.length; j++){
                square[i][j] = String.valueOf(letter);
                if(letter == 'Z'){
                    letter = 'A';
                    continue;
                }
                letter++;
            }
        }

        for (int i = 0; i < square.length; i++) {
            for (int j = 0; j < square.length; j++) {
                System.out.print(square[i][j] + " ");
            }
            System.out.println();
        }
    }
}