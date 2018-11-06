package net.javacogito.hibernate.controller;

import net.javacogito.hibernate.model.Country;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Implementation for table 'country' controller.
 * Contains basic CRUD operations for table 'country'.
 */

public class CountryController extends AbstractController<Country, Integer> {
  private static final String SELECT_ALL_COUNTRIES = "SELECT * FROM country";
  private static final CountryController COUNTRY_CONTROLLER = new CountryController();
  private static final Logger LOG = LogManager.getLogger(CountryController.class);
  private CountryController() {}

  /**
   * Gets Country Controller
   *
   * @return Country Controller
   */
  public static CountryController getCountryController() {
    return COUNTRY_CONTROLLER;
  }

  /**
   * Returns all countries as list. Executes SELECT * FROM country.
   *
   * @return list of all elements
   */
  @SuppressWarnings("unchecked") @Override public List<Country> getAll() {
    return (List<Country>) session.createQuery(SELECT_ALL_COUNTRIES).list();
  }


  /**
   * Returns country by it's id, usually primary key.
   *
   * @param id Id of country
   * @return result country.
   */
  @Override public Country getEntityById(Integer id) {
    Country country = (Country) session.get(Country.class, id);
    LOG.info(String.format("Found entity %s.", country));
    return country;
  }
}
