package net.javacogito.jdbcconnector.controller;

import net.javacogito.jdbcconnector.entity.Customer;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static net.javacogito.jdbcconnector.util.EntityUtil.createCustomer;

public class CustomerControllerTest extends AbstractControllerTest<Customer, Integer> {

  @Test public void insertTest() {
    List<Customer> countries = controller.getAll();
    Assert.assertEquals(3, countries.size());
    Assert.assertTrue(countries.contains(createCustomer(1, "IMB", "USA, Los Angeles, 3829  Red Maple Drive", 1)));
    Assert.assertTrue(countries.contains(createCustomer(2, "MicroSoft", "USA, Los Angeles, 2549  Southside Lane", 1)));
    Assert.assertTrue(countries.contains(createCustomer(3, "Google", "USA, Los Angeles, 3400  Evergreen Lane", 1)));
    Assert.assertFalse(countries.contains(createCustomer(4, "No name", "No address", 1)));
  }

  @Test public void getEntityByIdTest() {
    Assert.assertEquals(createCustomer(2, "MicroSoft", "USA, Los Angeles, 2549  Southside Lane", 1), controller.getEntityById(2));
  }

  @Test public void deleteTest() {
    Assert.assertTrue(controller.delete(2));
    List<Customer> customers = controller.getAll();
    Assert.assertEquals(2, customers.size());
    Assert.assertFalse(customers.contains(createCustomer(2, "MicroSoft", "USA, Los Angeles, 2549  Southside Lane", 1)));
  }

  @Test public void updateTest() {
    Customer customer = createCustomer(2, "Oracle", "USA, Los Angeles, 252  Parrill Court", 1);
    Assert.assertTrue(controller.update(customer));
    Assert.assertEquals(customer, controller.getEntityById(2));
  }

  @Override protected void createController() {
    controller = new CustomerController();
  }

  @Override protected void insertData() {
    Assert.assertTrue(controller.insert(createCustomer(1, "IMB", "USA, Los Angeles, 3829  Red Maple Drive", 1)));
    Assert.assertTrue(controller.insert(createCustomer(2, "MicroSoft", "USA, Los Angeles, 2549  Southside Lane", 1)));
    Assert.assertTrue(controller.insert(createCustomer(3, "Google", "USA, Los Angeles, 3400  Evergreen Lane", 1)));
  }
}
