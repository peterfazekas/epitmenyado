package hu.construction.tax.controller;

import hu.construction.tax.model.domain.Category;
import hu.construction.tax.model.domain.Site;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TaxService {

    private static final String DELIMITER = "\n";
    private final List<Site> sites;

    public TaxService(List<Site> sites) {
        this.sites = sites;
    }

    /**
     * 2. feladat
     */
    public int getSiteCount() {
        return sites.size();
    }

    /**
     * 3. feladat
     */
    public String printSiteAddressesByTaxId(String taxId) {
        List<String> siteAddressesByTaxId = getSiteAddressesByTaxId(taxId);
        return  (siteAddressesByTaxId.size() == 0)
                ? "Nem szerepel az adatállományban."
                : String.join(DELIMITER, siteAddressesByTaxId);
    }

    private List<String> getSiteAddressesByTaxId(String taxId) {
        return sites.stream()
                .filter(site -> site.isSite(taxId))
                .map(Site::getAddress)
                .collect(Collectors.toList());
    }

    /**
     * 5. feladat
     */

    public String getCategoryStatistic() {
        return Arrays.stream(Category.values())
                .map(this::getSitesByCategory)
                .map(this::getCategoryDetails)
                .collect(Collectors.joining(DELIMITER));
    }

    private List<Site> getSitesByCategory(Category category) {
        return sites.stream()
                .filter(site -> site.isCategory(category))
                .collect(Collectors.toList());
    }

    private String getCategoryDetails(List<Site> subSites) {
        String category = subSites.get(0).printCategory();
        return String.format("%s sávba %d telek esik, az adó %d Ft.", category, subSites.size(), calculateTexAmount(subSites)) ;
    }

    private int calculateTexAmount(List<Site> subSites) {
        return subSites.stream()
                .mapToInt(Site::taxAmount)
                .sum();
    }
    /**
     * 6. feladat
     */
    public String getStreetsWithMultipleCategory() {
        return getStreets().stream()
                .filter(i -> countCategoriesByStreet(i) > 1)
                .collect(Collectors.joining(DELIMITER));
    }


    private long countCategoriesByStreet(String street) {
        return sites.stream()
                .filter(site -> site.isStreet(street))
                .map(Site::printCategory)
                .distinct()
                .count();
    }

    private List<String> getStreets() {
        return sites.stream()
                .map(Site::getStreet)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * 7. feladat
     */
    public List<String> calculateTaxAmountByTaxId() {
        return createTaxIdAggregatedTaxAmountMap().entrySet().stream()
                .map(entry -> entry.getKey() + " " + entry.getValue())
                .collect(Collectors.toList());
    }

    private Map<String, Integer> createTaxIdAggregatedTaxAmountMap() {
        return sites.stream()
                .collect(Collectors.groupingBy(Site::getTaxId, Collectors.summingInt(Site::taxAmount)));
    }
}
