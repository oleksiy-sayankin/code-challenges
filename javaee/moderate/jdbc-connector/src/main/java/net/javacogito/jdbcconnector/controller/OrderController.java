package net.javacogito.jdbcconnector.controller;

import net.javacogito.jdbcconnector.entity.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation for table 'order' controller.
 * Contains basic CRUD operations for table 'order'.
 */

public class OrderController extends AbstractController<Order, Integer> {
  private static final String SELECT_ALL_ORDERS = "SELECT * FROM `order`";
  private static final String INSERT_ORDER = "INSERT INTO `order`(customer_id, product_id, amount) VALUES (?, ?, ?)";
  private static final String DELETE_ORDER = "DELETE FROM `order` WHERE id = ?";
  private static final String SELECT_ORDER_BY_ID = "SELECT * FROM `order` WHERE id = ?";
  private static final String SELECT_ID_BY_ORDER = "SELECT id FROM `order` WHERE customer_id = ? AND product_id = ? AND amount = ?";
  private static final String UPDATE_ORDER_BY_ID = "UPDATE `order` SET customer_id = ?, product_id = ?, amount = ? WHERE id = ?";
  private static final String CREATE_ORDER = "CREATE TABLE `order` (id INT AUTO_INCREMENT PRIMARY KEY, customer_id INT, product_id INT, amount INT)";
  private static final String DROP_ORDER = "DROP TABLE IF EXISTS `order`";
  private static final OrderController ORDER_CONTROLLER = new OrderController();
  private static final Logger LOG = LogManager.getLogger(OrderController.class);
  private OrderController() {}

  /**
   * Gets Order Controller
   *
   * @return Order Controller
   */
  public static OrderController getOrderController() {
    return ORDER_CONTROLLER;
  }

  /**
   * Returns all orders as list. Executes SELECT * FROM order.
   *
   * @return list of all elements
   */
  @Override public List<Order> getAll() {
    List<Order> orders = new LinkedList<>();
    PreparedStatement ps = getPrepareStatement(SELECT_ALL_ORDERS);
    try {
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        Order order = new Order();
        order.setId(rs.getInt(1));
        order.setCustomerId(rs.getInt(2));
        order.setProductId(rs.getInt(3));
        order.setAmount(rs.getInt(4));
        orders.add(order);
      }
    } catch (SQLException e) {
      LOG.error(e);
    } finally {
      closePrepareStatement(ps);
    }
    return orders;
  }

  /**
   * Updates an order.
   *
   * @param entity order to update.
   */
  @Override public boolean update(Order entity) {
    PreparedStatement ps = getPrepareStatement(UPDATE_ORDER_BY_ID);
    try {
      ps.setInt(1, entity.getCustomerId());
      ps.setInt(2, entity.getProductId());
      ps.setInt(3, entity.getAmount());
      ps.setInt(4, entity.getId());
      ps.executeUpdate();
    } catch (SQLException e) {
      LOG.error(e);
      return false;
    } finally {
      closePrepareStatement(ps);
    }
    LOG.info(String.format("Entity with id = %d updated to new value %s.", entity.getId(), entity));
    return true;
  }

  /**
   * Returns order by it's id, usually primary key.
   *
   * @param id Id of order
   * @return result order.
   */
  @Override public Order getEntityById(Integer id) {
    PreparedStatement ps = getPrepareStatement(SELECT_ORDER_BY_ID);
    Order order = new Order();
    try {
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        order.setId(rs.getInt(1));
        order.setCustomerId(rs.getInt(2));
        order.setProductId(rs.getInt(3));
        order.setAmount(rs.getInt(4));
      }
    } catch (SQLException e) {
      LOG.error(e);
    } finally {
      closePrepareStatement(ps);
    }
    LOG.info(String.format("Found entity %s.", order));
    return order;
  }

  /**
   * Returns id of the entity by its value
   *
   * @param entity entity with filled in data
   * @return id of the entity by its value
   */
  @Override public Integer getIdByEntity(Order entity) {
    PreparedStatement ps = getPrepareStatement(SELECT_ID_BY_ORDER);
    Integer id = null;
    try {
      ps.setInt(1, entity.getCustomerId());
      ps.setInt(2, entity.getProductId());
      ps.setInt(3, entity.getAmount());
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        id = rs.getInt(1);
      }
    } catch (SQLException e) {
      LOG.error(e);
    } finally {
      closePrepareStatement(ps);
    }
    LOG.info(String.format("Found id %d for entity %s.",id, entity));
    return id;
  }

  /**
   * Deletes order by id.
   *
   * @param id Id of order to delete
   * @return true of deletion completes successfully.
   */
  @Override public boolean delete(Integer id) {
    PreparedStatement ps = getPrepareStatement(DELETE_ORDER);
    try {
      ps.setInt(1, id);
      ps.executeUpdate();
    } catch (SQLException e) {
      LOG.error(e);
      return false;
    } finally {
      closePrepareStatement(ps);
    }
    LOG.info(String.format("Entity with id = %d deleted", id));
    return true;
  }

  /**
   * Inserts new order.
   *
   * @param entity order to create.
   * @return true of creation completes successfully.
   */
  @Override public boolean insert(Order entity) {
    PreparedStatement ps = getPrepareStatement(INSERT_ORDER);
    try {
      ps.setInt(1, entity.getCustomerId());
      ps.setInt(2, entity.getProductId());
      ps.setInt(3, entity.getAmount());
      ps.executeUpdate();
    } catch (SQLException e) {
      LOG.error(e);
      return false;
    } finally {
      closePrepareStatement(ps);
    }
    LOG.info(String.format("Entity %s inserted", entity));
    return true;
  }

  /**
   * Creates table for order.
   *
   * @return true if creation is successful
   */
  @Override public boolean create() {
    PreparedStatement ps = getPrepareStatement(CREATE_ORDER);
    try {
      ps.executeUpdate();
    } catch (SQLException e) {
      LOG.error(e);
      return false;
    } finally {
      closePrepareStatement(ps);
    }
    LOG.info("Table for entity is created");
    return true;
  }

  /**
   * Drops table with order.
   *
   * @return true if deletion is successful
   */
  @Override public boolean drop() {
    PreparedStatement ps = getPrepareStatement(DROP_ORDER);
    try {
      ps.executeUpdate();
    } catch (SQLException e) {
      LOG.error(e);
      return false;
    } finally {
      closePrepareStatement(ps);
    }
    LOG.info("Table for entity is dropped");
    return true;
  }
}
