package net.javacogito.jdbcconnector.controller;

import net.javacogito.jdbcconnector.entity.ProductType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation for table 'product_type' controller.
 * Contains basic CRUD operations for table 'product_type'.
 */

public class ProductTypeController extends AbstractController<ProductType, Integer> {
  private static final String SELECT_ALL_PRODUCT_TYPES = "SELECT * FROM product_type";
  private static final String INSERT_PRODUCT_TYPE = "INSERT INTO product_type VALUES (?, ?)";
  private static final String DELETE_PRODUCT_TYPE = "DELETE FROM product_type WHERE id = ?";
  private static final String SELECT_PRODUCT_TYPE_BY_ID = "SELECT * FROM product_type WHERE id = ?";
  private static final String UPDATE_PRODUCT_TYPE_BY_ID = "UPDATE product_type SET name = ? WHERE id = ?";
  private static final String CREATE_PRODUCT_TYPE = "CREATE TABLE product_type (id INT PRIMARY KEY, name VARCHAR(100))";
  private static final String DROP_PRODUCT_TYPE = "DROP TABLE IF EXISTS product_type";
  private static final ProductTypeController PRODUCT_TYPE_CONTROLLER = new ProductTypeController();
  private ProductTypeController() {}

  /**
   * Gets Product Type Controller
   *
   * @return Product Type Controller
   */
  public static ProductTypeController getProductTypeController() {
    return PRODUCT_TYPE_CONTROLLER;
  }

  /**
   * Returns all product types as list. Executes SELECT * FROM product_type.
   *
   * @return list of all elements
   */
  @Override public List<ProductType> getAll() {
    List<ProductType> productTypes = new LinkedList<>();
    PreparedStatement ps = getPrepareStatement(SELECT_ALL_PRODUCT_TYPES);
    try {
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        ProductType productType = new ProductType();
        productType.setId(rs.getInt(1));
        productType.setName(rs.getString(2));
        productTypes.add(productType);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closePrepareStatement(ps);
    }
    return productTypes;
  }

  /**
   * Updates an product type.
   *
   * @param entity product type to update.
   */
  @Override public boolean update(ProductType entity) {
    PreparedStatement ps = getPrepareStatement(UPDATE_PRODUCT_TYPE_BY_ID);
    try {
      ps.setString(1, entity.getName());
      ps.setInt(2, entity.getId());
      ps.executeUpdate();
    } catch (SQLException e) {
      return false;
    } finally {
      closePrepareStatement(ps);
    }
    return true;
  }

  /**
   * Returns product type by it's id, usually primary key.
   *
   * @param id Id of product type
   * @return result product type.
   */
  @Override public ProductType getEntityById(Integer id) {
    PreparedStatement ps = getPrepareStatement(SELECT_PRODUCT_TYPE_BY_ID);
    ProductType productType = new ProductType();
    try {
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        productType.setId(rs.getInt(1));
        productType.setName(rs.getString(2));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closePrepareStatement(ps);
    }
    return productType;
  }

  /**
   * Deletes product type by id.
   *
   * @param id Id of product type to delete
   * @return true of deletion completes successfully.
   */
  @Override public boolean delete(Integer id) {
    PreparedStatement ps = getPrepareStatement(DELETE_PRODUCT_TYPE);
    try {
      ps.setInt(1, id);
      ps.executeUpdate();
    } catch (SQLException e) {
      return false;
    } finally {
      closePrepareStatement(ps);
    }
    return true;
  }

  /**
   * Inserts new product type.
   *
   * @param entity product type to create.
   * @return true of creation completes successfully.
   */
  @Override public boolean insert(ProductType entity) {
    PreparedStatement ps = getPrepareStatement(INSERT_PRODUCT_TYPE);
    try {
      ps.setInt(1, entity.getId());
      ps.setString(2, entity.getName());
      ps.executeUpdate();
    } catch (SQLException e) {
      return false;
    } finally {
      closePrepareStatement(ps);
    }
    return true;
  }

  /**
   * Creates table for product type.
   *
   * @return true if creation is successful
   */
  @Override public boolean create() {
    PreparedStatement ps = getPrepareStatement(CREATE_PRODUCT_TYPE);
    try {
      ps.executeUpdate();
    } catch (SQLException e) {
      return false;
    } finally {
      closePrepareStatement(ps);
    }
    return true;
  }

  /**
   * Drops table with product type.
   *
   * @return true if deletion is successful
   */
  @Override public boolean drop() {
    PreparedStatement ps = getPrepareStatement(DROP_PRODUCT_TYPE);
    try {
      ps.executeUpdate();
    } catch (SQLException e) {
      return false;
    } finally {
      closePrepareStatement(ps);
    }
    return true;
  }
}