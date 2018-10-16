package net.javacogito.jdbcconnector.controller;

import net.javacogito.jdbcconnector.entity.Employee;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import static net.javacogito.jdbcconnector.util.EntityUtil.createEmployee;

public class EmployeeControllerTest {
  private Controller<Employee, Integer> controller;

  @Before public void init() {
    createTable();
    insertData();
  }

  @After public void close() {
    controller.drop();
  }

  @Test public void insertTest() {
    List<Employee> employees = controller.getAll();
    Assert.assertEquals(3, employees.size());
    Assert.assertTrue(employees.contains(createEmployee(1, "Tonya", "Miller", 32, 1, 1, 4300.43f)));
    Assert.assertTrue(employees.contains(createEmployee(2, "Donald", "Shea", 28, 1, 3, 11300.11f)));
    Assert.assertTrue(employees.contains(createEmployee(3, "Timmy", "Jones", 34, 2, 2, 32300.39f)));
    Assert.assertFalse(employees.contains(createEmployee(4, "No Name", "No Name", 34, 2, 2, 32300.39f)));
  }

  @Test public void getEntityById() {
    Assert.assertEquals(createEmployee(2, "Donald", "Shea", 28, 1, 3, 11300.11f), controller.getEntityById(2));
  }

  @Test public void deleteTest() {
    Assert.assertTrue(controller.delete(2));
    List<Employee> employees = controller.getAll();
    Assert.assertEquals(2, employees.size());
    Assert.assertFalse(employees.contains(createEmployee(2, "Donald", "Shea", 28, 1, 3, 11300.11f)));
  }

  @Test public void updateTest() {
    Employee employee = createEmployee(2, "John", "Sanchez", 45, 1, 3, 87300.43f);
    Assert.assertTrue(controller.update(employee));
    Assert.assertEquals(employee, controller.getEntityById(2));
  }

  private void createTable() {
    controller = new EmployeeController();
    if (!controller.create()) {
      throw new IllegalArgumentException("Can not create table.");
    }
  }

  private void insertData() {
    Assert.assertTrue(controller.insert(createEmployee(1, "Tonya", "Miller", 32, 1, 1, 4300.43f)));
    Assert.assertTrue(controller.insert(createEmployee(2, "Donald", "Shea", 28, 1, 3, 11300.11f)));
    Assert.assertTrue(controller.insert(createEmployee(3, "Timmy", "Jones", 34, 2, 2, 32300.39f)));
  }
}
