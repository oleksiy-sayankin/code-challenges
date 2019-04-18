package net.javacogito.jdbcconnector.controller;

import net.javacogito.jdbcconnector.entity.Product;
import net.javacogito.jdbcconnector.entity.ProductType;
import org.junit.Test;

import java.util.List;

import static net.javacogito.jdbcconnector.util.EntityUtil.createProduct;
import static net.javacogito.jdbcconnector.util.EntityUtil.createProductType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


public class ProductControllerTest extends AbstractControllerTest<Product, Integer>{
  private ProductType[] productTypes = new ProductType[3];

  @Test public void insertTest() {
    List<Product> products = controller.getAll();
    assertEquals(3, products.size());
    assertTrue(products.contains(createProduct("Pepsi", productTypes[0], 3.44f)));
    assertTrue(products.contains(createProduct("Ice Cream", productTypes[1], 5.49f)));
    assertTrue(products.contains(createProduct("Personal Computer", productTypes[2], 702.44f)));
    assertFalse(products.contains(createProduct("No Name", createProductType(4), 322.21f)));
  }

  @Test public void getEntityByIdTest() {
    assertEquals(createProduct("Ice Cream", productTypes[1], 5.49f), controller.getEntityById(2));
  }

  @Test public void getIdByEntityTest() {
    assertEquals(new Integer(2), controller.getIdByEntity(createProduct("Ice Cream", productTypes[1], 5.49f)));
  }

  @Test public void deleteTest() {
    assertTrue(controller.delete(2));
    List<Product> products = controller.getAll();
    assertEquals(2, products.size());
    assertFalse(products.contains(createProduct("Ice Cream", productTypes[1], 5.49f)));
  }

  @Test public void updateTest() {
    Product product = createProduct(2, "Vegetables", productTypes[1], 33.97f);
    assertEquals(new Integer(2), controller.update(product));
    assertEquals(product, controller.getEntityById(2));
  }

  @Override protected void createController() {
    controller = ProductController.getProductController();
  }

  @Override protected void insertData() {
    ProductTypeController ptc = ProductTypeController.getProductTypeController();
    ptc.drop();
    ptc.create();

    productTypes[0] = createProductType(1);
    productTypes[1] = createProductType(2);
    productTypes[2] = createProductType(3);

    for (ProductType productType : productTypes) {
      ptc.insert(productType);
    }

    assertEquals(new Integer(1), controller.insert(createProduct("Pepsi", productTypes[0], 3.44f)));
    assertEquals(new Integer(2), controller.insert(createProduct("Ice Cream", productTypes[1], 5.49f)));
    assertEquals(new Integer(3), controller.insert(createProduct("Personal Computer", productTypes[2], 702.44f)));
  }
}
