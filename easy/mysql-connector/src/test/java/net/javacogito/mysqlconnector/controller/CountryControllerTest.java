package net.javacogito.mysqlconnector.controller;

import net.javacogito.mysqlconnector.entity.Country;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CountryControllerTest {
  private Controller<Country, Integer> controller;

  @Before public void init() {
    createTable();
    insertData();
  }

  @After public void close() {
    controller.drop();
  }

  @Test public void insertCountryTest() {
    Country country = new Country();

    List<Country> countries = controller.getAll();

    Assert.assertEquals(3, countries.size());

    country.setId(1);
    country.setName("Ukraine");
    Assert.assertTrue(countries.contains(country));

    country.setId(2);
    country.setName("USA");
    Assert.assertTrue(countries.contains(country));

    country.setId(3);
    country.setName("Great Britain");
    Assert.assertTrue(countries.contains(country));

    country.setId(4);
    country.setName("No Name");
    Assert.assertFalse(countries.contains(country));
  }

  @Test public void getEntityById() {
    Country country = new Country();
    country.setId(2);
    country.setName("USA");
    Assert.assertEquals(country, controller.getEntityById(2));
  }

  @Test public void deleteCountryTest() {
    Country country = new Country();

    Assert.assertTrue(controller.delete(2));
    List<Country> countries = controller.getAll();

    Assert.assertEquals(2, countries.size());

    country.setId(2);
    country.setName("USA");
    Assert.assertFalse(countries.contains(country));
  }

  @Test public void updateTest() {
    Country country = new Country();

    country.setId(2);
    country.setName("Russia");
    Assert.assertTrue(controller.update(country));
    Assert.assertEquals(country, controller.getEntityById(2));
  }

  private void createTable() {
    controller = new CountryController();
    if (!controller.create()) {
      throw new IllegalArgumentException("Can not create table.");
    }
  }

  private void insertData() {
    Country country = new Country();

    country.setId(1);
    country.setName("Ukraine");
    Assert.assertTrue(controller.insert(country));

    country.setId(2);
    country.setName("USA");
    Assert.assertTrue(controller.insert(country));

    country.setId(3);
    country.setName("Great Britain");
    Assert.assertTrue(controller.insert(country));
  }
}
