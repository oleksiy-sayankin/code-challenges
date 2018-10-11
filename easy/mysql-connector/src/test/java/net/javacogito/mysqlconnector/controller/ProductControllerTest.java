package net.javacogito.mysqlconnector.controller;

import net.javacogito.mysqlconnector.entity.Product;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
    Product product = new Product();

    List<Product> products = controller.getAll();

    Assert.assertEquals(3, products.size());

    product.setId(1);
    product.setName("Pepsi");
    product.setProductTypeId(1);
    product.setPrice(3.44f);
    Assert.assertTrue(products.contains(product));

    product.setId(2);
    product.setName("Ice Cream");
    product.setProductTypeId(2);
    product.setPrice(5.49f);
    Assert.assertTrue(products.contains(product));

    product.setId(3);
    product.setName("Personal Computer");
    product.setProductTypeId(3);
    product.setPrice(702.44f);
    Assert.assertTrue(products.contains(product));

    product.setId(4);
    product.setName("No Name");
    product.setProductTypeId(4);
    product.setPrice(322.21f);
    Assert.assertFalse(products.contains(product));
  }

  @Test public void getEntityById() {
    Product product = new Product();

    product.setId(2);
    product.setName("Ice Cream");
    product.setProductTypeId(2);
    product.setPrice(5.49f);
    Assert.assertEquals(product, controller.getEntityById(2));
  }

  @Test public void deleteTest() {
    Product product = new Product();

    Assert.assertTrue(controller.delete(2));
    List<Product> products = controller.getAll();

    Assert.assertEquals(2, products.size());

    product.setId(2);
    product.setName("Ice Cream");
    product.setProductTypeId(2);
    product.setPrice(5.49f);
    Assert.assertFalse(products.contains(product));
  }

  @Test public void updateTest() {
    Product product = new Product();

    product.setId(2);
    product.setName("Vegetables");
    product.setProductTypeId(2);
    product.setPrice(33.97f);
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
    Product product = new Product();

    product.setId(1);
    product.setName("Pepsi");
    product.setProductTypeId(1);
    product.setPrice(3.44f);
    Assert.assertTrue(controller.insert(product));

    product.setId(2);
    product.setName("Ice Cream");
    product.setProductTypeId(2);
    product.setPrice(5.49f);
    Assert.assertTrue(controller.insert(product));

    product.setId(3);
    product.setName("Personal Computer");
    product.setProductTypeId(3);
    product.setPrice(702.44f);
    Assert.assertTrue(controller.insert(product));
  }
}
