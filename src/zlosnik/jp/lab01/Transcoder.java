package zlosnik.jp.lab01;

public class Transcoder {
    public int[][] getLetterIndexes(String[][] square, String snippet) {
        if (snippet.length() != 2) {
            throw new IllegalArgumentException("Snippet length must be 2.");
        }
        String firstLetter = snippet.substring(0, 1);
        String secondLetter = snippet.substring(1, 2);
        int[][] index = new int[2][2];
        boolean foundFirst = false;
        boolean foundSecond = false;
        for (int row = 0; row < square.length; row++) {
            for (int column = 0; column < square.length; column++) {
                if (square[row][column].equals(firstLetter) && !foundFirst) {
                    index[0][1] = column;
                    index[0][0] = row;
                    foundFirst = true;
                }
                if (square[row][column].equals(secondLetter) && !foundSecond) {
                    index[1][1] = column;
                    index[1][0] = row;
                    foundSecond = true;
                }
                if (foundFirst && foundSecond) {
                    return index;
                }
            }
        }

        throw new IllegalArgumentException("Failed to locate two letters in snippet.");
    }

    public String snippetEncoder(String[][] square, String snippet) {
        String encodedSnippet;
        int[][] indexes = getLetterIndexes(square, snippet);
        boolean sameColumn = indexes[0][1] == indexes[1][1];
        boolean sameRow = indexes[0][0] == indexes[1][0];

        // Different rows and columns
        if (!sameColumn & !sameRow) {
            System.out.println("DIFFERENT EVERYTHING");
            encodedSnippet = square[indexes[1][0]][indexes[0][0]] + square[indexes[0][1]][indexes[1][1]];
            return encodedSnippet;
        }

        // Same rows
        if (sameRow & !sameColumn) {
            System.out.println("SAME ROW");
            encodedSnippet = square[indexes[0][0]][indexes[0][1] + 1] + square[indexes[1][0]][indexes[1][1] + 1];
            return encodedSnippet;
        }

        // Same columns
        if (!sameRow & sameColumn) {
            System.out.println("SAME COLUMN");
            encodedSnippet = square[indexes[0][0]][indexes[0][1]] + square[indexes[1][0]][indexes[1][1]];
            return encodedSnippet;
        }

        // Same letters
        if (sameRow & sameColumn) {
            System.out.println("SAME LETTERS");
            encodedSnippet = snippet.charAt(0) + "X" + snippet.charAt(1);
            return encodedSnippet;
        }

        return "XX";
    }
}
