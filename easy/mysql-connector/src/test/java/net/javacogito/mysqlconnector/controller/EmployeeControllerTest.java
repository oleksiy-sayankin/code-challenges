package net.javacogito.mysqlconnector.controller;

import net.javacogito.mysqlconnector.entity.Employee;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
    Employee employee = new Employee();

    List<Employee> employees = controller.getAll();

    Assert.assertEquals(3, employees.size());

    employee.setId(1);
    employee.setFirstName("Tonya");
    employee.setLastName("Miller");
    employee.setAge(32);
    employee.setDepartmentId(1);
    employee.setCountryId(1);
    employee.setSalary(4300.43f);
    Assert.assertTrue(employees.contains(employee));

    employee.setId(2);
    employee.setFirstName("Donald");
    employee.setLastName("Shea");
    employee.setAge(28);
    employee.setDepartmentId(1);
    employee.setCountryId(3);
    employee.setSalary(11300.11f);
    Assert.assertTrue(employees.contains(employee));

    employee.setId(3);
    employee.setFirstName("Timmy");
    employee.setLastName("Jones");
    employee.setAge(34);
    employee.setDepartmentId(2);
    employee.setCountryId(2);
    employee.setSalary(32300.39f);
    Assert.assertTrue(employees.contains(employee));

    employee.setId(4);
    employee.setFirstName("No Name");
    employee.setLastName("No Name");
    employee.setAge(34);
    employee.setDepartmentId(2);
    employee.setCountryId(2);
    employee.setSalary(32300.39f);
    Assert.assertFalse(employees.contains(employee));
  }

  @Test public void getEntityById() {
    Employee employee = new Employee();

    employee.setId(2);
    employee.setFirstName("Donald");
    employee.setLastName("Shea");
    employee.setAge(28);
    employee.setDepartmentId(1);
    employee.setCountryId(3);
    employee.setSalary(11300.11f);
    Assert.assertEquals(employee, controller.getEntityById(2));
  }

  @Test public void deleteTest() {
    Employee employee = new Employee();

    Assert.assertTrue(controller.delete(2));
    List<Employee> employees = controller.getAll();

    Assert.assertEquals(2, employees.size());

    employee.setId(2);
    employee.setFirstName("Donald");
    employee.setLastName("Shea");
    employee.setAge(28);
    employee.setDepartmentId(1);
    employee.setCountryId(3);
    employee.setSalary(11300.11f);
    Assert.assertFalse(employees.contains(employee));
  }

  @Test public void updateTest() {
    Employee employee = new Employee();

    employee.setId(2);
    employee.setFirstName("John");
    employee.setLastName("Sanchez");
    employee.setAge(45);
    employee.setDepartmentId(1);
    employee.setCountryId(1);
    employee.setSalary(87300.43f);
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
    Employee employee = new Employee();
    employee.setId(1);
    employee.setFirstName("Tonya");
    employee.setLastName("Miller");
    employee.setAge(32);
    employee.setDepartmentId(1);
    employee.setCountryId(1);
    employee.setSalary(4300.43f);
    Assert.assertTrue(controller.insert(employee));

    employee.setId(2);
    employee.setFirstName("Donald");
    employee.setLastName("Shea");
    employee.setAge(28);
    employee.setDepartmentId(1);
    employee.setCountryId(3);
    employee.setSalary(11300.11f);
    Assert.assertTrue(controller.insert(employee));

    employee.setId(3);
    employee.setFirstName("Timmy");
    employee.setLastName("Jones");
    employee.setAge(34);
    employee.setDepartmentId(2);
    employee.setCountryId(2);
    employee.setSalary(32300.39f);
    Assert.assertTrue(controller.insert(employee));
  }
}
