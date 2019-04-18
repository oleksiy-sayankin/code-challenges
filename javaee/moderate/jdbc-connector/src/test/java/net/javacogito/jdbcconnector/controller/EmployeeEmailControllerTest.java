package net.javacogito.jdbcconnector.controller;

import net.javacogito.jdbcconnector.entity.EmployeeEmail;
import org.junit.Test;

import java.util.List;

import static net.javacogito.jdbcconnector.util.EntityUtil.createEmployeeEmail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class EmployeeEmailControllerTest extends AbstractControllerTest<EmployeeEmail, Integer>{

  @Test public void insertEmployeeEmailTest() {
    List<EmployeeEmail> employeeEmails = controller.getAll();
    assertEquals(3, employeeEmails.size());
    assertTrue(employeeEmails.contains(createEmployeeEmail(1, "test@gmail.com")));
    assertTrue(employeeEmails.contains(createEmployeeEmail(2, "test@mail.ru")));
    assertTrue(employeeEmails.contains(createEmployeeEmail(3, "test@list.ru")));
    assertFalse(employeeEmails.contains(createEmployeeEmail(4, "noname@list.ru")));
  }

  @Test public void getEntityByIdTest() {
    assertEquals(createEmployeeEmail(2, "test@mail.ru"), controller.getEntityById(2));
  }

  @Test public void getIdByEntityTest() {
    assertEquals(new Integer(2), controller.getIdByEntity(createEmployeeEmail(2, "test@mail.ru")));
  }


  @Test public void deleteTest() {
    assertTrue(controller.delete(2));
    List<EmployeeEmail> employeeEmails = controller.getAll();
    assertEquals(2, employeeEmails.size());
    assertFalse(employeeEmails.contains(createEmployeeEmail(2, "test@mail.ru")));
  }

  @Test public void updateTest() {
    EmployeeEmail employeeEmail = createEmployeeEmail(2, 8, "update@mail.ru");
    assertEquals(new Integer(2), controller.update(employeeEmail));
    assertEquals(employeeEmail, controller.getEntityById(2));
  }

  @Override protected void createController() {
    controller = EmployeeEmailController.getEmployeeEmailController();
  }

  @Override protected void insertData() {
    assertEquals(new Integer(1), controller.insert(createEmployeeEmail(1, "test@gmail.com")));
    assertEquals(new Integer(2), controller.insert(createEmployeeEmail(2, "test@mail.ru")));
    assertEquals(new Integer(3), controller.insert(createEmployeeEmail(3, "test@list.ru")));
  }
}
