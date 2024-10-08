package zlosnik.jp.lab01;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        SquareMaker maker = new SquareMaker(9);
        String[][] square = maker.getSquare();
        Transcoder transcoder = new Transcoder(square);

        System.out.println("SQUARE:");
        maker.printSquare(square);

        // TODO Zła konwersja snippetów ( IW i JX źle )

        // test(transcoder);

        String string = "IW";
        System.out.println("Original string: " + string);

        string = transcoder.prepString(string);
        System.out.println("Prepped string: " + string);

        List<String> snippetList = transcoder.stringSplitter(string);
        System.out.println("Snippet list: " + snippetList);

        snippetList = transcoder.snippetListEncoder(snippetList);
        System.out.println("Encoded snippet list: " + snippetList);

        snippetList = transcoder.snippetListDecoder(snippetList);
        System.out.println("Decoded snippet list: " + snippetList);

        string = transcoder.snippetListToString(snippetList);
        System.out.println("Decoded string: " + string);

    }

    public static void test(Transcoder transcoder) {
        // Build the alphabet
        char[] alphabet = new char[26];
        char letter = 'A';
        for (int i = 0; i < alphabet.length; i++) {
            alphabet[i] = (char) (letter + i);
        }

        String string = "";

        StringBuilder builder = new StringBuilder(string);
        List<String> snippetList;
        for (char letter1 : alphabet) {
            for (char letter2 : alphabet) {
                builder.delete(0, builder.length());
                builder.append(letter1);
                builder.append(letter2);
                builder.replace(0, builder.toString().length(), transcoder.prepString(string)); // prep
                snippetList = transcoder.stringSplitter(string); // split
                snippetList = transcoder.snippetListEncoder(snippetList); // encode
                snippetList = transcoder.snippetListDecoder(snippetList); //decode
                string = transcoder.snippetListToString(snippetList); // merge

                try {
                    if (!builder.toString().equals(transcoder.snippetDecoder(transcoder.snippetEncoder(builder.toString()))))
                        System.out.println(builder);
                } catch (Exception e) {
                    System.out.println("ERROR CAUGHT: " + builder);
                }
            }
        }
    }
}