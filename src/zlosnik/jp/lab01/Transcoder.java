package zlosnik.jp.lab01;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.LinkedList;
import java.util.List;

public class Transcoder {
    String[][] square;

    Transcoder(String[][] square) {
        this.square = square;
    }

    public int[][] getLetterIndexes(String snippet) {
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

    public String snippetEncoder(String snippet) {
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

    public String snippetDecoder(String snippet) {
        String decodedSnippet;
        int[][] indexes = getLetterIndexes(snippet);
        boolean sameColumn = indexes[0][1] == indexes[1][1];
        boolean sameRow = indexes[0][0] == indexes[1][0];

        if (sameRow & sameColumn) {
            decodedSnippet = snippet.charAt(0) + "X" + snippet.charAt(1);
            return decodedSnippet;
        } else if (sameColumn) {
            decodedSnippet = square[(indexes[0][0] - 1 + square.length) % square.length][indexes[0][1]] +
                    square[(indexes[1][0] - 1 + square.length) % square.length][indexes[1][1]];
            return decodedSnippet;
        } else if (sameRow) {
            decodedSnippet = square[indexes[0][0]][(indexes[0][1] - 1 + square.length) % square.length] +
                    square[indexes[1][0]][(indexes[1][1] - 1 + square.length) % square.length];
            return decodedSnippet;
        } else {
            decodedSnippet = square[indexes[1][0]][indexes[0][1]] + square[indexes[0][0]][indexes[1][1]];
            return decodedSnippet;
        }
    }

    public String prepString(String string) {
        string = string.toUpperCase();
        StringBuilder newString = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) != ' ')
                newString.append(string.charAt(i));

            if (i < string.length() - 1 && string.charAt(i) == string.charAt(i + 1)) {
                newString.append("X");
            }
        }

        if (newString.length() % 2 == 1)
            newString.append("X");
        return newString.toString();
    }

    public List<String> stringSplitter(String str) {
        List<String> snippetList = new LinkedList<>();
        str = prepString(str);
        StringCharacterIterator iterator = new StringCharacterIterator(str);
        StringBuilder snippet = new StringBuilder();

        char ch = iterator.current();
        while (ch != CharacterIterator.DONE) {
            if (iterator.getIndex() % 2 != 0) {
                snippet.append(ch);
            } else {
                snippetList.add(snippet.toString());
                snippet.delete(0, snippet.length());
                snippet.append(ch);
            }
            ch = iterator.next();
        }
        snippetList.add(snippet.toString());
        snippetList.removeFirst();
        return snippetList;
    }
}