package supermarket;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SupermarketTest {

    FileHandler fileHandler = new FileHandler();
    Supermarket market = new Supermarket(fileHandler, "prices.csv");
    List<Integer> bill1  = new ArrayList<>();

    @Test
    void getBillData() throws InterruptedException, IOException {
        bill1.add(1001);
        bill1.add(1001);
        bill1.add(3401);
        bill1.add(1001);
        Map<Integer, Integer> solution = new HashMap<>();
        solution.put(1001, 3);
        solution.put(3401, 1);
        assertEquals(market.loadBill(bill1), solution);
    }


    @Test
    void getPrice1() throws InterruptedException, IOException {
        bill1.add(1001);
        bill1.add(1001);
        bill1.add(1001);
        bill1.add(1001);
        bill1.add(1243);
        bill1.add(1243);
        bill1.add(1243);
        BigDecimal totalPrice = market.getTotalPrice(bill1);
        assertEquals(totalPrice, BigDecimal.valueOf(3.60));
    }

    @Test
    void getPrice2() throws InterruptedException, IOException {
        bill1.add(1001);
        bill1.add(1001);
        bill1.add(1243);
        bill1.add(1243);
        bill1.add(1243);
        bill1.add(3401);
        bill1.add(1243);
        bill1.add(1243);
        bill1.add(1001);
        BigDecimal totalPrice = market.getTotalPrice(bill1);
        assertEquals(totalPrice, BigDecimal.valueOf(6.90).setScale(2));
    }
}