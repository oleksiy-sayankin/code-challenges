package net.javacogito.mysqlconnector.controller;

import net.javacogito.mysqlconnector.entity.EmployeeEmail;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
    EmployeeEmail employeeEmail = new EmployeeEmail();

    List<EmployeeEmail> employeeEmails = controller.getAll();

    Assert.assertEquals(3, employeeEmails.size());

    employeeEmail.setId(1);
    employeeEmail.setEmployeeId(1);
    employeeEmail.setEmail("test@gmail.com");
    Assert.assertTrue(employeeEmails.contains(employeeEmail));

    employeeEmail.setId(2);
    employeeEmail.setEmployeeId(2);
    employeeEmail.setEmail("test@mail.ru");
    Assert.assertTrue(employeeEmails.contains(employeeEmail));

    employeeEmail.setId(3);
    employeeEmail.setEmployeeId(3);
    employeeEmail.setEmail("test@list.ru");
    Assert.assertTrue(employeeEmails.contains(employeeEmail));

    employeeEmail.setId(4);
    employeeEmail.setEmployeeId(4);
    employeeEmail.setEmail("noname@list.ru");
    Assert.assertFalse(employeeEmails.contains(employeeEmail));
  }

  @Test public void getEntityById() {
    EmployeeEmail employeeEmail = new EmployeeEmail();
    employeeEmail.setId(2);
    employeeEmail.setEmployeeId(2);
    employeeEmail.setEmail("test@mail.ru");
    Assert.assertEquals(employeeEmail, controller.getEntityById(2));
  }

  @Test public void deleteTest() {
    EmployeeEmail employeeEmail = new EmployeeEmail();

    Assert.assertTrue(controller.delete(2));
    List<EmployeeEmail> employeeEmails = controller.getAll();

    Assert.assertEquals(2, employeeEmails.size());

    employeeEmail.setId(2);
    employeeEmail.setEmployeeId(2);
    employeeEmail.setEmail("test@mail.ru");
    Assert.assertFalse(employeeEmails.contains(employeeEmail));
  }

  @Test public void updateTest() {
    EmployeeEmail employeeEmail = new EmployeeEmail();

    employeeEmail.setId(2);
    employeeEmail.setEmployeeId(8);
    employeeEmail.setEmail("update@mail.ru");
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
    EmployeeEmail employeeEmail = new EmployeeEmail();

    employeeEmail.setId(1);
    employeeEmail.setEmployeeId(1);
    employeeEmail.setEmail("test@gmail.com");
    Assert.assertTrue(controller.insert(employeeEmail));

    employeeEmail.setId(2);
    employeeEmail.setEmployeeId(2);
    employeeEmail.setEmail("test@mail.ru");
    Assert.assertTrue(controller.insert(employeeEmail));

    employeeEmail.setId(3);
    employeeEmail.setEmployeeId(3);
    employeeEmail.setEmail("test@list.ru");
    Assert.assertTrue(controller.insert(employeeEmail));
  }
}
