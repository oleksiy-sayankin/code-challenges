package net.javacogito.mysqlconnector.controller;

import net.javacogito.mysqlconnector.entity.ProductType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ProductTypeControllerTest {
  private Controller<ProductType, Integer> controller;

  @Before public void init() {
    createTable();
    insertData();
  }

  @After public void close() {
    controller.drop();
  }

  @Test public void insertTest() {
    ProductType productType = new ProductType();

    List<ProductType> productTypes = controller.getAll();

    Assert.assertEquals(3, productTypes.size());

    productType.setId(1);
    productType.setName("Movies");
    Assert.assertTrue(productTypes.contains(productType));

    productType.setId(2);
    productType.setName("Books");
    Assert.assertTrue(productTypes.contains(productType));

    productType.setId(3);
    productType.setName("Software");
    Assert.assertTrue(productTypes.contains(productType));

    productType.setId(4);
    productType.setName("No Name");
    Assert.assertFalse(productTypes.contains(productType));
  }

  @Test public void getEntityById() {
    ProductType productType = new ProductType();
    productType.setId(2);
    productType.setName("Books");
    Assert.assertEquals(productType, controller.getEntityById(2));
  }

  @Test public void deleteTest() {
    ProductType productType = new ProductType();

    Assert.assertTrue(controller.delete(2));
    List<ProductType> productTypes = controller.getAll();

    Assert.assertEquals(2, productTypes.size());

    productType.setId(2);
    productType.setName("Books");
    Assert.assertFalse(productTypes.contains(productType));
  }

  @Test public void updateTest() {
    ProductType productType = new ProductType();

    productType.setId(2);
    productType.setName("Electronics");
    Assert.assertTrue(controller.update(productType));
    Assert.assertEquals(productType, controller.getEntityById(2));
  }

  private void createTable() {
    controller = new ProductTypeController();
    if (!controller.create()) {
      throw new IllegalArgumentException("Can not create table.");
    }
  }

  private void insertData() {
    ProductType productType = new ProductType();

    productType.setId(1);
    productType.setName("Movies");
    Assert.assertTrue(controller.insert(productType));

    productType.setId(2);
    productType.setName("Books");
    Assert.assertTrue(controller.insert(productType));

    productType.setId(3);
    productType.setName("Software");
    Assert.assertTrue(controller.insert(productType));
  }
}
