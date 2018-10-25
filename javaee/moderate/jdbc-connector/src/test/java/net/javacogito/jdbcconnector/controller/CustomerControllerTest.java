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
    Assert.assertTrue(countries.contains(createCustomer("IMB", "USA, Los Angeles, 3829  Red Maple Drive", 1)));
    Assert.assertTrue(countries.contains(createCustomer("MicroSoft", "USA, Los Angeles, 2549  Southside Lane", 1)));
    Assert.assertTrue(countries.contains(createCustomer("Google", "USA, Los Angeles, 3400  Evergreen Lane", 1)));
    Assert.assertFalse(countries.contains(createCustomer("No name", "No address", 1)));
  }

  @Test public void getEntityByIdTest() {
    Assert.assertEquals(createCustomer("MicroSoft", "USA, Los Angeles, 2549  Southside Lane", 1), controller.getEntityById(2));
  }

  @Test public void deleteTest() {
    Assert.assertTrue(controller.delete(2));
    List<Customer> customers = controller.getAll();
    Assert.assertEquals(2, customers.size());
    Assert.assertFalse(customers.contains(createCustomer("MicroSoft", "USA, Los Angeles, 2549  Southside Lane", 1)));
  }

  @Test public void updateTest() {
    Customer customer = createCustomer("Oracle", "USA, Los Angeles, 252  Parrill Court", 1);
    customer.setId(2);
    Assert.assertTrue(controller.update(customer));
    Assert.assertEquals(customer, controller.getEntityById(2));
  }

  @Override protected void createController() {
    controller = CustomerController.getCustomerController();
  }

  @Override protected void insertData() {
    Assert.assertTrue(controller.insert(createCustomer("IMB", "USA, Los Angeles, 3829  Red Maple Drive", 1)));
    Assert.assertTrue(controller.insert(createCustomer("MicroSoft", "USA, Los Angeles, 2549  Southside Lane", 1)));
    Assert.assertTrue(controller.insert(createCustomer("Google", "USA, Los Angeles, 3400  Evergreen Lane", 1)));
  }
}
