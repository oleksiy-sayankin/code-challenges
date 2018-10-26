package net.javacogito.jdbcconnector.controller;

import net.javacogito.jdbcconnector.entity.Product;
import org.junit.Test;

import java.util.List;

import static net.javacogito.jdbcconnector.util.EntityUtil.createProduct;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


public class ProductControllerTest extends AbstractControllerTest<Product, Integer>{

  @Test public void insertTest() {
    List<Product> products = controller.getAll();
    assertEquals(3, products.size());
    assertTrue(products.contains(createProduct("Pepsi", 1, 3.44f)));
    assertTrue(products.contains(createProduct("Ice Cream", 2, 5.49f)));
    assertTrue(products.contains(createProduct("Personal Computer", 3, 702.44f)));
    assertFalse(products.contains(createProduct("No Name", 4, 322.21f)));
  }

  @Test public void getEntityByIdTest() {
    assertEquals(createProduct("Ice Cream", 2, 5.49f), controller.getEntityById(2));
  }

  @Test public void getIdByEntityTest() {
    assertEquals(new Integer(2), controller.getIdByEntity(createProduct("Ice Cream", 2, 5.49f)));
  }

  @Test public void deleteTest() {
    assertTrue(controller.delete(2));
    List<Product> products = controller.getAll();
    assertEquals(2, products.size());
    assertFalse(products.contains(createProduct("Ice Cream", 2, 5.49f)));
  }

  @Test public void updateTest() {
    Product product = createProduct(2, "Vegetables", 2, 33.97f);
    assertTrue(controller.update(product));
    assertEquals(product, controller.getEntityById(2));
  }

  @Override protected void createController() {
    controller = ProductController.getProductController();
  }

  @Override protected void insertData() {
    assertTrue(controller.insert(createProduct("Pepsi", 1, 3.44f)));
    assertTrue(controller.insert(createProduct("Ice Cream", 2, 5.49f)));
    assertTrue(controller.insert(createProduct("Personal Computer", 3, 702.44f)));
  }
}
