package net.javacogito.jdbcconnector.controller;

import net.javacogito.jdbcconnector.entity.ProductType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import static net.javacogito.jdbcconnector.util.EntityUtil.createProductType;

public class ProductTypeControllerTest extends AbstractControllerTest<ProductType, Integer>{

  @Test public void insertTest() {
    List<ProductType> productTypes = controller.getAll();
    Assert.assertEquals(3, productTypes.size());
    Assert.assertTrue(productTypes.contains(createProductType(1, "Movies")));
    Assert.assertTrue(productTypes.contains(createProductType(2, "Books")));
    Assert.assertTrue(productTypes.contains(createProductType(3, "Software")));
    Assert.assertFalse(productTypes.contains(createProductType(3, "No Name")));
  }

  @Test public void getEntityByIdTest() {
    Assert.assertEquals(createProductType(2, "Books"), controller.getEntityById(2));
  }

  @Test public void deleteTest() {
    Assert.assertTrue(controller.delete(2));
    List<ProductType> productTypes = controller.getAll();
    Assert.assertEquals(2, productTypes.size());
    Assert.assertFalse(productTypes.contains(createProductType(2, "Books")));
  }

  @Test public void updateTest() {
    ProductType productType = createProductType(2, "Electronics");
    Assert.assertTrue(controller.update(productType));
    Assert.assertEquals(productType, controller.getEntityById(2));
  }

  @Override protected void createController() {
    controller = new ProductTypeController();
  }

  @Override protected void insertData() {
    Assert.assertTrue(controller.insert(createProductType(1, "Movies")));
    Assert.assertTrue(controller.insert(createProductType(2, "Books")));
    Assert.assertTrue(controller.insert(createProductType(3, "Software")));
  }
}
