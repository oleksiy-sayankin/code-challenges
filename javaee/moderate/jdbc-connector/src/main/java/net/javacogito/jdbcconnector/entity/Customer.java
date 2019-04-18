package net.javacogito.jdbcconnector.entity;

import net.javacogito.jdbcconnector.controller.Controller;
import net.javacogito.jdbcconnector.controller.CustomerController;

import java.io.Serializable;
import java.util.Objects;

/**
 * Basic lass for 'country' table. Represents a single row.
 */
public final class Customer implements Serializable, Entity {
  private int id;
  private String company;
  private String address;
  private Country country;
  private static final Controller CONTROLLER = CustomerController.getCustomerController();

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
   * Gets company.
   *
   * @return company
   */
  public String getCompany() {
    return company;
  }

  /**
   * Sets company.
   */
  public void setCompany(String company) {
    this.company = company;
  }

  /**
   * Gets address.
   *
   * @return address.
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets address.
   */
  public void setAddress(String address) {
    this.address = address;
  }


  /**
   * Gets country Id.
   *
   * @return address.
   */
  public Country getCountry() {
    return country;
  }

  /**
   * Sets country Id.
   */
  public void setCountry(Country country) {
    this.country = country;
  }

  /**
   * Gets customer controller.
   *
   * @return customer controller.
   */
  @Override public Controller getController() {
    return CONTROLLER;
  }


  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Customer customer = (Customer) o;
    return  Objects.equals(country, customer.country) && Objects.equals(company, customer.company) && Objects
        .equals(address, customer.address);
  }

  @Override public int hashCode() {
    return Objects.hash(id, company, address, country);
  }

  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    sb.append("id=");
    sb.append(Integer.toString(id));
    sb.append(", ");
    sb.append("company=");
    sb.append(company);
    sb.append(", ");
    sb.append("address=");
    sb.append(address);
    sb.append(", ");
    sb.append("country=");
    sb.append(country);
    sb.append("]");
    return sb.toString();
  }
}
