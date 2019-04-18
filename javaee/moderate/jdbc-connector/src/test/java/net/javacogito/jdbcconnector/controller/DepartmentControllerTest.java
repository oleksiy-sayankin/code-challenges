package net.javacogito.jdbcconnector.controller;

import net.javacogito.jdbcconnector.entity.Department;
import org.junit.Test;

import java.util.List;

import static net.javacogito.jdbcconnector.util.EntityUtil.createDepartment;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class DepartmentControllerTest extends AbstractControllerTest<Department, Integer> {

  @Test public void insertTest() {
    List<Department> departments = controller.getAll();
    assertEquals(3, departments.size());
    assertTrue(departments.contains(createDepartment("Management Department")));
    assertTrue(departments.contains(createDepartment("Financial Department")));
    assertTrue(departments.contains(createDepartment("HR Department")));
    assertFalse(departments.contains(createDepartment("No Name")));
  }

  @Test public void getEntityByIdTest() {
    assertEquals(createDepartment("Financial Department"), controller.getEntityById(2));
  }

  @Test public void getIdByEntityTest() {
    assertEquals(new Integer(2), controller.getIdByEntity(createDepartment("Financial Department")));
  }


  @Test public void deleteTest() {
    assertTrue(controller.delete(2));
    List<Department> countries = controller.getAll();
    assertEquals(2, countries.size());
    assertFalse(countries.contains(createDepartment("Financial Department")));
  }

  @Test public void updateTest() {
    Department department = createDepartment(2, "Storage Department");
    assertEquals(new Integer(2), controller.update(department));
    assertEquals(department, controller.getEntityById(2));
  }

  @Override protected void createController() {
    controller = DepartmentController.getDepartmentController();
  }

  @Override protected void insertData() {
    assertEquals(new Integer(1), controller.insert(createDepartment("Management Department")));
    assertEquals(new Integer(2), controller.insert(createDepartment("Financial Department")));
    assertEquals(new Integer(3), controller.insert(createDepartment("HR Department")));
  }
}
