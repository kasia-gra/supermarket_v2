package supermarket;

import supermarket.model.Product;
import supermarket.model.Promo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Supermarket {

    private Map<Integer, Product> availableProducts = new HashMap<>();
    private FileHandler fileHandler;

    Supermarket(FileHandler fileHandler, String pricesList) {
        this.fileHandler = fileHandler;
        updateProductsList(pricesList);
    }


    public void updateProductsList(String pricesList) {
        availableProducts = fileHandler.readPrices(pricesList);
    }

    public Map<Integer, Integer> loadBill(List<Integer> listOfBarcodes) {
        Map<Integer, Integer> bill = new HashMap<>();
        for (int code : listOfBarcodes) {
            int qty = (bill.containsKey(code)) ? (bill.get(code) + 1) : 1;
            bill.put(code, qty);
        }
        return bill;
    }


    public BigDecimal getTotalPrice(List<Integer> listOfBarcodes) {
        BigDecimal totalPrice = BigDecimal.valueOf(0);
        Map<Integer, Integer> bill = loadBill(listOfBarcodes);
        List<Promo> usedPromos = new ArrayList<>();
        for (Map.Entry<Integer, Integer> billEntry : bill.entrySet()) {
            Product product = availableProducts.get(billEntry.getKey());
            int qty = billEntry.getValue();
            for (Promo promo : product.getPromosByBestPrice()) {
                if (qty > 0) {
                    if (promo.getCodeOfProductRequiredToGetDiscount() == product.getBarcode()) {
                        int numOfPromoApplications = qty / promo.getRequiredQty();
                        if (numOfPromoApplications >= 1) {
                            totalPrice = totalPrice.add(promo.getTotalPrice().multiply(BigDecimal.valueOf(numOfPromoApplications)));
                            qty = qty - numOfPromoApplications * promo.getRequiredQty();
                        }
                    }
                    else if (bill.containsKey(promo.getCodeOfProductRequiredToGetDiscount())) {
                        if (bill.get(promo.getCodeOfProductRequiredToGetDiscount()) >= promo.getRequiredQty() && !usedPromos.contains(promo)) {
                            usedPromos.add(promo);
                            int maxNumOfPromoApplications = bill.get(promo.getCodeOfProductRequiredToGetDiscount()) / promo.getRequiredQty();
                            int actualNumOfPromoApplications = (maxNumOfPromoApplications < qty)?  maxNumOfPromoApplications : qty;
                            totalPrice = totalPrice.add(promo.getTotalPrice().multiply(BigDecimal.valueOf(actualNumOfPromoApplications)));
                            qty = qty - actualNumOfPromoApplications;
                        }
                    }
                }
            }
            totalPrice = totalPrice.add(product.getUnitPrice().multiply(BigDecimal.valueOf(qty)));
        }
        return totalPrice;
    }



}
