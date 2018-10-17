package net.javacogito.jdbcconnector.controller;

import net.javacogito.jdbcconnector.entity.Product;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import static net.javacogito.jdbcconnector.util.EntityUtil.createProduct;

public class ProductControllerTest extends AbstractControllerTest<Product, Integer>{

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

  @Override protected void createController() {
    controller = ProductController.getProductController();
  }

  @Override protected void insertData() {
    Assert.assertTrue(controller.insert(createProduct(1, "Pepsi", 1, 3.44f)));
    Assert.assertTrue(controller.insert(createProduct(2, "Ice Cream", 2, 5.49f)));
    Assert.assertTrue(controller.insert(createProduct(3, "Personal Computer", 3, 702.44f)));
  }
}
