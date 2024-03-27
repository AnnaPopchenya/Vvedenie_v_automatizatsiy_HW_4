package org.example.home;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OrdersTest extends AbstractTest {

    @Test
    void testInsertOrder() {
        try (Session session = getSession()) {
            session.createSQLQuery("INSERT INTO orders (customer_id, date_get) VALUES (1, datetime('now'));").executeUpdate();

            Long orderId = (Long) session.createSQLQuery("SELECT last_insert_rowid();").uniqueResult();
            assertNotNull(orderId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testSelectOrder() {
        try (Session session = getSession()) {
            session.createSQLQuery("INSERT INTO orders (customer_id, date_get) VALUES (2, datetime('now'));").executeUpdate();

            OrdersEntity order = session.get(OrdersEntity.class, 1);
            assertNotNull(order);
            assertEquals(2, order.getCustomerId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testUpdateOrder() {
        try (Session session = getSession()) {
            session.createSQLQuery("INSERT INTO orders (customer_id, date_get) VALUES (3, datetime('now'));").executeUpdate();

            OrdersEntity order = session.get(OrdersEntity.class, 1);
            assertNotNull(order);

            order.setCustomerId(4);
            session.update(order);

            OrdersEntity updatedOrder = session.get(OrdersEntity.class, 1);
            assertNotNull(updatedOrder);
            assertEquals(4, updatedOrder.getCustomerId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDeleteOrder() {
        try (Session session = getSession()) {
            session.createSQLQuery("INSERT INTO orders (customer_id, date_get) VALUES (5, datetime('now'));").executeUpdate();

            OrdersEntity order = session.get(OrdersEntity.class, 1);
            assertNotNull(order);

            session.delete(order);

            OrdersEntity deletedOrder = session.get(OrdersEntity.class, 1);
            assertNull(deletedOrder); // Заменить assertNull на Assertions.assertNull
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
