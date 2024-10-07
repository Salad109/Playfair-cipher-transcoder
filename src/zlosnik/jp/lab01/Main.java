package zlosnik.jp.lab01;

public class Main {
    public static void main(String[] args) {
        SquareMaker maker = new SquareMaker(5);
        Transcoder transcoder = new Transcoder();
        String[][] square = maker.getSquare();

        System.out.println("SQUARE:");
        maker.printSquare(square);

        String snippet = "AC";
        int[][] indexes = transcoder.getLetterIndexes(square, snippet);
        System.out.println("Index of " + snippet.charAt(0) + ": " + "[R" + indexes[0][0] + ", C" + indexes[0][1] + "]");
        System.out.println("Index of " + snippet.charAt(1) + ": " + "[R" + indexes[1][0] + ", C" + indexes[1][1] + "]");

        System.out.println(transcoder.snippetEncoder(square, snippet));
    }
}