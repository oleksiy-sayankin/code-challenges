package net.javacogito.jdbcconnector.controller;

import net.javacogito.jdbcconnector.entity.Country;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static net.javacogito.jdbcconnector.util.EntityUtil.createCountry;

public class CountryControllerTest extends AbstractControllerTest<Country, Integer> {

  @Test public void insertTest() {
    List<Country> countries = controller.getAll();
    Assert.assertEquals(3, countries.size());
    Assert.assertTrue(countries.contains(createCountry(1, "Ukraine")));
    Assert.assertTrue(countries.contains(createCountry(2, "USA")));
    Assert.assertTrue(countries.contains(createCountry(3, "Great Britain")));
    Assert.assertFalse(countries.contains(createCountry(4, "No Name")));
  }

  @Test public void getEntityByIdTest() {
    Assert.assertEquals(createCountry(2, "USA"), controller.getEntityById(2));
  }

  @Test public void deleteTest() {
    Assert.assertTrue(controller.delete(2));
    List<Country> countries = controller.getAll();
    Assert.assertEquals(2, countries.size());
    Assert.assertFalse(countries.contains(createCountry(2, "USA")));
  }

  @Test public void updateTest() {
    Country country = createCountry(2, "Russia");
    Assert.assertTrue(controller.update(country));
    Assert.assertEquals(country, controller.getEntityById(2));
  }

  @Override protected void createController() {
    controller = new CountryController();
  }

  @Override protected void insertData() {
    Assert.assertTrue(controller.insert(createCountry(1, "Ukraine")));
    Assert.assertTrue(controller.insert(createCountry(2, "USA")));
    Assert.assertTrue(controller.insert(createCountry(3, "Great Britain")));
  }
}
