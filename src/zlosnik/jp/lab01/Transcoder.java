package zlosnik.jp.lab01;

public class Transcoder {
    public int[][] getLetterIndexes(String[][] square, String letter) {
        int[][] index = new int[2][2];
        boolean foundFirst = false;
        boolean foundSecond = false;
        search: for(int i = 0; i < square.length; i++) {
            for(int j = 0; j < square[i].length; j++) {
                if(square[i][j].equals(letter.substring(0, 1)) && !foundFirst) {
                    index[0][0] = i;
                    index[0][1] = j;
                    foundFirst = true;
                }
                if(square[i][j].equals(letter.substring(1, 2)) && !foundSecond) {
                    index[1][0] = i;
                    index[1][1] = j;
                    foundSecond = true;
                }
                if(foundFirst && foundSecond) {
                    break search;
                }
            }
        }
        return index;
    }

}
