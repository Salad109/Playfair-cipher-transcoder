package zlosnik.jp.lab01;


import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SquareMaker squareMaker = new SquareMaker();
        String[][] square = squareMaker.getSquare();
        Transcoder transcoder = new Transcoder(square);
        StringHandler handler = new StringHandler();
        Scanner scanner = new Scanner(System.in);

        int answer = 0;
        do {
            printOptions();
            try {
                answer = scanner.nextLine().charAt(0) - 48;
            } catch (Exception e) {
                System.out.println("Invalid option");
            }

            switch (answer) {
                case 0:
                    terminate(scanner);
                    break;
                case 1:
                    printSquare(squareMaker);
                    break;
                case 2:
                    reshuffle(squareMaker, square);
                    break;
                case 3:
                    encode(handler, transcoder, scanner);
                    break;
                case 4:
                    decode(handler, transcoder, scanner);
                    break;
                case 5:
                    transcode(handler, transcoder, scanner);
                    break;
            }
        } while (answer != 0);
    }

    private static void terminate(Scanner scanner) {
        System.out.println("Closing program...");
        scanner.close();
    }

    private static void printOptions() {
        System.out.println("Choose from the following options:");
        System.out.println("0. Exit");
        System.out.println("1. Print square");
        System.out.println("2. Reshuffle square");
        System.out.println("3. Encode a message");
        System.out.println("4. Decode a message");
        System.out.println("5. Transcode a message");
    }

    private static void printSquare(SquareMaker maker) {
        maker.printSquare(maker.getSquare());
    }

    private static void reshuffle(SquareMaker squareMaker, String[][] square) {
        squareMaker.reshuffleSquare(square);
        squareMaker.printSquare(square);
    }

    private static void encode(StringHandler handler, Transcoder transcoder, Scanner scanner) {
        System.out.print("Provide a message to encode: ");
        String input;
        do {
            input = scanner.nextLine();
        } while (input.isBlank());

        System.out.println("Original string:\t" + input);

        input = handler.prepString(input);
        System.out.println("Prepped string:\t" + input);

        List<String> snippetList = handler.splitString(input);
        System.out.println("Original snippet list:\t" + snippetList);

        snippetList = transcoder.encodeSnippetList(snippetList);
        System.out.println("Encoded snippet list:\t" + snippetList);

        input = handler.mergeSnippetList(snippetList);
        System.out.println("Encoded string:\t" + input);
    }

    private static void decode(StringHandler handler, Transcoder transcoder, Scanner scanner) {
        System.out.print("Provide a message to decode: ");
        String input;
        do {
            input = scanner.nextLine();
        } while (input.isBlank());

        System.out.println("Original string:\t" + input);

        input = handler.prepString(input);
        System.out.println("Prepped string:\t" + input);

        List<String> snippetList = handler.splitString(input);
        System.out.println("Original snippet list:\t" + snippetList);

        snippetList = transcoder.decodeSnippetList(snippetList);
        System.out.println("Decoded snippet list:\t" + snippetList);

        input = handler.mergeSnippetList(snippetList);
        System.out.println("Decoded string:\t" + input);
    }

    private static void transcode(StringHandler handler, Transcoder transcoder, Scanner scanner) {
        System.out.print("Provide a message to transcode: ");
        String input;
        do {
            input = scanner.nextLine();
        } while (input.isBlank());

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