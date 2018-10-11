package net.javacogito.mysqlconnector.controller;

import net.javacogito.mysqlconnector.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation for table 'product' controller.
 * Contains basic CRUD operations for table 'product'.
 */

public class ProductController extends AbstractController<Product, Integer> {
  public static final String SELECT_ALL_PRODUCTS = "SELECT * FROM product";
  public static final String INSERT_PRODUCT = "INSERT INTO product VALUES (?, ?, ?, ?)";
  public static final String DELETE_PRODUCT = "DELETE FROM product WHERE id = ?";
  public static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM product WHERE id = ?";
  public static final String UPDATE_PRODUCT_BY_ID = "UPDATE product SET name = ?, product_type_id = ?, price = ? WHERE id = ?";
  public static final String CREATE_PRODUCT = "CREATE TABLE product (id INT PRIMARY KEY, name VARCHAR(100), product_type_id INT, price FLOAT)";
  public static final String DROP_PRODUCT = "DROP TABLE IF EXISTS product";

  /**
   * Returns all products as list. Executes SELECT * FROM product.
   *
   * @return list of all elements
   */
  @Override public List<Product> getAll() {
    List<Product> products = new LinkedList<>();
    PreparedStatement ps = getPrepareStatement(SELECT_ALL_PRODUCTS);
    try {
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        Product product = new Product();
        product.setId(rs.getInt(1));
        product.setName(rs.getString(2));
        product.setProductTypeId(rs.getInt(3));
        product.setPrice(rs.getFloat(4));
        products.add(product);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closePrepareStatement(ps);
    }
    return products;
  }

  /**
   * Updates an product.
   *
   * @param entity product to update.
   */
  @Override public boolean update(Product entity) {
    PreparedStatement ps = getPrepareStatement(UPDATE_PRODUCT_BY_ID);
    try {
      ps.setString(1, entity.getName());
      ps.setInt(2, entity.getProductTypeId());
      ps.setFloat(3, entity.getPrice());
      ps.setInt(4, entity.getId());
      ps.executeUpdate();
    } catch (SQLException e) {
      return false;
    } finally {
      closePrepareStatement(ps);
    }
    return true;
  }

  /**
   * Returns product by it's id, usually primary key.
   *
   * @param id Id of product
   * @return result product.
   */
  @Override public Product getEntityById(Integer id) {
    PreparedStatement ps = getPrepareStatement(SELECT_PRODUCT_BY_ID);
    Product product = new Product();
    try {
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        product.setId(rs.getInt(1));
        product.setName(rs.getString(2));
        product.setProductTypeId(rs.getInt(3));
        product.setPrice(rs.getFloat(4));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closePrepareStatement(ps);
    }
    return product;
  }

  /**
   * Deletes product by id.
   *
   * @param id Id of product to delete
   * @return true of deletion completes successfully.
   */
  @Override public boolean delete(Integer id) {
    PreparedStatement ps = getPrepareStatement(DELETE_PRODUCT);
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
   * Inserts new product.
   *
   * @param entity product to create.
   * @return true of creation completes successfully.
   */
  @Override public boolean insert(Product entity) {
    PreparedStatement ps = getPrepareStatement(INSERT_PRODUCT);
    try {
      ps.setInt(1, entity.getId());
      ps.setString(2, entity.getName());
      ps.setInt(3, entity.getProductTypeId());
      ps.setFloat(4, entity.getPrice());
      ps.executeUpdate();
    } catch (SQLException e) {
      return false;
    } finally {
      closePrepareStatement(ps);
    }
    return true;
  }

  /**
   * Creates table for product.
   *
   * @return true if creation is successful
   */
  @Override public boolean create() {
    PreparedStatement ps = getPrepareStatement(CREATE_PRODUCT);
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
   * Drops table with product.
   *
   * @return true if deletion is successful
   */
  @Override public boolean drop() {
    PreparedStatement ps = getPrepareStatement(DROP_PRODUCT);
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
