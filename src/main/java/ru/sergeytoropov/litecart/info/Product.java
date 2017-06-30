package ru.sergeytoropov.litecart.info;

/**
 * @author sergeytoropov
 * @since 30.06.17
 */
public class Product {
    public final String name;
    public final String regularPrice;
    public final String campaignPrice;
    public final boolean logicRegularPrice;
    public final boolean logicCompaignPrice;

    public Product(String name, String regularPrice, String campaignPrice, boolean logicRegularPrice, boolean logicCompaignPrice) {
        this.name = name;
        this.regularPrice = regularPrice;
        this.campaignPrice = campaignPrice;
        this.logicRegularPrice = logicRegularPrice;
        this.logicCompaignPrice = logicCompaignPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (logicRegularPrice == false || product.logicRegularPrice == false) return false;
        if (logicCompaignPrice == false || product.logicCompaignPrice == false) return false;

        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (regularPrice != null ? !regularPrice.equals(product.regularPrice) : product.regularPrice != null)
            return false;
        return campaignPrice != null ? campaignPrice.equals(product.campaignPrice) : product.campaignPrice == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (regularPrice != null ? regularPrice.hashCode() : 0);
        result = 31 * result + (campaignPrice != null ? campaignPrice.hashCode() : 0);
        result = 31 * result + (logicRegularPrice ? 1 : 0);
        result = 31 * result + (logicCompaignPrice ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", regularPrice='" + regularPrice + '\'' +
                ", campaignPrice='" + campaignPrice + '\'' +
                ", logicRegularPrice=" + logicRegularPrice +
                ", logicCompaignPrice=" + logicCompaignPrice +
                '}';
    }
}
