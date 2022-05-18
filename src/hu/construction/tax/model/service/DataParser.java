package hu.construction.tax.model.service;

import hu.construction.tax.model.domain.Category;
import hu.construction.tax.model.domain.Site;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataParser {

    private static final String SEPARATOR = " ";

    public List<Site> parse(List<String> lines) {
        parseCategories(lines.remove(0));
        return lines.stream()
                .map(this::createSite)
                .collect(Collectors.toList());
    }

    private void parseCategories(String line) {
        List<Integer> values = Arrays.stream(line.split(SEPARATOR))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        TaxHelper.setCategories(values);
    }

    private Site createSite(String line) {
        String[] items = line.split(SEPARATOR);
        String taxId = items[0];
        String street = items[1];
        String houseNumber = items[2];
        Category category = Category.valueOf(items[3]);
        int area = Integer.parseInt(items[4]);
        return new Site(taxId, street, houseNumber, category, area);
    }

}
