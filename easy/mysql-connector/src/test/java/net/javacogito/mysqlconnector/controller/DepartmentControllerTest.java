package net.javacogito.mysqlconnector.controller;

import net.javacogito.mysqlconnector.entity.Department;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DepartmentControllerTest {
  private Controller<Department, Integer> controller;

  @Before public void init() {
    createTable();
    insertData();
  }

  @After public void close() {
    controller.drop();
  }

  @Test public void insertDepartmentTest() {
    Department department = new Department();

    List<Department> departments = controller.getAll();

    Assert.assertEquals(3, departments.size());

    department.setId(1);
    department.setName("Management Department");
    Assert.assertTrue(departments.contains(department));

    department.setId(2);
    department.setName("Financial Department");
    Assert.assertTrue(departments.contains(department));

    department.setId(3);
    department.setName("HR Department");
    Assert.assertTrue(departments.contains(department));

    department.setId(4);
    department.setName("No Name");
    Assert.assertFalse(departments.contains(department));
  }

  @Test public void getEntityById() {
    Department department = new Department();

    department.setId(2);
    department.setName("Financial Department");
    Assert.assertEquals(department, controller.getEntityById(2));
  }

  @Test public void deleteDepartmentTest() {
    Department department = new Department();

    Assert.assertTrue(controller.delete(2));
    List<Department> countries = controller.getAll();

    Assert.assertEquals(2, countries.size());

    department.setId(2);
    department.setName("Financial Department");
    Assert.assertFalse(countries.contains(department));
  }

  @Test public void updateTest() {
    Department department = new Department();

    department.setId(2);
    department.setName("Storage Department");
    Assert.assertTrue(controller.update(department));
    Assert.assertEquals(department, controller.getEntityById(2));
  }

  private void createTable() {
    controller = new DepartmentController();
    if (!controller.create()) {
      throw new IllegalArgumentException("Can not create table.");
    }
  }

  private void insertData() {
    Department department = new Department();
    department.setId(1);
    department.setName("Management Department");
    Assert.assertTrue(controller.insert(department));

    department.setId(2);
    department.setName("Financial Department");
    Assert.assertTrue(controller.insert(department));

    department.setId(3);
    department.setName("HR Department");
    Assert.assertTrue(controller.insert(department));
  }
}
