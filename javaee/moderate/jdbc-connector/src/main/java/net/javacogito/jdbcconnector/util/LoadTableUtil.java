package net.javacogito.jdbcconnector.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import net.javacogito.jdbcconnector.controller.CountryController;
import net.javacogito.jdbcconnector.controller.CustomerController;
import net.javacogito.jdbcconnector.controller.DepartmentController;
import net.javacogito.jdbcconnector.controller.ProductController;
import net.javacogito.jdbcconnector.controller.ProductTypeController;
import net.javacogito.jdbcconnector.entity.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import static net.javacogito.jdbcconnector.util.EntityUtil.*;

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
        countries.add(createCountry(csvRecord.get(0)));
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
      CountryController cc = CountryController.getCountryController();
      for (CSVRecord csvRecord : csvParser) {
        customers.add(createCustomer(csvRecord.get(0), csvRecord.get(1), cc.getEntityById(Integer.parseInt(csvRecord.get(2)))));
      }
    }
    return customers;
  }

  /**
   * Loads departments from CSV file into list of entities.
   *
   * @param departmentsFile departments File
   * @return list of entities
   * @throws IOException when can't read data
   */
  public static List<Department> loadDepartments(File departmentsFile) throws IOException {
    List<Department> departments = new ArrayList<>();
    try(
        Reader reader = new FileReader(departmentsFile);
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
    ) {
      for (CSVRecord csvRecord : csvParser) {
        departments.add(createDepartment(csvRecord.get(0)));
      }
    }
    return departments;
  }


  /**
   * Loads employees from CSV file into list of entities.
   *
   * @param employeesFile employees File
   * @return list of entities
   * @throws IOException when can't read data
   */
  public static List<Employee> loadEmployees(File employeesFile) throws IOException {
    List<Employee> employees = new ArrayList<>();
    try(
        Reader reader = new FileReader(employeesFile);
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
    ) {
      DepartmentController dec = DepartmentController.getDepartmentController();
      CountryController coc = CountryController.getCountryController();
      for (CSVRecord csvRecord : csvParser) {
        employees.add(createEmployee(csvRecord.get(0), csvRecord.get(1), Integer.parseInt(csvRecord.get(2)),
            dec.getEntityById(Integer.parseInt(csvRecord.get(3))), coc.getEntityById(Integer.parseInt(csvRecord.get(4))), Float.parseFloat(csvRecord.get(5))));
      }
    }
    return employees;
  }

  /**
   * Loads employee emails from CSV file into list of entities.
   *
   * @param employeeEmailsFile employee emails File
   * @return list of entities
   * @throws IOException when can't read data
   */
  public static List<EmployeeEmail> loadEmployeeEmails(File employeeEmailsFile) throws IOException {
    List<EmployeeEmail> employees = new ArrayList<>();
    try(
        Reader reader = new FileReader(employeeEmailsFile);
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
    ) {
      for (CSVRecord csvRecord : csvParser) {
        employees.add(createEmployeeEmail(Integer.parseInt(csvRecord.get(0)), csvRecord.get(1)));
      }
    }
    return employees;
  }


  /**
   * Loads employee phones from CSV file into list of entities.
   *
   * @param employeePhonesFile employee phones File
   * @return list of entities
   * @throws IOException when can't read data
   */
  public static List<EmployeePhone> loadEmployeePhones(File employeePhonesFile) throws IOException {
    List<EmployeePhone> employeePhones = new ArrayList<>();
    try(
        Reader reader = new FileReader(employeePhonesFile);
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
    ) {
      for (CSVRecord csvRecord : csvParser) {
        employeePhones.add(createEmployeePhone(Integer.parseInt(csvRecord.get(0)), csvRecord.get(1)));
      }
    }
    return employeePhones;
  }

  /**
   * Loads orders from CSV file into list of entities.
   *
   * @param ordersFile orders File
   * @return list of entities
   * @throws IOException when can't read data
   */
  public static List<Order> loadOrders(File ordersFile) throws IOException {
    List<Order> orders = new ArrayList<>();
    try(
        Reader reader = new FileReader(ordersFile);
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
    ) {
      CustomerController cuc = CustomerController.getCustomerController();
      ProductController prc = ProductController.getProductController();
      for (CSVRecord csvRecord : csvParser) {
        orders.add(createOrder(cuc.getEntityById(Integer.parseInt(csvRecord.get(0))), prc.getEntityById(Integer.parseInt(csvRecord.get(1))), Integer.parseInt(csvRecord.get(2))));
      }
    }
    return orders;
  }

  /**
   * Loads products from CSV file into list of entities.
   *
   * @param productsFile products File
   * @return list of entities
   * @throws IOException when can't read data
   */
  public static List<Product> loadProducts(File productsFile) throws IOException {
    List<Product> products = new ArrayList<>();
    try(
        Reader reader = new FileReader(productsFile);
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
    ) {
      ProductTypeController ptc = ProductTypeController.getProductTypeController();
      for (CSVRecord csvRecord : csvParser) {
        products.add(createProduct(csvRecord.get(0), ptc.getEntityById(Integer.parseInt(csvRecord.get(1))), Float.parseFloat(csvRecord.get(2))));
      }
    }
    return products;
  }

  /**
   * Loads product types from CSV file into list of entities.
   *
   * @param productTypesFile product types File
   * @return list of entities
   * @throws IOException when can't read data
   */
  public static List<ProductType> loadProductTypes(File productTypesFile) throws IOException {
    List<ProductType> productTypes = new ArrayList<>();
    try(
        Reader reader = new FileReader(productTypesFile);
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
    ) {
      for (CSVRecord csvRecord : csvParser) {
        productTypes.add(createProductType(csvRecord.get(0)));
      }
    }
    return productTypes;
  }
}
