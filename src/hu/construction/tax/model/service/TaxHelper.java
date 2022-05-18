package hu.construction.tax.model.service;

import hu.construction.tax.model.domain.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaxHelper {

    private static final int TAX_LIMIT = 10_000;
    private final static Map<Category, Integer> categoryMap = new HashMap<>();

    public static void setCategories(List<Integer> values) {
        categoryMap.put(Category.A, values.get(0));
        categoryMap.put(Category.B, values.get(1));
        categoryMap.put(Category.C, values.get(2));
    }

    private static int getValue(Category category) {
        return categoryMap.getOrDefault(category, 0);
    }

    /**
     * 4. feladat
     */
    public static int calculateTax(Category category, int area) {
        return ado(category, area);
    }

    private static int ado(Category category, int area) {
        int ado = getValue(category) * area;
        return  ado < TAX_LIMIT ? 0 : ado;
    }
}
