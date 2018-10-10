package net.javacogito.mysqlconnector.entity;

import java.util.Objects;

/**
 * Basic lass for 'country' table. Represents a single row.
 */
public final class Customer {
  private int id;
  private String company;
  private String address;
  private int countryId;

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
   * Gets company.
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
   * @return address.
   */
  public int getCountryId() {
    return countryId;
  }

  /**
   * Sets country Id.
   */
  public void setCountryId(int countryId) {
    this.countryId = countryId;
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Customer customer = (Customer) o;
    return id == customer.id && countryId == customer.countryId && Objects.equals(company, customer.company) && Objects
        .equals(address, customer.address);
  }

  @Override public int hashCode() {
    return Objects.hash(id, company, address, countryId);
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
    sb.append("]");
    return sb.toString();
  }
}
