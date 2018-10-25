package net.javacogito.jdbcconnector.entity;

import net.javacogito.jdbcconnector.controller.Controller;
import net.javacogito.jdbcconnector.controller.ProductTypeController;

import java.io.Serializable;
import java.util.Objects;

/**
 * Basic lass for 'product_type' table. Represents a single row.
 */
public final class ProductType implements Serializable, Entity {
  private int id;
  private String name;
  private static final Controller CONTROLLER = ProductTypeController.getProductTypeController();

  /**
   * Gets id.
   *
   * @return id
   */
  public int getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id product type id
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets name.
   *
   * @return product type name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   *
   * @param name product type name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets product type controller.
   *
   * @return product type controller
   */
  @Override public Controller getController() {
    return CONTROLLER;
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    ProductType that = (ProductType) o;
    return Objects.equals(name, that.name);
  }

  @Override public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    sb.append("id=");
    sb.append(Integer.toString(id));
    sb.append(", ");
    sb.append("name=");
    sb.append(name);
    sb.append("]");
    return sb.toString();
  }
}
