package net.javacogito.jdbcconnector.controller;

import net.javacogito.jdbcconnector.entity.Customer;
import org.junit.Test;

import java.util.List;

import static net.javacogito.jdbcconnector.util.EntityUtil.createCustomer;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class CustomerControllerTest extends AbstractControllerTest<Customer, Integer> {

  @Test public void insertTest() {
    List<Customer> countries = controller.getAll();
    assertEquals(3, countries.size());
    assertTrue(countries.contains(createCustomer("IMB", "USA, Los Angeles, 3829  Red Maple Drive", 1)));
    assertTrue(countries.contains(createCustomer("MicroSoft", "USA, Los Angeles, 2549  Southside Lane", 1)));
    assertTrue(countries.contains(createCustomer("Google", "USA, Los Angeles, 3400  Evergreen Lane", 1)));
    assertFalse(countries.contains(createCustomer("No name", "No address", 1)));
  }

  @Test public void getEntityByIdTest() {
    assertEquals(createCustomer("MicroSoft", "USA, Los Angeles, 2549  Southside Lane", 1), controller.getEntityById(2));
  }

  @Test public void getIdByEntityTest() {
    assertEquals(new Integer(2), controller.getIdByEntity(createCustomer("MicroSoft", "USA, Los Angeles, 2549  Southside Lane", 1)));
  }


  @Test public void deleteTest() {
    assertTrue(controller.delete(2));
    List<Customer> customers = controller.getAll();
    assertEquals(2, customers.size());
    assertFalse(customers.contains(createCustomer("MicroSoft", "USA, Los Angeles, 2549  Southside Lane", 1)));
  }

  @Test public void updateTest() {
    Customer customer = createCustomer("Oracle", "USA, Los Angeles, 252  Parrill Court", 1);
    customer.setId(2);
    assertTrue(controller.update(customer));
    assertEquals(customer, controller.getEntityById(2));
  }

  @Override protected void createController() {
    controller = CustomerController.getCustomerController();
  }

  @Override protected void insertData() {
    assertTrue(controller.insert(createCustomer("IMB", "USA, Los Angeles, 3829  Red Maple Drive", 1)));
    assertTrue(controller.insert(createCustomer("MicroSoft", "USA, Los Angeles, 2549  Southside Lane", 1)));
    assertTrue(controller.insert(createCustomer("Google", "USA, Los Angeles, 3400  Evergreen Lane", 1)));
  }
}
