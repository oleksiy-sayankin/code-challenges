package net.javacogito.jdbcconnector.controller;

import net.javacogito.jdbcconnector.entity.Country;
import net.javacogito.jdbcconnector.entity.Customer;
import org.junit.Test;

import java.util.List;

import static net.javacogito.jdbcconnector.util.EntityUtil.createCountry;
import static net.javacogito.jdbcconnector.util.EntityUtil.createCustomer;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class CustomerControllerTest extends AbstractControllerTest<Customer, Integer> {

  private Country country;

  @Test public void insertTest() {
    List<Customer> countries = controller.getAll();
    assertEquals(3, countries.size());
    assertTrue(countries.contains(createCustomer("IMB", "USA, Los Angeles, 3829  Red Maple Drive", country)));
    assertTrue(countries.contains(createCustomer("MicroSoft", "USA, Los Angeles, 2549  Southside Lane", country)));
    assertTrue(countries.contains(createCustomer("Google", "USA, Los Angeles, 3400  Evergreen Lane", country)));
    assertFalse(countries.contains(createCustomer("No name", "No address", country)));
  }

  @Test public void getEntityByIdTest() {
    assertEquals(createCustomer(2, "MicroSoft", "USA, Los Angeles, 2549  Southside Lane", country), controller.getEntityById(2));
  }

  @Test public void getIdByEntityTest() {
    assertEquals(2, (int) controller.getIdByEntity(createCustomer("MicroSoft", "USA, Los Angeles, 2549  Southside Lane", country)));
  }


  @Test public void deleteTest() {
    assertTrue(controller.delete(2));
    List<Customer> customers = controller.getAll();
    assertEquals(2, customers.size());
    assertFalse(customers.contains(createCustomer("MicroSoft", "USA, Los Angeles, 2549  Southside Lane", country)));
  }

  @Test public void updateTest() {
    Customer customer = createCustomer("Oracle", "USA, Los Angeles, 252  Parrill Court", country);
    customer.setId(2);
    assertEquals(2, (int) controller.update(customer));
    assertEquals(customer, controller.getEntityById(2));
  }

  @Override protected void createController() {
    controller = CustomerController.getCustomerController();
  }

  @Override protected void insertData() {

    CountryController  cc = CountryController.getCountryController();
    cc.create();

    country = createCountry(1, "Ukraine");
    cc.insert(country);

    assertEquals(1, (int) controller.insert(createCustomer("IMB", "USA, Los Angeles, 3829  Red Maple Drive", country)));
    assertEquals(2, (int) controller.insert(createCustomer("MicroSoft", "USA, Los Angeles, 2549  Southside Lane", country)));
    assertEquals(3, (int) controller.insert(createCustomer("Google", "USA, Los Angeles, 3400  Evergreen Lane", country)));
  }
}
