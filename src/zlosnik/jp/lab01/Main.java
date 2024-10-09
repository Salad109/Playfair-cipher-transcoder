package zlosnik.jp.lab01;


import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SquareMaker squareMaker = new SquareMaker();
        String[][] square = squareMaker.getSquare();
        Transcoder transcoder = new Transcoder(square);
        StringHandler handler = new StringHandler();

        System.out.println("SQUARE:");
        squareMaker.printSquare(square);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Reshuffle square? [y/n]");
        String answer = scanner.nextLine();
        if (answer.charAt(0) == 'y') {
            square = squareMaker.reshuffleSquare(square);
            System.out.println("SQUARE:");
            squareMaker.printSquare(square);
        } else if (answer.charAt(0) != 'n') {
            throw new IllegalArgumentException("Invalid answer");
        }

        System.out.print("Provide string to transcode: ");
        String input = scanner.nextLine();
        if(input.isBlank()) {
            throw new IllegalArgumentException("Invalid string input");
        }

        System.out.println("Original string:\t" + input);

        input = handler.prepString(input);
        System.out.println("Prepped string:\t" + input);

        List<String> snippetList = handler.splitString(input);
        System.out.println("Original snippet list:\t" + snippetList);

        snippetList = transcoder.encodeSnippetList(snippetList);
        System.out.println("Encoded snippet list:\t" + snippetList);

        snippetList = transcoder.decodeSnippetList(snippetList);
        System.out.println("Decoded snippet list:\t" + snippetList);

        input = handler.mergeSnippetList(snippetList);
        System.out.println("Decoded string:\t" + input);

    }
}