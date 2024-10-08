package zlosnik.jp.lab01;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.LinkedList;
import java.util.List;

public class Transcoder {
    String[][] square;
    List<Integer> spaceIndexes;

    Transcoder(String[][] square) {
        this.square = square;
        spaceIndexes = new LinkedList<>();
    }

    public int[][] getLetterIndexes(String snippet) {
        String firstLetter, secondLetter;
        if (snippet.length() == 3) { // AXA
            firstLetter = snippet.substring(0, 1);
            secondLetter = snippet.substring(2, 3);
        } else if (snippet.length() != 2) {
            throw new IllegalArgumentException("Snippet length must be 2.");
        }
        firstLetter = snippet.substring(0, 1);
        secondLetter = snippet.substring(1, 2);
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

        if (snippet.length() == 3 && snippet.charAt(0) == snippet.charAt(2)) {
            return snippet.charAt(0) + snippet.substring(2, 3);
        }
        if (sameColumn) {
            decodedSnippet = square[(indexes[0][0] - 1 + square.length) % square.length][indexes[0][1]] + square[(indexes[1][0] - 1  + square.length) % square.length][indexes[1][1]];
            return decodedSnippet;
        } else if (sameRow) {
            decodedSnippet = square[indexes[0][0]][(indexes[0][1] - 1  + square.length) % square.length] + square[indexes[1][0]][(indexes[1][1] - 1  + square.length) % square.length];
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
            if (string.charAt(i) == ' ')
                spaceIndexes.add(i);
            else
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

    public List<String> snippetListEncoder(List<String> snippetList) {
        List<String> encodedList = new LinkedList<>();
        for (String snippet : snippetList) {
            encodedList.add(snippetEncoder(snippet));
        }
        return encodedList;
    }

    public String snippetListToString(List<String> snippetList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String snippet : snippetList) {
            stringBuilder.append(snippet);
        }

        while (stringBuilder.indexOf("X") != -1 && stringBuilder.indexOf("X") != stringBuilder.length() - 1) {
            int indexX = stringBuilder.indexOf("X");
            if (stringBuilder.toString().charAt(indexX - 1) == stringBuilder.toString().charAt(indexX + 1))
                stringBuilder.deleteCharAt(stringBuilder.indexOf("X"));
        }

        if (!stringBuilder.isEmpty() && stringBuilder.toString().charAt(stringBuilder.length() - 1) == 'X')
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        for (int index : spaceIndexes) {
            try {
                stringBuilder.insert(index, " ");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("ERROR INDEX: " + index);
            }
        }


        return stringBuilder.toString();
    }

    public List<String> snippetListDecoder(List<String> snippetList) {
        List<String> decodedList = new LinkedList<>();
        for (String snippet : snippetList) {
            decodedList.add(snippetDecoder(snippet));
        }
        return decodedList;
    }
}