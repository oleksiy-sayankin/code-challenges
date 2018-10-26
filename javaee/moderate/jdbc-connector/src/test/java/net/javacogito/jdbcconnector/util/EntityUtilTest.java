package net.javacogito.jdbcconnector.util;

import net.javacogito.jdbcconnector.entity.*;
import org.junit.Test;

import static net.javacogito.jdbcconnector.util.EntityUtil.*;
import static org.junit.Assert.assertEquals;

public class EntityUtilTest {

  @Test public void createCountryTest() {
    Country countryExpected = new Country();
    countryExpected.setName("My Country");
    Country countryActual = createCountry("My Country");
    assertEquals(countryExpected, countryActual);
  }


  @Test public void createCustomerTest() {
    Customer customerExpected = new Customer();
    customerExpected.setCompany("IBM");
    customerExpected.setAddress("Perova str. 10");
    customerExpected.setCountryId(1);
    Customer customerActual = createCustomer("IBM", "Perova str. 10", 1);
    assertEquals(customerExpected, customerActual);
  }

  @Test public void createDepartmentTest() {
    Department departmentExpected = new Department();
    departmentExpected.setName("My department");
    Department departmentActual = createDepartment("My department");
    assertEquals(departmentExpected, departmentActual);
  }

  @Test public void createEmployeeTest() {
    Employee employeeExpected = new Employee();
    employeeExpected.setFirstName("AAA");
    employeeExpected.setLastName("BBB");
    employeeExpected.setAge(33);
    employeeExpected.setCountryId(1);
    employeeExpected.setDepartmentId(1);
    employeeExpected.setSalary(3.8f);
    Employee employeeActual = createEmployee("AAA", "BBB", 33, 1, 1, 3.8f);
    assertEquals(employeeExpected, employeeActual);
  }

  @Test public void createEmployeeEmailTest() {
    EmployeeEmail employeeEmailExpected = new EmployeeEmail();
    employeeEmailExpected.setEmail("test@mail.com");
    employeeEmailExpected.setEmployeeId(1);
    EmployeeEmail employeeEmailActual = createEmployeeEmail(1,"test@mail.com");
    assertEquals(employeeEmailExpected, employeeEmailActual);
  }

  @Test public void createEmployeePhoneTest() {
    EmployeePhone employeePhoneExpected = new EmployeePhone();
    employeePhoneExpected.setEmployeeId(1);
    employeePhoneExpected.setNumber("79879-234-234");
    EmployeePhone employeePhoneActual = createEmployeePhone(1, "79879-234-234");
    assertEquals(employeePhoneExpected, employeePhoneActual);
  }

  @Test public void createOrderTest() {
    Order orderExpected = new Order();
    orderExpected.setAmount(100);
    orderExpected.setCustomerId(123);
    orderExpected.setProductId(321);
    Order orderActual = createOrder(123, 321, 100);
    assertEquals(orderExpected, orderActual);
  }

  @Test public void createProductTest() {
    Product productExpected = new Product();
    productExpected.setName("asd");
    productExpected.setProductTypeId(1);
    productExpected.setPrice(32.32f);
    Product productActual = createProduct("asd", 1, 32.32f);
    assertEquals(productExpected, productActual);
  }

  @Test public void createProductTypeTest() {
    ProductType productTypeExpected = new ProductType();
    productTypeExpected.setName("test");
    ProductType productTypeActual = createProductType("test");
    assertEquals(productTypeExpected, productTypeActual);
  }
}
