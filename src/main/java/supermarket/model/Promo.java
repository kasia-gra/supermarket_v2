package supermarket.model;

import java.math.BigDecimal;

public class Promo {

    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private int productCode;
    private int requiredQty;
    private int codeOfProductRequiredToGetDiscount;

    public Promo(int productCode, int requiredQty,int codeOfProductRequiredToGetDiscount, BigDecimal unitPrice, BigDecimal totalPrice) {
        this.unitPrice = unitPrice;
        this.productCode = productCode;
        this.requiredQty =  requiredQty;
        this.totalPrice = totalPrice;
        this.codeOfProductRequiredToGetDiscount = codeOfProductRequiredToGetDiscount;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString(){
        return "promo qty: " + this.requiredQty + " unit price " + this.unitPrice + " total price " + this.totalPrice;
    }

    public int getRequiredQty() {
        return requiredQty;
    }

    public int getCodeOfProductRequiredToGetDiscount() {
        return codeOfProductRequiredToGetDiscount;
    }
}