package moneycalculator.persistence.file;

import moneycalculator.model.Currency;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import moneycalculator.persistence.CurrencyLoader;

public class CsvFileCurrencyLoader implements CurrencyLoader {
    private final String filename;

    public CsvFileCurrencyLoader(String filename) {
        this.filename = filename;
    }

    @Override
    public Currency[] currencies() {

        List<Currency> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader (new File (filename)));
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                list.add(currencyOf(line));
            }
  
        } catch (FileNotFoundException e) {
            System.out.println("ERROR CsvFileCurrencyLoader::currencies(File not found) " + e.getMessage());
        } catch (IOException e) {
            System.out.println("ERROR CsvFileCurrencyLoader::currencies(IO) " + e.getMessage());
        }
        return list.toArray(new Currency[0]);
    }

    private Currency currencyOf(String line) {
        String[] split = line.split(",");
        return new Currency(split[0], split[1], split[2]);
    }
    
}