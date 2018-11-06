package net.javacogito.hibernate.model;

import net.javacogito.hibernate.controller.Controller;
import net.javacogito.hibernate.controller.CountryController;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Basic lass for 'country' table. Represents a single row.
 */

@javax.persistence.Entity
@Table(name = "country")
@SuppressWarnings("all")
public final class Country implements Serializable, Entity {
  private static final Controller CONTROLLER = CountryController.getCountryController();

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(name = "name", nullable = false)
  private String name;

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
   * Gets name.
   *
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   *
   * @param name country name.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets country controller.
   *
   * @return country controller
   */

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Country country = (Country) o;
    return Objects.equals(name, country.name);
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

  @Override public Controller getController() {
    return null;
  }
}
