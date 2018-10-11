package net.javacogito.mysqlconnector.util;

import net.javacogito.mysqlconnector.entity.Country;
import net.javacogito.mysqlconnector.entity.Customer;

/**
 * Class for creation an entity instances.
 */
public final class EntityUtil {
  private EntityUtil(){}

  /**
   * Creates country entity.
   *
   * @param id country id
   * @param name country name
   * @return country entity
   */
  public static Country createCountry(int id, String name){
    Country country = new Country();
    country.setId(id);
    country.setName(name);
    return country;
  }

  /**
   * Creates customer entity.
   *
   * @param id customer id
   * @param company customer company
   * @param address customer address
   * @param countryId customer country id
   * @return customer entity
   */
  public static Customer createCustomer(int id, String company, String address, int countryId){
    Customer customer = new Customer();
    customer.setId(id);
    customer.setCompany(company);
    customer.setAddress(address);
    customer.setCountryId(countryId);
    return customer;
  }
}
