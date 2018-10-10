package net.javacogito.mysqlconnector.controller;

import net.javacogito.mysqlconnector.entity.Customer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CustomerControllerTest {
  private Controller<Customer, Integer> controller;

  @Before public void init() {
    createTable();
    insertData();
  }

  @After public void close() {
    controller.drop();
  }

  @Test public void insertCustomerTest() {
    Customer customer = new Customer();

    List<Customer> countries = controller.getAll();

    Assert.assertEquals(3, countries.size());

    customer.setId(1);
    customer.setCompany("IMB");
    customer.setAddress("USA, Los Angeles, 3829  Red Maple Drive");
    customer.setCountryId(1);
    Assert.assertTrue(countries.contains(customer));

    customer.setId(2);
    customer.setCompany("MicroSoft");
    customer.setAddress("USA, Los Angeles, 2549  Southside Lane");
    customer.setCountryId(1);
    Assert.assertTrue(countries.contains(customer));

    customer.setId(3);
    customer.setCompany("Google");
    customer.setAddress("USA, Los Angeles, 3400  Evergreen Lane");
    customer.setCountryId(1);
    Assert.assertTrue(countries.contains(customer));

    customer.setId(4);
    customer.setCompany("No name");
    customer.setAddress("No address");
    customer.setCountryId(1);
    Assert.assertFalse(countries.contains(customer));
  }

  @Test public void getEntityById() {
    Customer customer = new Customer();

    customer.setId(2);
    customer.setCompany("MicroSoft");
    customer.setAddress("USA, Los Angeles, 2549  Southside Lane");
    customer.setCountryId(1);
    Assert.assertEquals(customer, controller.getEntityById(2));
  }

  @Test public void deleteCustomerTest() {
    Customer customer = new Customer();

    Assert.assertTrue(controller.delete(2));
    List<Customer> customers = controller.getAll();

    Assert.assertEquals(2, customers.size());

    customer.setId(2);
    customer.setCompany("MicroSoft");
    customer.setAddress("USA, Los Angeles, 2549  Southside Lane");
    customer.setCountryId(1);
    Assert.assertFalse(customers.contains(customer));
  }

  @Test public void updateTest() {
    Customer customer = new Customer();

    customer.setId(2);
    customer.setId(2);
    customer.setCompany("MicroSoft");
    customer.setAddress("USA, Los Angeles, 2549  Southside Lane");
    customer.setCountryId(1);
    Assert.assertTrue(controller.update(customer));
    Assert.assertEquals(customer, controller.getEntityById(2));
  }

  private void createTable() {
    controller = new CustomerController();
    if (!controller.create()) {
      throw new IllegalArgumentException("Can not create table.");
    }
  }

  private void insertData() {
    Customer customer = new Customer();
    customer.setId(1);
    customer.setCompany("IMB");
    customer.setAddress("USA, Los Angeles, 3829  Red Maple Drive");
    customer.setCountryId(1);
    Assert.assertTrue(controller.insert(customer));

    customer.setId(2);
    customer.setCompany("MicroSoft");
    customer.setAddress("USA, Los Angeles, 2549  Southside Lane");
    customer.setCountryId(1);
    Assert.assertTrue(controller.insert(customer));

    customer.setId(3);
    customer.setCompany("Google");
    customer.setAddress("USA, Los Angeles, 3400  Evergreen Lane");
    customer.setCountryId(1);
    Assert.assertTrue(controller.insert(customer));
  }
}
