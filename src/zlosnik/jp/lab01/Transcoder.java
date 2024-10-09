package zlosnik.jp.lab01;

import java.util.LinkedList;
import java.util.List;

public class Transcoder {
    String[][] square;
    List<Integer> spaceIndexes;

    Transcoder(String[][] square) {
        this.square = square;
        spaceIndexes = new LinkedList<>();
    }

    private int[][] getLetterIndexes(String snippet) {
        String firstLetter, secondLetter;
        if (snippet.length() == 2) {
            firstLetter = snippet.substring(0, 1);
            secondLetter = snippet.substring(1, 2);
        } else if (snippet.length() == 3) {
            firstLetter = snippet.substring(0, 1);
            secondLetter = snippet.substring(2, 3);
        } else {
            throw new IllegalArgumentException("Invalid snippet length.");
        }
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

    private String encodeSnippet(String snippet) {
        String encodedSnippet;
        int[][] indexes = getLetterIndexes(snippet);
        boolean sameColumn = indexes[0][1] == indexes[1][1];
        boolean sameRow = indexes[0][0] == indexes[1][0];

        if (sameRow & sameColumn) {
            encodedSnippet = snippet.charAt(0) + "X" + snippet.charAt(1);
            return encodedSnippet;
        } else if (sameColumn) {
            encodedSnippet = square[(indexes[0][0] + 1) % square.length][indexes[0][1]] + square[(indexes[1][0] + 1) % square.length][indexes[1][1]];
            return encodedSnippet;
        } else if (sameRow) {
            encodedSnippet = square[indexes[0][0]][(indexes[0][1] + 1) % square.length] + square[indexes[1][0]][(indexes[1][1] + 1) % square.length];
            return encodedSnippet;
        } else {
            encodedSnippet = square[indexes[1][0]][indexes[0][1]] + square[indexes[0][0]][indexes[1][1]];
            return encodedSnippet;
        }
    }

    private String decodeSnippet(String snippet) {
        String decodedSnippet;
        int[][] indexes = getLetterIndexes(snippet);
        boolean sameColumn = indexes[0][1] == indexes[1][1];
        boolean sameRow = indexes[0][0] == indexes[1][0];

        if (snippet.length() == 3 && snippet.charAt(0) == snippet.charAt(2)) {
            return snippet.charAt(0) + snippet.substring(2, 3);
        }
        if (sameColumn) {
            decodedSnippet = square[(indexes[0][0] - 1 + square.length) % square.length][indexes[0][1]] + square[(indexes[1][0] - 1 + square.length) % square.length][indexes[1][1]];
        } else if (sameRow) {
            decodedSnippet = square[indexes[0][0]][(indexes[0][1] - 1 + square.length) % square.length] + square[indexes[1][0]][(indexes[1][1] - 1 + square.length) % square.length];
        } else {
            decodedSnippet = square[indexes[1][0]][indexes[0][1]] + square[indexes[0][0]][indexes[1][1]];
        }
        return decodedSnippet;
    }

    public List<String> encodeSnippetList(List<String> snippetList) {
        List<String> encodedList = new LinkedList<>();
        for (String snippet : snippetList) {
            encodedList.add(encodeSnippet(snippet));
        }
        return encodedList;
    }

    public List<String> decodeSnippetList(List<String> snippetList) {
        List<String> decodedList = new LinkedList<>();
        for (String snippet : snippetList) {
            decodedList.add(decodeSnippet(snippet));
        }
        return decodedList;
    }
}