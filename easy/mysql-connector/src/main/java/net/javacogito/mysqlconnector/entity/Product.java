package net.javacogito.mysqlconnector.entity;

import net.javacogito.mysqlconnector.controller.Controller;
import net.javacogito.mysqlconnector.controller.ProductController;

import java.io.Serializable;
import java.util.Objects;

/**
 * Basic lass for 'product' table. Represents a single row.
 */

public final class Product implements Serializable, Entity {
  private int id;
  private String name;
  private int productTypeId;
  private float price;

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
   * @param id primary key
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets product name.
   *
   * @return product name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets product name.
   *
   * @param name product name.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets Product Type Id.
   *
   * @return Product Type Id
   */
  public int getProductTypeId() {
    return productTypeId;
  }

  /**
   * Sets Product Type Id
   *
   * @param productTypeId Product Type Id
   */
  public void setProductTypeId(int productTypeId) {
    this.productTypeId = productTypeId;
  }

  /**
   * Gets product price.
   *
   * @return product price
   */
  public float getPrice() {
    return price;
  }

  /**
   * Sets product price.
   *
   * @param price product price
   */
  public void setPrice(float price) {
    this.price = price;
  }

  /**
   * Gets product controller.
   *
   * @return product controller
   */

  @Override public Controller getController() {
    return new ProductController();
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Product product = (Product) o;
    return id == product.id && productTypeId == product.productTypeId && Float.compare(product.price, price) == 0
        && Objects.equals(name, product.name);
  }

  @Override public int hashCode() {
    return Objects.hash(id, name, productTypeId, price);
  }

  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    sb.append("id=");
    sb.append(Integer.toString(id));
    sb.append(", ");
    sb.append("name=");
    sb.append(name);
    sb.append(", ");
    sb.append("product_type_id=");
    sb.append(Integer.toString(productTypeId));
    sb.append(", ");
    sb.append("price=");
    sb.append(Float.toString(price));
    sb.append("]");
    return sb.toString();
  }
}
