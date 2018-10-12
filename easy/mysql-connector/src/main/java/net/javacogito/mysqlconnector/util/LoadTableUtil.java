package net.javacogito.mysqlconnector.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import net.javacogito.mysqlconnector.entity.Country;
import net.javacogito.mysqlconnector.entity.Customer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import static net.javacogito.mysqlconnector.util.EntityUtil.createCountry;
import static net.javacogito.mysqlconnector.util.EntityUtil.createCustomer;

/**
 * Util class for loading data from CSV file and representing it as a List of entities.
 */
public final class LoadTableUtil {
  private LoadTableUtil(){}

  /**
   * Loads countries from CSV file into list of entities.
   *
   * @param countiesFile counties File
   * @return list of entities
   * @throws IOException when can't read data
   */
  public static List<Country> loadCountries(File countiesFile) throws IOException {
    List<Country> countries = new ArrayList<>();
    try(
    Reader reader = new FileReader(countiesFile);
    CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
    ) {
      for (CSVRecord csvRecord : csvParser) {
        countries.add(createCountry(Integer.parseInt(csvRecord.get(0)), csvRecord.get(1)));
      }
    }
    return countries;
  }

  /**
   * Loads customers from CSV file into list of entities.
   *
   * @param customersFile customers File
   * @return list of entities
   * @throws IOException when can't read data
   */
  public static List<Customer> loadCustomers(File customersFile) throws IOException {
    List<Customer> customers = new ArrayList<>();
    try(
        Reader reader = new FileReader(customersFile);
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
    ) {
      for (CSVRecord csvRecord : csvParser) {
        customers.add(createCustomer(Integer.parseInt(csvRecord.get(0)), csvRecord.get(1), csvRecord.get(2), Integer.parseInt(csvRecord.get(3))));
      }
    }
    return customers;
  }


}
