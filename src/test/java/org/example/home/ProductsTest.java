package org.example.home;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProductsTest extends AbstractTest {

    @Test
    void testInsertProduct() {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();

            ProductsEntity newProduct = new ProductsEntity();
            newProduct.setMenuName("MISO SOUP");
            newProduct.setPrice("150.00");

            session.save(newProduct);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetProduct() {
        try (Session session = getSession()) {
            ProductsEntity product = session.get(ProductsEntity.class, 1);

            assertNotNull(product);
            assertEquals("GOJIRA ROLL", product.getMenuName());
            assertEquals("300.0", product.getPrice());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testUpdateProduct() {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();

            ProductsEntity product = session.get(ProductsEntity.class, 1);
            product.setPrice("350.0");

            session.update(product);

            transaction.commit();

            // Проверяем обновленную цену продукта
            ProductsEntity updatedProduct = session.get(ProductsEntity.class, 1);
            assertEquals("350.0", updatedProduct.getPrice());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDeleteProduct() {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();

            ProductsEntity product = session.get(ProductsEntity.class, 1);
            session.delete(product);

            transaction.commit();

            ProductsEntity deletedProduct = session.get(ProductsEntity.class, 1);
            assertNull(deletedProduct);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
