package org.example.home;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OrdersProductsTest extends AbstractTest {

    @Test
    void testSelectOrderProduct() {
        try (Session session = getSession()) {
            // Предварительная вставка данных для выборки
            session.createSQLQuery("INSERT INTO orders_products (order_id, product_id, quantity) " +
                    "VALUES (2, 3, 1);").executeUpdate();

            // Выборка данных и проверка
            OrdersProductsEntity orderProduct = session.get(OrdersProductsEntity.class, new OrdersProductsIdEntity((long) 2, (long) 3));
            assertNotNull(orderProduct);
            assertEquals(1, orderProduct.getQuantity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testUpdateOrderProduct() {
        try (Session session = getSession()) {
            // Предварительная вставка данных для обновления
            session.createSQLQuery("INSERT INTO orders_products (order_id, product_id, quantity) " +
                    "VALUES (3, 4, 2);").executeUpdate();

            // Обновление данных и проверка
            OrdersProductsEntity orderProduct = session.get(OrdersProductsEntity.class, new OrdersProductsIdEntity((long) 3, (long) 4));
            assertNotNull(orderProduct);

            // Явное приведение типа для установки значения quantity
            orderProduct.setQuantity((short) 3);
            session.update(orderProduct);

            OrdersProductsEntity updatedOrderProduct = session.get(OrdersProductsEntity.class, new OrdersProductsIdEntity((long) 3, (long) 4));
            assertNotNull(updatedOrderProduct);
            assertEquals(3, updatedOrderProduct.getQuantity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDeleteOrderProduct() {
        try (Session session = getSession()) {
            // Предварительная вставка данных для удаления
            session.createSQLQuery("INSERT INTO orders_products (order_id, product_id, quantity) " +
                    "VALUES (4, 5, 3);").executeUpdate();

            // Удаление данных и проверка
            OrdersProductsEntity orderProduct = session.get(OrdersProductsEntity.class, new OrdersProductsIdEntity((long) 4, (long) 5));
            assertNotNull(orderProduct);

            session.delete(orderProduct);

            OrdersProductsEntity deletedOrderProduct = session.get(OrdersProductsEntity.class, new OrdersProductsIdEntity((long) 4, (long) 5));
            assertNull(deletedOrderProduct);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}