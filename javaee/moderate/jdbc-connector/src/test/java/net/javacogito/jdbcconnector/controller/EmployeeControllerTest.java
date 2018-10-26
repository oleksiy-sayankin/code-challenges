package net.javacogito.jdbcconnector.controller;

import net.javacogito.jdbcconnector.entity.Employee;
import org.junit.Test;

import java.util.List;

import static net.javacogito.jdbcconnector.util.EntityUtil.createEmployee;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class EmployeeControllerTest extends AbstractControllerTest<Employee, Integer> {

  @Test public void insertTest() {
    List<Employee> employees = controller.getAll();
    assertEquals(3, employees.size());
    assertTrue(employees.contains(createEmployee("Tonya", "Miller", 32, 1, 1, 4300.43f)));
    assertTrue(employees.contains(createEmployee("Donald", "Shea", 28, 1, 3, 11300.11f)));
    assertTrue(employees.contains(createEmployee("Timmy", "Jones", 34, 2, 2, 32300.39f)));
    assertFalse(employees.contains(createEmployee("No Name", "No Name", 34, 2, 2, 32300.39f)));
  }

  @Test public void getEntityById() {
    assertEquals(createEmployee("Donald", "Shea", 28, 1, 3, 11300.11f), controller.getEntityById(2));
  }

  @Test public void getIdByEntityTest() {
    assertEquals(new Integer(2), controller.getIdByEntity(createEmployee("Donald", "Shea", 28, 1, 3, 11300.11f)));
  }


  @Test public void deleteTest() {
    assertTrue(controller.delete(2));
    List<Employee> employees = controller.getAll();
    assertEquals(2, employees.size());
    assertFalse(employees.contains(createEmployee(2, "Donald", "Shea", 28, 1, 3, 11300.11f)));
  }

  @Test public void updateTest() {
    Employee employee = createEmployee(2, "John", "Sanchez", 45, 1, 3, 87300.43f);
    assertTrue(controller.update(employee));
    assertEquals(employee, controller.getEntityById(2));
  }

  @Override protected void createController() {
    controller = EmployeeController.getEmployeeController();
  }

  @Override protected void insertData() {
    assertTrue(controller.insert(createEmployee("Tonya", "Miller", 32, 1, 1, 4300.43f)));
    assertTrue(controller.insert(createEmployee("Donald", "Shea", 28, 1, 3, 11300.11f)));
    assertTrue(controller.insert(createEmployee("Timmy", "Jones", 34, 2, 2, 32300.39f)));
  }
}
