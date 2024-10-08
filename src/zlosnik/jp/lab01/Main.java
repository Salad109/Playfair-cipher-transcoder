package zlosnik.jp.lab01;


public class Main {
    public static void main(String[] args) {
        SquareMaker maker = new SquareMaker(7);
        String[][] square = maker.getSquare();
        Transcoder transcoder = new Transcoder(square);

        System.out.println("SQUARE:");
        maker.printSquare(square);

        String snippet = "DN";
        System.out.println("Original: " + snippet);
        snippet = transcoder.snippetEncoder(snippet);
        System.out.println("Encoded: " + snippet);
        snippet = transcoder.snippetDecoder(snippet);
        System.out.println("Decoded: " + snippet);

        String string = "Dobre piiiwo";
        System.out.println("Original: " + string);
        string = transcoder.prepString(string);
        System.out.println("Prepped: " + string);
        System.out.println("Split: " + transcoder.stringSplitter(string));
    }

}