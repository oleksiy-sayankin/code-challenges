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
    Assert.assertTrue(countries.contains(createCountry("Ukraine")));
    Assert.assertTrue(countries.contains(createCountry("USA")));
    Assert.assertTrue(countries.contains(createCountry("Great Britain")));
    Assert.assertFalse(countries.contains(createCountry("No Name")));
  }

  @Test public void getEntityByIdTest() {
    Assert.assertEquals(createCountry("USA"), controller.getEntityById(2));
  }

  @Test public void deleteTest() {
    Assert.assertTrue(controller.delete(2));
    List<Country> countries = controller.getAll();
    Assert.assertEquals(2, countries.size());
    Assert.assertFalse(countries.contains(createCountry( "USA")));
  }

  @Test public void updateTest() {
    Country country = createCountry(2, "Russia");
    Assert.assertTrue(controller.update(country));
    Assert.assertEquals(country, controller.getEntityById(2));
  }

  @Override protected void createController() {
    controller = CountryController.getCountryController();
  }

  @Override protected void insertData() {
    Assert.assertTrue(controller.insert(createCountry("Ukraine")));
    Assert.assertTrue(controller.insert(createCountry("USA")));
    Assert.assertTrue(controller.insert(createCountry("Great Britain")));
  }
}
