package net.javacogito.mysqlconnector.controller;

import net.javacogito.mysqlconnector.entity.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CountryController extends AbstractController <Country, Integer> {
  public static final String SELECT_ALL_COUNTRIES = "SELECT * FROM country";
  public static final String INSERT_COUNTRY = "INSERT INTO country VALUES (?, ?)";
  public static final String DELETE_COUNTRY = "DELETE FROM country WHERE id = ?";
  public static final String SELECT_COUNTRY_BY_ID = "SELECT * FROM country WHERE id = ?";
  public static final String UPDATE_COUNTRY_BY_ID = "UPDATE country SET name = ? WHERE id = ?";
  
  @Override public List<Country> getAll() {
    List<Country> countries = new LinkedList<>();
    PreparedStatement ps = getPrepareStatement(SELECT_ALL_COUNTRIES);
    try {
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        Country country = new Country();
        country.setId(rs.getInt(1));
        country.setName(rs.getString(2));
        countries.add(country);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closePrepareStatement(ps);
    }

    return countries;
  }

  @Override public void update(Country entity) {
    PreparedStatement ps = getPrepareStatement(UPDATE_COUNTRY_BY_ID);
    try {
      ps.setString(1, entity.getName());
      ps.setInt(2, entity.getId());
      ps.executeUpdate();
    } catch (SQLException e) {
    }
    finally {
      closePrepareStatement(ps);
    }
  }

  @Override public Country getEntityById(Integer id) {
    PreparedStatement ps = getPrepareStatement(SELECT_COUNTRY_BY_ID);
    Country country = new Country();
    try {
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        country.setId(rs.getInt(1));
        country.setName(rs.getString(2));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closePrepareStatement(ps);
    }
    return country;
  }

  @Override public boolean delete(Integer id) {
    PreparedStatement ps = getPrepareStatement(DELETE_COUNTRY);
    try {
      ps.setInt(1, id);
      ps.executeUpdate();
    } catch (SQLException e) {
      return false;
    }
    finally {
      closePrepareStatement(ps);
    }
    return true;
  }

  @Override public boolean create(Country entity)  {
    PreparedStatement ps = getPrepareStatement(INSERT_COUNTRY);
    try {
      ps.setInt(1, entity.getId());
      ps.setString(2, entity.getName());
      ps.executeUpdate();
    } catch (SQLException e) {
      return false;
    }
    finally {
    closePrepareStatement(ps);
  }
    return true;
  }
}
