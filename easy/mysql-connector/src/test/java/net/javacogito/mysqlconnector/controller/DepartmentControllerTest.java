package net.javacogito.mysqlconnector.controller;

import net.javacogito.mysqlconnector.entity.Department;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import static net.javacogito.mysqlconnector.util.EntityUtil.createDepartment;

public class DepartmentControllerTest {
  private Controller<Department, Integer> controller;

  @Before public void init() {
    createTable();
    insertData();
  }

  @After public void close() {
    controller.drop();
  }

  @Test public void insertTest() {
    List<Department> departments = controller.getAll();
    Assert.assertEquals(3, departments.size());
    Assert.assertTrue(departments.contains(createDepartment(1, "Management Department")));
    Assert.assertTrue(departments.contains(createDepartment(2, "Financial Department")));
    Assert.assertTrue(departments.contains(createDepartment(3, "HR Department")));
    Assert.assertFalse(departments.contains(createDepartment(4, "No Name")));
  }

  @Test public void getEntityByIdTest() {
    Assert.assertEquals(createDepartment(2, "Financial Department"), controller.getEntityById(2));
  }

  @Test public void deleteTest() {
    Assert.assertTrue(controller.delete(2));
    List<Department> countries = controller.getAll();
    Assert.assertEquals(2, countries.size());
    Assert.assertFalse(countries.contains(createDepartment(2, "Financial Department")));
  }

  @Test public void updateTest() {
    Department department = createDepartment(2, "Storage Department");
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
    Assert.assertTrue(controller.insert(createDepartment(1, "Management Department")));
    Assert.assertTrue(controller.insert(createDepartment(2, "Financial Department")));
    Assert.assertTrue(controller.insert(createDepartment(3, "HR Department")));
  }
}
