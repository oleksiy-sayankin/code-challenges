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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getCountryId() {
    return countryId;
  }

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
