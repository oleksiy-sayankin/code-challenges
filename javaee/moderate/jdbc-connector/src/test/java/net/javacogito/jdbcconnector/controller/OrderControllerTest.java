package net.javacogito.jdbcconnector.controller;

import net.javacogito.jdbcconnector.entity.Country;
import net.javacogito.jdbcconnector.entity.Customer;
import net.javacogito.jdbcconnector.entity.Order;
import net.javacogito.jdbcconnector.entity.Product;
import net.javacogito.jdbcconnector.entity.ProductType;
import org.junit.Test;

import java.util.List;

import static net.javacogito.jdbcconnector.util.EntityUtil.createCountry;
import static net.javacogito.jdbcconnector.util.EntityUtil.createCustomer;
import static net.javacogito.jdbcconnector.util.EntityUtil.createOrder;

import static net.javacogito.jdbcconnector.util.EntityUtil.createProduct;
import static net.javacogito.jdbcconnector.util.EntityUtil.createProductType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class OrderControllerTest extends AbstractControllerTest<Order, Integer> {
  private Customer[] customers = new Customer[3];
  private Product[] products = new Product[3];
  private ProductType[] productTypes = new ProductType[3];

  @Test public void insertCustomerTest() {
    List<Order> orders = controller.getAll();
    assertEquals(3, orders.size());
    assertTrue(orders.contains(createOrder(customers[0], products[0],1)));
    assertTrue(orders.contains(createOrder(customers[1], products[1],2)));
    assertTrue(orders.contains(createOrder(customers[2], products[2],3)));
    assertFalse(orders.contains(createOrder(createCustomer(4), createProduct(4),4)));
  }

  @Test public void getEntityByIdTest() {
    assertEquals(createOrder(customers[1], products[1],2), controller.getEntityById(2));
  }

  @Test public void getIdByEntityTest() {
    assertEquals(new Integer(2), controller.getIdByEntity(createOrder(customers[1], products[1],2)));
  }


  @Test public void deleteTest() {
    assertTrue(controller.delete(2));
    List<Order> orders = controller.getAll();
    assertEquals(2, orders.size());
    assertFalse(orders.contains(createOrder(customers[1], products[1],2)));
  }

  @Test public void updateTest() {
    Order order = createOrder(2,createCustomer(20),createProduct(20),20);
    assertEquals(new Integer(2), controller.update(order));
    assertEquals(order, controller.getEntityById(2));
  }

  @Override protected void createController() {
    controller = OrderController.getOrderController();
  }

  @Override protected void insertData() {
    CountryController  coc = CountryController.getCountryController();
    coc.drop();
    coc.create();

    Country country = createCountry(1, "Ukraine");
    coc.insert(country);

    CustomerController cuc = CustomerController.getCustomerController();
    cuc.drop();
    cuc.create();

    customers[0] = createCustomer(1, "Company A", "Address 1", country);
    customers[1] = createCustomer(2, "Company B", "Address 2", country);
    customers[2] = createCustomer(3, "Company C", "Address 3", country);

    for (Customer customer : customers) {
      cuc.insert(customer);
    }

    ProductTypeController ptc = ProductTypeController.getProductTypeController();
    ptc.drop();
    ptc.create();

    productTypes[0] = createProductType(1);
    productTypes[1] = createProductType(2);
    productTypes[2] = createProductType(3);

    for (ProductType productType : productTypes) {
      ptc.insert(productType);
    }

    ProductController pc = ProductController.getProductController();
    pc.create();

    products[0] = createProduct(1, "Product A", productTypes[0], 1.4f);
    products[1] = createProduct(2, "Product B", productTypes[1], 2.4f);
    products[2] = createProduct(3, "Product C", productTypes[2], 3.4f);

    for (Product product : products) {
      pc.insert(product);
    }

    assertEquals(new Integer(1), controller.insert(createOrder(customers[0], products[0],1)));
    assertEquals(new Integer(2), controller.insert(createOrder(customers[1], products[1],2)));
    assertEquals(new Integer(3), controller.insert(createOrder(customers[2], products[2],3)));
  }
}
