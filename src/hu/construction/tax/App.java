package hu.construction.tax;

import hu.construction.tax.controller.TaxService;
import hu.construction.tax.model.service.*;

import java.util.Scanner;

public class App {

    private final TaxService service;
    private final Console console;
    private final FileWriter writer;

    private App() {
        DataApi dataApi = new DataApi(new FileReader(), new DataParser());
        service = new TaxService(dataApi.getData("utca.txt"));
        console = new Console(new Scanner(System.in));
        writer = new FileWriter("fizetendo.txt");
    }

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        System.out.println("2. feladat: A mintában " + service.getSiteCount() + " telek szerepel.");
        System.out.print("3. feladat: Egy tulajdonos adószáma: ");
        String taxId = console.read();
        System.out.println(service.printSiteAddressesByTaxId(taxId));
        System.out.println("5. feladat");
        System.out.println(service.getCategoryStatistic());
        System.out.println("6. feladat");
        System.out.println(service.getStreetsWithMultipleCategory());
        writer.writeAll(service.calculateTaxAmountByTaxId());
    }
}
