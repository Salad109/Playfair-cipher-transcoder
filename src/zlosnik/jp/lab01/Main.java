package zlosnik.jp.lab01;

public class Main {
    public static void main(String[] args) {
        SquareMaker maker = new SquareMaker(7);
        String[][] square = maker.getSquare();
        Transcoder transcoder = new Transcoder(square);

        System.out.println("SQUARE:");
        maker.printSquare(square);


        String snippet = "DN";
        int[][] indexes = transcoder.getLetterIndexes(snippet);
        System.out.println("Index of " + snippet.charAt(0) + ": " + "[R" + indexes[0][0] + ", C" + indexes[0][1] + "]");
        System.out.println("Index of " + snippet.charAt(1) + ": " + "[R" + indexes[1][0] + ", C" + indexes[1][1] + "]");

        System.out.println(snippet);
        snippet = transcoder.snippetEncoder(snippet);
        System.out.println(snippet);
        snippet = transcoder.snippetDecoder(snippet);
        System.out.println(snippet);
    }

    private String prepString(String str) {
        str = str.replace(" ", "");

        return str;
    }
}