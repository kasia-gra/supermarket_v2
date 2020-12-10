package supermarket;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String [] args) throws IOException {
        FileHandler fh = new FileHandler();
        List<Integer> bill1  = fh.readBill("bill1.txt");
        List<Integer> bill2  = fh.readBill("bill2.txt");
        Supermarket supermarket = new Supermarket(fh, "prices.csv");

    }

}
