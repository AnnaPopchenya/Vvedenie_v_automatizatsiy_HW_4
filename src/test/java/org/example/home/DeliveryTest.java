package org.example.home;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeliveryTest extends AbstractTest {

    @Test
    void testInsertDelivery() {
        try (Session session = getSession()) {
            DeliveryEntity delivery = new DeliveryEntity();
            delivery.setOrderId(1);
            delivery.setCourierId(3);
            delivery.setDateArrived("2023-02-26 17:59:15");
            delivery.setTaken("Yes");
            delivery.setPaymentMethod("Cash");

            session.save(delivery);

            Long count = (Long) session.createQuery("SELECT COUNT(*) FROM DeliveryEntity").uniqueResult();
            assertEquals(15, count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testSelectDelivery() {
        try (Session session = getSession()) {
            DeliveryEntity delivery = session.get(DeliveryEntity.class, (short) 1);
            assertNotNull(delivery);
            assertEquals(3, delivery.getCourierId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testUpdateDelivery() {
        try (Session session = getSession()) {
            DeliveryEntity delivery = session.get(DeliveryEntity.class, (short) 1);
            assertNotNull(delivery);

            delivery.setTaken("No");
            session.update(delivery);

            DeliveryEntity updatedDelivery = session.get(DeliveryEntity.class, (short) 1);
            assertNotNull(updatedDelivery);
            assertEquals("No", updatedDelivery.getTaken());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDeleteDelivery() {
        try (Session session = getSession()) {
            DeliveryEntity delivery = session.get(DeliveryEntity.class, (short) 1);
            assertNotNull(delivery);

            session.delete(delivery);

            DeliveryEntity deletedDelivery = session.get(DeliveryEntity.class, (short) 1);
            assertNull(deletedDelivery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}