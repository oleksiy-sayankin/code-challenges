package net.javacogito.jdbcconnector.controller;

import net.javacogito.jdbcconnector.entity.EmployeePhone;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import static net.javacogito.jdbcconnector.util.EntityUtil.createEmployeePhone;

public class EmployeePhoneControllerTest extends AbstractControllerTest<EmployeePhone, Integer>{

  @Test public void insertEmployeeEmailTest() {
    List<EmployeePhone> employeePhones = controller.getAll();
    Assert.assertEquals(3, employeePhones.size());
    Assert.assertTrue(employeePhones.contains(createEmployeePhone(1, 1, "555-9320-23-322-3")));
    Assert.assertTrue(employeePhones.contains(createEmployeePhone(2, 2, "555-3243-22-77-554")));
    Assert.assertTrue(employeePhones.contains(createEmployeePhone(3, 3, "555-3408-3-323-232")));
    Assert.assertFalse(employeePhones.contains(createEmployeePhone(4, 4, "555-000-000-000-00")));
  }

  @Test public void getEntityByIdTest() {
    Assert.assertEquals(createEmployeePhone(2, 2, "555-3243-22-77-554"), controller.getEntityById(2));
  }

  @Test public void deleteTest() {
    Assert.assertTrue(controller.delete(2));
    List<EmployeePhone> employeePhones = controller.getAll();
    Assert.assertEquals(2, employeePhones.size());
    Assert.assertFalse(employeePhones.contains(createEmployeePhone(2, 2, "555-3243-22-77-554")));
  }

  @Test public void updateTest() {
    EmployeePhone employeePhone = createEmployeePhone(2, 27, "555-777-22-77-888");
    Assert.assertTrue(controller.update(employeePhone));
    Assert.assertEquals(employeePhone, controller.getEntityById(2));
  }

  @Override protected void createController() {
    controller = new EmployeePhoneController();
  }

  @Override protected void insertData() {
    Assert.assertTrue(controller.insert(createEmployeePhone(1, 1, "555-9320-23-322-3")));
    Assert.assertTrue(controller.insert(createEmployeePhone(2, 2, "555-3243-22-77-554")));
    Assert.assertTrue(controller.insert(createEmployeePhone(3, 3, "555-3408-3-323-232")));
  }
}
