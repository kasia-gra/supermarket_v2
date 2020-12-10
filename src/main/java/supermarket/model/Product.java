package supermarket.model;

import java.math.BigDecimal;
import java.util.*;


public class Product {

    private String productName;
    private List<Promo> promosList = new ArrayList<>();
    private BigDecimal unitPrice;
    private boolean hasBeenUsed = false;

    private int barcode;

    public Product(String productName, int barcode){
        this.productName = productName;
        this.barcode = barcode;
    }

    public void setUnitPrice(BigDecimal unitPrice) {this.unitPrice = unitPrice;}

    public void addPromo(Promo promo){
        promosList.add(promo);
    }

    public List<Promo> getPromosByBestPrice(){
        promosList.sort(Comparator.comparing(Promo::getUnitPrice));
        return promosList;
    }

    public int getBarcode() {
        return barcode;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }


    @Override
    public String toString(){
        return "Name: " + this.productName + " promos: " + promosList;
    }
}
