package zlosnik.jp.lab01;

public class Main {
    public static void main(String[] args) {
        SquareMaker maker = new SquareMaker(6);
        Transcoder transcoder = new Transcoder();
        String[][] square = maker.getSquare();

        System.out.println("SQUARE:");
        maker.printSquare(square);

        String snippet = "AK";
        int[][] indexes = transcoder.getLetterIndexes(square, snippet);
        System.out.println("Index " + snippet.charAt(0) + ": " + "[" + indexes[0][0] + ", " + indexes[0][1] + "]");
        System.out.println("Index " + snippet.charAt(1) + ": " + "[" + indexes[1][0] + ", " + indexes[1][1] + "]");
    }
}