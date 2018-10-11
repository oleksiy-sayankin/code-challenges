package net.javacogito.mysqlconnector.controller;

import net.javacogito.mysqlconnector.entity.EmployeeEmail;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import static net.javacogito.mysqlconnector.util.EntityUtil.createEmployeeEmail;

public class EmployeeEmailControllerTest {
  private Controller<EmployeeEmail, Integer> controller;

  @Before public void init() {
    createTable();
    insertData();
  }

  @After public void close() {
    controller.drop();
  }

  @Test public void insertEmployeeEmailTest() {
    List<EmployeeEmail> employeeEmails = controller.getAll();
    Assert.assertEquals(3, employeeEmails.size());
    Assert.assertTrue(employeeEmails.contains(createEmployeeEmail(1, 1, "test@gmail.com")));
    Assert.assertTrue(employeeEmails.contains(createEmployeeEmail(2, 2, "test@mail.ru")));
    Assert.assertTrue(employeeEmails.contains(createEmployeeEmail(3, 3, "test@list.ru")));
    Assert.assertFalse(employeeEmails.contains(createEmployeeEmail(4, 4, "noname@list.ru")));
  }

  @Test public void getEntityByIdTest() {
    Assert.assertEquals(createEmployeeEmail(2, 2, "test@mail.ru"), controller.getEntityById(2));
  }

  @Test public void deleteTest() {
    Assert.assertTrue(controller.delete(2));
    List<EmployeeEmail> employeeEmails = controller.getAll();
    Assert.assertEquals(2, employeeEmails.size());
    Assert.assertFalse(employeeEmails.contains(createEmployeeEmail(2, 2, "test@mail.ru")));
  }

  @Test public void updateTest() {
    EmployeeEmail employeeEmail = createEmployeeEmail(2, 8, "update@mail.ru");
    Assert.assertTrue(controller.update(employeeEmail));
    Assert.assertEquals(employeeEmail, controller.getEntityById(2));
  }

  private void createTable() {
    controller = new EmployeeEmailController();
    if (!controller.create()) {
      throw new IllegalArgumentException("Can not create table.");
    }
  }

  private void insertData() {
    Assert.assertTrue(controller.insert(createEmployeeEmail(1, 1, "test@gmail.com")));
    Assert.assertTrue(controller.insert(createEmployeeEmail(2, 2, "test@mail.ru")));
    Assert.assertTrue(controller.insert(createEmployeeEmail(3, 3, "test@list.ru")));
  }
}
