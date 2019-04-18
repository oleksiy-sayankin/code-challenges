package net.javacogito.jdbcconnector.entity;

import net.javacogito.jdbcconnector.controller.Controller;
import net.javacogito.jdbcconnector.controller.OrderController;

import java.io.Serializable;
import java.util.Objects;

/**
 * Basic lass for 'order' table. Represents a single row.
 */

public final class Order implements Serializable, Entity {
  private int id;
  private Customer customer;
  private Product product;
  private int amount;
  private static final Controller CONTROLLER = OrderController.getOrderController();

  /**
   * Gets id.
   * @return id
   */
  public int getId() {
    return id;
  }

  /**
   * Sets id.
   * @param id primary key
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets customer Id.
   *
   * @return customer Id
   */
  public Customer getCustomer() {
    return customer;
  }

  /**
   * Sets customer Id.
   *
   * @param customer customer Id
   */
  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  /**
   * Gets product Id.
   *
   * @return product Id
   */
  public Product getProduct() {
    return product;
  }

  /**
   * Sets product Id.
   *
   * @param product product Id
   */
  public void setProduct(Product product) {
    this.product = product;
  }

  /**
   * Get amount of product in the order.
   *
   * @return amount of product in the order
   */
  public int getAmount() {
    return amount;
  }

  /**
   * Set amount of product in the order.
   *
   * @param amount amount of product in the order
   */
  public void setAmount(int amount) {
    this.amount = amount;
  }

  /**
   * Gets order controller.
   *
   * @return order controller.
   */
  @Override public Controller getController() {
    return CONTROLLER;
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Order order = (Order) o;
    return Objects.equals(customer, order.customer) && Objects.equals(product, order.product) && amount == order.amount;
  }

  @Override public int hashCode() {
    return Objects.hash(id, customer, product, amount);
  }

  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    sb.append("id=");
    sb.append(Integer.toString(id));
    sb.append(", ");
    sb.append("customer_id=");
    sb.append(customer);
    sb.append(", ");
    sb.append("product=");
    sb.append(product);
    sb.append(", ");
    sb.append("amount=");
    sb.append(Integer.toString(amount));
    sb.append("]");
    return sb.toString();
  }
}
