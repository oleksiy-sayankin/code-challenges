package net.javacogito.mysqlconnector.entity;

import java.util.Objects;

/**
 * Basic lass for 'order' table. Represents a single row.
 */

public final class Order {
  private int id;
  private int customerId;
  private int productId;
  private int amount;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  public int getProductId() {
    return productId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Order order = (Order) o;
    return id == order.id && customerId == order.customerId && productId == order.productId && amount == order.amount;
  }

  @Override public int hashCode() {
    return Objects.hash(id, customerId, productId, amount);
  }

  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    sb.append("id=");
    sb.append(Integer.toString(id));
    sb.append(", ");
    sb.append("customer_id=");
    sb.append(Integer.toString(customerId));
    sb.append(", ");
    sb.append("product_id=");
    sb.append(productId);
    sb.append(", ");
    sb.append("amount=");
    sb.append(Integer.toString(amount));
    sb.append("]");
    return sb.toString();
  }
}
