package hu.construction.tax.model.service;

import hu.construction.tax.model.domain.Site;

import java.util.List;

public class DataApi {

    private final FileReader fileReader;
    private final DataParser dataParser;

    public DataApi(FileReader fileReader, DataParser dataParser) {
        this.fileReader = fileReader;
        this.dataParser = dataParser;
    }

    public List<Site> getData(String filename) {
        return dataParser.parse(fileReader.read(filename));
    }
}
