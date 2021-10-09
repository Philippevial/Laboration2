package se.iths.java21.philippe.laboration2;

import java.util.Objects;

final class Product {
    private String name;
    private float price;
    private int prodID;
    private String category;

    Product(String namn, float price, int prodID, String category) {
        this.name = namn;
        this.price = price;
        this.prodID = prodID;
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setProdID(int prodID) {
        this.prodID = prodID;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String name() {
        return name;
    }

    public float price() {
        return price;
    }

    public long prodID() {
        return prodID;
    }

    public String category() {
        return category;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Product) obj;
        return Objects.equals(this.name, that.name) &&
                this.price == that.price &&
                this.prodID == that.prodID &&
                Objects.equals(this.category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, prodID, category);
    }

    @Override
    public String toString() {
        return "Product[" +
                "Namn: " + name + ", " +
                "Pris: " + price + "kr, " +
                "ProduktID: " + prodID + ", " +
                "Kategori: " + category + ']';
    }

}
