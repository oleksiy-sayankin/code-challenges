package net.javacogito.jdbcconnector.controller;

import net.javacogito.jdbcconnector.entity.ProductType;
import org.junit.Test;

import java.util.List;

import static net.javacogito.jdbcconnector.util.EntityUtil.createProductType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class ProductTypeControllerTest extends AbstractControllerTest<ProductType, Integer>{

  @Test public void insertTest() {
    List<ProductType> productTypes = controller.getAll();
    assertEquals(3, productTypes.size());
    assertTrue(productTypes.contains(createProductType("Movies")));
    assertTrue(productTypes.contains(createProductType("Books")));
    assertTrue(productTypes.contains(createProductType("Software")));
    assertFalse(productTypes.contains(createProductType("No Name")));
  }

  @Test public void getEntityByIdTest() {
    assertEquals(createProductType(2, "Books"), controller.getEntityById(2));
  }

  @Test public void getIdByEntityTest() {
    assertEquals(new Integer(2), controller.getIdByEntity(createProductType("Books")));
  }


  @Test public void deleteTest() {
    assertTrue(controller.delete(2));
    List<ProductType> productTypes = controller.getAll();
    assertEquals(2, productTypes.size());
    assertFalse(productTypes.contains(createProductType("Books")));
  }

  @Test public void updateTest() {
    ProductType productType = createProductType(2, "Electronics");
    assertTrue(controller.update(productType));
    assertEquals(productType, controller.getEntityById(2));
  }

  @Override protected void createController() {
    controller = ProductTypeController.getProductTypeController();
  }

  @Override protected void insertData() {
    assertTrue(controller.insert(createProductType("Movies")));
    assertTrue(controller.insert(createProductType("Books")));
    assertTrue(controller.insert(createProductType("Software")));
  }
}
