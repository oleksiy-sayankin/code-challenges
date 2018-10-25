package net.javacogito.jdbcconnector.util;

import net.javacogito.jdbcconnector.entity.Country;
import net.javacogito.jdbcconnector.entity.Customer;
import org.junit.Assert;
import org.junit.Test;

import static net.javacogito.jdbcconnector.util.EntityUtil.createCountry;
import static net.javacogito.jdbcconnector.util.EntityUtil.createCustomer;

public class EntityUtilTest {

  @Test public void createCountryTest() {
    Country countryExpected = new Country();
    countryExpected.setName("My Country");
    Country countryActual = createCountry("My Country");
    Assert.assertEquals(countryExpected, countryActual);
  }


  @Test public void createCustomerTest() {
    Customer customerExpected = new Customer();
    customerExpected.setCompany("IBM");
    customerExpected.setAddress("Perova str. 10");
    customerExpected.setCountryId(1);
    Customer customerActual = createCustomer("IBM", "Perova str. 10", 1);
    Assert.assertEquals(customerExpected, customerActual);
  }
}
