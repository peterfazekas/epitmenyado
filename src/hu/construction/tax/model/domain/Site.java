package hu.construction.tax.model.domain;

import hu.construction.tax.model.service.TaxHelper;

public class Site {

    private final String taxId;
    private final String street;
    private final String houseNumber;
    private final Category category;
    private final int area;

    public Site(String taxId, String street, String houseNumber, Category category, int area) {
        this.taxId = taxId;
        this.street = street;
        this.houseNumber = houseNumber;
        this.category = category;
        this.area = area;
    }

    public String getTaxId() {
        return taxId;
    }

    public String getStreet() {
        return street;
    }

    public boolean isStreet(String street) {
        return this.street.equals(street);
    }

    public boolean isSite(String taxId) {
        return this.taxId.equals(taxId);
    }

    public String getAddress() {
        return street + " utca " + houseNumber;
    }

    public int taxAmount() {
        return TaxHelper.calculateTax(category, area);
    }

    public boolean isCategory(Category category) {
        return this.category.equals(category);
    }

    public String printCategory() {
        return category.name();
    }
}
