package zlosnik.jp.lab01;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        SquareMaker maker = new SquareMaker();
        String[][] square = maker.getSquare();
        Transcoder transcoder = new Transcoder(square);

        System.out.println("SQUARE:");
        maker.printSquare(square);

        String string = "abc def xdl"; // TODO x na końcu
        System.out.println("Original string: \t" + string);

        string = transcoder.prepString(string);
        System.out.println("Prepped string: \t" + string);

        List<String> snippetList = transcoder.stringSplitter(string);
        System.out.println("Original snippet list: \t" + snippetList);

        snippetList = transcoder.snippetListEncoder(snippetList);
        System.out.println("Encoded snippet list: \t" + snippetList);

        snippetList = transcoder.snippetListDecoder(snippetList);
        System.out.println("Decoded snippet list: \t" + snippetList);

        string = transcoder.snippetListToString(snippetList);
        System.out.println("Decoded string: \t" + string);

    }
}