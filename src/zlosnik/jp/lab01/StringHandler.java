package zlosnik.jp.lab01;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.LinkedList;
import java.util.List;

public class StringHandler {
    List<Integer> spaceIndexes;

    StringHandler() {
        spaceIndexes = new LinkedList<>();
    }

    public String prepString(String string) {
        string = string.toUpperCase();

        StringBuilder newString = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == ' ') spaceIndexes.add(i);
            else newString.append(string.charAt(i));

            if (i < string.length() - 1 && string.charAt(i) == string.charAt(i + 1)) {
                newString.append("X");
            }
        }
        if(newString.length() % 2 != 0) {
            newString.append("X");
        }

        return newString.toString();
    }

    public List<String> splitString(String str) {
        List<String> snippetList = new LinkedList<>();
        str = prepString(str);
        StringCharacterIterator iterator = new StringCharacterIterator(str);
        StringBuilder snippetBuilder = new StringBuilder();

        char ch = iterator.current();
        while (ch != CharacterIterator.DONE) {
            if (iterator.getIndex() % 2 != 0) {
                snippetBuilder.append(ch);
            } else {
                snippetList.add(snippetBuilder.toString());
                snippetBuilder.delete(0, snippetBuilder.length());
                snippetBuilder.append(ch);
            }
            ch = iterator.next();
        }
        snippetList.add(snippetBuilder.toString());
        snippetList.removeFirst();
        return snippetList;
    }

    public String mergeSnippetList(List<String> snippetList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String snippet : snippetList) {
            stringBuilder.append(snippet);
        }
        // System.out.println("Rebuilt snippet list: \t" + stringBuilder);

        if (stringBuilder.charAt(stringBuilder.length() - 1) == 'X')
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        // System.out.println("Trimmed snippet list: \t" + stringBuilder);

        int length = stringBuilder.length() - 1;
        for (int i = 1; i < length; i++) {
            length = stringBuilder.length() - 1;
            if (stringBuilder.charAt(i - 1) == stringBuilder.charAt(i + 1) && stringBuilder.charAt(i) == 'X') {
                stringBuilder.deleteCharAt(i);
            }
        }

        for (int index : spaceIndexes) {
            stringBuilder.insert(index, " ");
        }
        // System.out.println("Formatted snippet list: \t" + stringBuilder);

        return stringBuilder.toString();
    }
}
