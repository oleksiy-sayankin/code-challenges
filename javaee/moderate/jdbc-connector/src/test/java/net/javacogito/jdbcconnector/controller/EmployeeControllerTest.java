package net.javacogito.jdbcconnector.controller;

import net.javacogito.jdbcconnector.entity.Country;
import net.javacogito.jdbcconnector.entity.Department;
import net.javacogito.jdbcconnector.entity.Employee;
import net.javacogito.jdbcconnector.util.EntityUtil;
import org.junit.Test;

import java.util.List;

import static net.javacogito.jdbcconnector.util.EntityUtil.createCountry;
import static net.javacogito.jdbcconnector.util.EntityUtil.createDepartment;
import static net.javacogito.jdbcconnector.util.EntityUtil.createEmployee;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class EmployeeControllerTest extends AbstractControllerTest<Employee, Integer> {
  private Department[] department = new Department[2];
  private Country country[] = new Country[3];

  @Test public void insertTest() {
    List<Employee> employees = controller.getAll();
    assertEquals(3, employees.size());
    assertTrue(employees.contains(createEmployee("Tonya", "Miller", 32, department[0], country[0], 4300.43f)));
    assertTrue(employees.contains(createEmployee("Donald", "Shea", 28, department[0], country[2], 11300.11f)));
    assertTrue(employees.contains(createEmployee("Timmy", "Jones", 34, department[1], country[1], 32300.39f)));
    assertFalse(employees.contains(createEmployee("No Name", "No Name", 34, department[1], country[2], 32300.39f)));
  }

  @Test public void getEntityById() {
    assertEquals(createEmployee("Donald", "Shea", 28, department[0], country[2], 11300.11f), controller.getEntityById(2));
  }

  @Test public void getIdByEntityTest() {
    assertEquals(new Integer(2), controller.getIdByEntity(createEmployee("Donald", "Shea", 28, department[0], country[2], 11300.11f)));
  }


  @Test public void deleteTest() {
    assertTrue(controller.delete(2));
    List<Employee> employees = controller.getAll();
    assertEquals(2, employees.size());
    assertFalse(employees.contains(createEmployee(2, "Donald", "Shea", 28, department[0], country[2], 11300.11f)));
  }

  @Test public void updateTest() {
    Employee employee = createEmployee(2, "John", "Sanchez", 45, department[0], country[2], 87300.43f);
    assertEquals(new Integer(2), controller.update(employee));
    assertEquals(employee, controller.getEntityById(2));
  }

  @Override protected void createController() {
    controller = EmployeeController.getEmployeeController();
  }

  @Override protected void insertData() {
    DepartmentController dc = DepartmentController.getDepartmentController();
    dc.create();

    department[0] = createDepartment(1);
    department[1] = createDepartment(2);

    dc.insert(department[0]);
    dc.insert(department[1]);

    CountryController cc = CountryController.getCountryController();
    cc.create();

    country[0] = createCountry(1);
    country[1] = createCountry(2);
    country[2] = createCountry(3);

    cc.insert(country[0]);
    cc.insert(country[1]);
    cc.insert(country[2]);

    assertEquals(new Integer(1), controller.insert(createEmployee("Tonya", "Miller", 32, department[0], country[0], 4300.43f)));
    assertEquals(new Integer(2), controller.insert(createEmployee("Donald", "Shea", 28, department[0], country[2], 11300.11f)));
    assertEquals(new Integer(3), controller.insert(createEmployee("Timmy", "Jones", 34, department[1], country[1], 32300.39f)));
  }
}
