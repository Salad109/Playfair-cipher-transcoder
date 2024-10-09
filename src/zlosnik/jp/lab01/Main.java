package zlosnik.jp.lab01;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        SquareMaker maker = new SquareMaker(9);
        String[][] square = maker.getSquare();
        Transcoder transcoder = new Transcoder(square);

        System.out.println("SQUARE:");
        maker.printSquare(square);

        String string = "IR"; // TODO Enkodując, litera może się zmienić w literę z kolejnego alfabetu, wtedy sie kurwa sypie
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
}