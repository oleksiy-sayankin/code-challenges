package net.javacogito.jdbcconnector.controller;

import net.javacogito.jdbcconnector.entity.Order;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import static net.javacogito.jdbcconnector.util.EntityUtil.createOrder;

public class OrderControllerTest extends AbstractControllerTest<Order, Integer> {

  @Test public void insertCustomerTest() {
    List<Order> orders = controller.getAll();
    Assert.assertEquals(3, orders.size());
    Assert.assertTrue(orders.contains(createOrder(1,1,1,1)));
    Assert.assertTrue(orders.contains(createOrder(2,2,2,2)));
    Assert.assertTrue(orders.contains(createOrder(3,3,3,3)));
    Assert.assertFalse(orders.contains(createOrder(4,4,4,4)));
  }

  @Test public void getEntityByIdTest() {
    Assert.assertEquals(createOrder(2,2,2,2), controller.getEntityById(2));
  }

  @Test public void deleteTest() {
    Assert.assertTrue(controller.delete(2));
    List<Order> orders = controller.getAll();
    Assert.assertEquals(2, orders.size());
    Assert.assertFalse(orders.contains(createOrder(2,2,2,2)));
  }

  @Test public void updateTest() {
    Order order = createOrder(2,20,20,20);
    Assert.assertTrue(controller.update(order));
    Assert.assertEquals(order, controller.getEntityById(2));
  }

  @Override protected void createController() {
    controller = new OrderController();
  }

  @Override protected void insertData() {
    Assert.assertTrue(controller.insert(createOrder(1,1,1,1)));
    Assert.assertTrue(controller.insert(createOrder(2,2,2,2)));
    Assert.assertTrue(controller.insert(createOrder(3,3,3,3)));
  }
}
