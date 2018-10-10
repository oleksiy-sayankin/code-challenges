package net.javacogito.mysqlconnector.controller;

import net.javacogito.mysqlconnector.entity.Order;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class OrderControllerTest {
  private Controller<Order, Integer> controller;

  @Before public void init() {
    createTable();
    insertData();
  }

  @After public void close() {
    controller.drop();
  }

  @Test public void insertCustomerTest() {
    Order order = new Order();

    List<Order> orders = controller.getAll();

    Assert.assertEquals(3, orders.size());

    order.setId(1);
    order.setCustomerId(1);
    order.setProductId(1);
    order.setAmount(1);
    Assert.assertTrue(orders.contains(order));

    order.setId(2);
    order.setCustomerId(2);
    order.setProductId(2);
    order.setAmount(2);
    Assert.assertTrue(orders.contains(order));

    order.setId(3);
    order.setCustomerId(3);
    order.setProductId(3);
    order.setAmount(3);
    Assert.assertTrue(orders.contains(order));

    order.setId(4);
    order.setCustomerId(4);
    order.setProductId(4);
    order.setAmount(4);
    Assert.assertFalse(orders.contains(order));
  }

  @Test public void getEntityById() {
    Order order = new Order();

    order.setId(2);
    order.setCustomerId(2);
    order.setProductId(2);
    order.setAmount(2);
    Assert.assertEquals(order, controller.getEntityById(2));
  }

  @Test public void deleteTest() {
    Order order = new Order();

    Assert.assertTrue(controller.delete(2));
    List<Order> orders = controller.getAll();

    Assert.assertEquals(2, orders.size());

    order.setId(2);
    order.setCustomerId(2);
    order.setProductId(2);
    order.setAmount(2);
    Assert.assertFalse(orders.contains(order));
  }

  @Test public void updateTest() {
    Order order = new Order();

    order.setId(2);
    order.setCustomerId(2);
    order.setProductId(2);
    order.setAmount(2);
    Assert.assertTrue(controller.update(order));
    Assert.assertEquals(order, controller.getEntityById(2));
  }

  private void createTable() {
    controller = new OrderController();
    if (!controller.create()) {
      throw new IllegalArgumentException("Can not create table.");
    }
  }

  private void insertData() {
    Order order = new Order();
    order.setId(1);
    order.setCustomerId(1);
    order.setProductId(1);
    order.setAmount(1);
    Assert.assertTrue(controller.insert(order));

    order.setId(2);
    order.setCustomerId(2);
    order.setProductId(2);
    order.setAmount(2);
    Assert.assertTrue(controller.insert(order));

    order.setId(3);
    order.setCustomerId(3);
    order.setProductId(3);
    order.setAmount(3);
    Assert.assertTrue(controller.insert(order));
  }
}
