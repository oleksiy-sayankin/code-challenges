package net.javacogito.jdbcconnector.controller;

import net.javacogito.jdbcconnector.entity.Country;
import org.junit.Test;

import java.util.List;

import static net.javacogito.jdbcconnector.util.EntityUtil.createCountry;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class CountryControllerTest extends AbstractControllerTest<Country, Integer> {

  @Test public void insertTest() {
    List<Country> countries = controller.getAll();
    assertEquals(3, countries.size());
    assertTrue(countries.contains(createCountry("Ukraine")));
    assertTrue(countries.contains(createCountry("USA")));
    assertTrue(countries.contains(createCountry("Great Britain")));
    assertFalse(countries.contains(createCountry("No Name")));
  }

  @Test public void getEntityByIdTest() {
    assertEquals(createCountry("USA"), controller.getEntityById(2));
  }

  @Test public void getIdByEntityTest() {
    assertEquals(new Integer(2), controller.getIdByEntity(createCountry("USA")));
  }

  @Test public void deleteTest() {
    assertTrue(controller.delete(2));
    List<Country> countries = controller.getAll();
    assertEquals(2, countries.size());
    assertFalse(countries.contains(createCountry( "USA")));
  }

  @Test public void updateTest() {
    Country country = createCountry(2, "Russia");
    assertTrue(controller.update(country));
    assertEquals(country, controller.getEntityById(2));
  }

  @Override protected void createController() {
    controller = CountryController.getCountryController();
  }

  @Override protected void insertData() {
    assertTrue(controller.insert(createCountry("Ukraine")));
    assertTrue(controller.insert(createCountry("USA")));
    assertTrue(controller.insert(createCountry("Great Britain")));
  }
}
