package net.javacogito.mysqlconnector.controller;

import net.javacogito.mysqlconnector.entity.EmployeePhone;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class EmployeePhoneControllerTest {
  private Controller<EmployeePhone, Integer> controller;

  @Before public void init() {
    createTable();
    insertData();
  }

  @After public void close() {
    controller.drop();
  }

  @Test public void insertEmployeeEmailTest() {
    EmployeePhone employeePhone = new EmployeePhone();

    List<EmployeePhone> employeePhones = controller.getAll();

    Assert.assertEquals(3, employeePhones.size());

    employeePhone.setId(1);
    employeePhone.setEmployeeId(1);
    employeePhone.setNumber("555-9320-23-322-3");
    Assert.assertTrue(employeePhones.contains(employeePhone));

    employeePhone.setId(2);
    employeePhone.setEmployeeId(2);
    employeePhone.setNumber("555-3243-22-77-554");
    Assert.assertTrue(employeePhones.contains(employeePhone));

    employeePhone.setId(3);
    employeePhone.setEmployeeId(3);
    employeePhone.setNumber("555-3408-3-323-232");
    Assert.assertTrue(employeePhones.contains(employeePhone));

    employeePhone.setId(4);
    employeePhone.setEmployeeId(4);
    employeePhone.setNumber("555-000-000-000-00");
    Assert.assertFalse(employeePhones.contains(employeePhone));
  }

  @Test public void getEntityById() {
    EmployeePhone employeePhone = new EmployeePhone();
    employeePhone.setId(2);
    employeePhone.setEmployeeId(2);
    employeePhone.setNumber("555-3243-22-77-554");
    Assert.assertEquals(employeePhone, controller.getEntityById(2));
  }

  @Test public void deleteTest() {
    EmployeePhone employeePhone = new EmployeePhone();

    Assert.assertTrue(controller.delete(2));
    List<EmployeePhone> employeePhones = controller.getAll();

    Assert.assertEquals(2, employeePhones.size());

    employeePhone.setId(2);
    employeePhone.setEmployeeId(2);
    employeePhone.setNumber("555-3243-22-77-554");
    Assert.assertFalse(employeePhones.contains(employeePhone));
  }

  @Test public void updateTest() {
    EmployeePhone employeePhone = new EmployeePhone();

    employeePhone.setId(2);
    employeePhone.setEmployeeId(27);
    employeePhone.setNumber("555-777-22-77-888");
    Assert.assertTrue(controller.update(employeePhone));
    Assert.assertEquals(employeePhone, controller.getEntityById(2));
  }

  private void createTable() {
    controller = new EmployeePhoneController();
    if (!controller.create()) {
      throw new IllegalArgumentException("Can not create table.");
    }
  }

  private void insertData() {
    EmployeePhone employeePhone = new EmployeePhone();

    employeePhone.setId(1);
    employeePhone.setEmployeeId(1);
    employeePhone.setNumber("555-9320-23-322-3");
    Assert.assertTrue(controller.insert(employeePhone));

    employeePhone.setId(2);
    employeePhone.setEmployeeId(2);
    employeePhone.setNumber("555-3243-22-77-554");
    Assert.assertTrue(controller.insert(employeePhone));

    employeePhone.setId(3);
    employeePhone.setEmployeeId(3);
    employeePhone.setNumber("555-3408-3-323-232");
    Assert.assertTrue(controller.insert(employeePhone));
  }
}
