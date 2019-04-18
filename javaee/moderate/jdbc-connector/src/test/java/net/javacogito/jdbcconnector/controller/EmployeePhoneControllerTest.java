package net.javacogito.jdbcconnector.controller;

import net.javacogito.jdbcconnector.entity.EmployeePhone;
import org.junit.Test;

import java.util.List;

import static net.javacogito.jdbcconnector.util.EntityUtil.createEmployeePhone;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class EmployeePhoneControllerTest extends AbstractControllerTest<EmployeePhone, Integer>{

  @Test public void insertEmployeeEmailTest() {
    List<EmployeePhone> employeePhones = controller.getAll();
    assertEquals(3, employeePhones.size());
    assertTrue(employeePhones.contains(createEmployeePhone(1, "555-9320-23-322-3")));
    assertTrue(employeePhones.contains(createEmployeePhone(2, "555-3243-22-77-554")));
    assertTrue(employeePhones.contains(createEmployeePhone(3, "555-3408-3-323-232")));
    assertFalse(employeePhones.contains(createEmployeePhone(4, "555-000-000-000-00")));
  }

  @Test public void getEntityByIdTest() {
    assertEquals(createEmployeePhone(2, "555-3243-22-77-554"), controller.getEntityById(2));
  }

  @Test public void getIdByEntityTest() {
    assertEquals(new Integer(2), controller.getIdByEntity(createEmployeePhone(2, "555-3243-22-77-554")));
  }


  @Test public void deleteTest() {
    assertTrue(controller.delete(2));
    List<EmployeePhone> employeePhones = controller.getAll();
    assertEquals(2, employeePhones.size());
    assertFalse(employeePhones.contains(createEmployeePhone(2, "555-3243-22-77-554")));
  }

  @Test public void updateTest() {
    EmployeePhone employeePhone = createEmployeePhone(2, 27, "555-777-22-77-888");
    assertEquals(new Integer(2), controller.update(employeePhone));
    assertEquals(employeePhone, controller.getEntityById(2));
  }

  @Override protected void createController() {
    controller = EmployeePhoneController.getEmployeePhoneController();
  }

  @Override protected void insertData() {
    assertEquals(new Integer(1), controller.insert(createEmployeePhone(1, "555-9320-23-322-3")));
    assertEquals(new Integer(2), controller.insert(createEmployeePhone(2, "555-3243-22-77-554")));
    assertEquals(new Integer(3), controller.insert(createEmployeePhone(3, "555-3408-3-323-232")));
  }
}
