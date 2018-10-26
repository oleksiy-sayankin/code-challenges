package net.javacogito.jdbcconnector.controller;

import net.javacogito.jdbcconnector.entity.Order;
import org.junit.Test;

import java.util.List;

import static net.javacogito.jdbcconnector.util.EntityUtil.createOrder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class OrderControllerTest extends AbstractControllerTest<Order, Integer> {

  @Test public void insertCustomerTest() {
    List<Order> orders = controller.getAll();
    assertEquals(3, orders.size());
    assertTrue(orders.contains(createOrder(1,1,1)));
    assertTrue(orders.contains(createOrder(2,2,2)));
    assertTrue(orders.contains(createOrder(3,3,3)));
    assertFalse(orders.contains(createOrder(4,4,4)));
  }

  @Test public void getEntityByIdTest() {
    assertEquals(createOrder(2,2,2), controller.getEntityById(2));
  }

  @Test public void getIdByEntityTest() {
    assertEquals(new Integer(2), controller.getIdByEntity(createOrder(2,2,2)));
  }


  @Test public void deleteTest() {
    assertTrue(controller.delete(2));
    List<Order> orders = controller.getAll();
    assertEquals(2, orders.size());
    assertFalse(orders.contains(createOrder(2,2,2)));
  }

  @Test public void updateTest() {
    Order order = createOrder(2,20,20,20);
    assertTrue(controller.update(order));
    assertEquals(order, controller.getEntityById(2));
  }

  @Override protected void createController() {
    controller = OrderController.getOrderController();
  }

  @Override protected void insertData() {
    assertTrue(controller.insert(createOrder(1,1,1)));
    assertTrue(controller.insert(createOrder(2,2,2)));
    assertTrue(controller.insert(createOrder(3,3,3)));
  }
}
