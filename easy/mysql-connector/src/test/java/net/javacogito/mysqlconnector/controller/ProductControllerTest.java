package net.javacogito.mysqlconnector.controller;

import net.javacogito.mysqlconnector.entity.Product;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import static net.javacogito.mysqlconnector.util.EntityUtil.createProduct;

public class ProductControllerTest {
  private Controller<Product, Integer> controller;

  @Before public void init() {
    createTable();
    insertData();
  }

  @After public void close() {
    controller.drop();
  }

  @Test public void insertTest() {
    List<Product> products = controller.getAll();
    Assert.assertEquals(3, products.size());
    Assert.assertTrue(products.contains(createProduct(1, "Pepsi", 1, 3.44f)));
    Assert.assertTrue(products.contains(createProduct(2, "Ice Cream", 2, 5.49f)));
    Assert.assertTrue(products.contains(createProduct(3, "Personal Computer", 3, 702.44f)));
    Assert.assertFalse(products.contains(createProduct(4, "No Name", 4, 322.21f)));
  }

  @Test public void getEntityByIdTest() {
    Assert.assertEquals(createProduct(2, "Ice Cream", 2, 5.49f), controller.getEntityById(2));
  }

  @Test public void deleteTest() {
    Assert.assertTrue(controller.delete(2));
    List<Product> products = controller.getAll();
    Assert.assertEquals(2, products.size());
    Assert.assertFalse(products.contains(createProduct(2, "Ice Cream", 2, 5.49f)));
  }

  @Test public void updateTest() {
    Product product = createProduct(2, "Vegetables", 2, 33.97f);
    Assert.assertTrue(controller.update(product));
    Assert.assertEquals(product, controller.getEntityById(2));
  }

  private void createTable() {
    controller = new ProductController();
    if (!controller.create()) {
      throw new IllegalArgumentException("Can not create table.");
    }
  }

  private void insertData() {
    Assert.assertTrue(controller.insert(createProduct(1, "Pepsi", 1, 3.44f)));
    Assert.assertTrue(controller.insert(createProduct(2, "Ice Cream", 2, 5.49f)));
    Assert.assertTrue(controller.insert(createProduct(3, "Personal Computer", 3, 702.44f)));
  }
}
