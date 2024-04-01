package org.example.home;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CourierInfoTest extends AbstractTest {

    @Test
    void testInsertCourierInfo() {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();

            CourierInfoEntity newCourier = new CourierInfoEntity();
            //тут надо установить еще courierId
            newCourier.setFirstName("Alice");
            newCourier.setLastName("Smith");
            newCourier.setPhoneNumber("+123456789");
            newCourier.setDeliveryType("car");

            session.save(newCourier);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetCourierInfo() {
        try (Session session = getSession()) {
            //тут courier не будет получен, он будет NULL
            CourierInfoEntity courier = session.get(CourierInfoEntity.class, (short) 1);

            assertNotNull(courier);
            assertEquals("John", courier.getFirstName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testUpdateCourierInfo() {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();

            //тут courier не будет получен, он будет NULL
            CourierInfoEntity courier = session.get(CourierInfoEntity.class, (short) 1);
            courier.setPhoneNumber("+987654321");

            session.update(courier);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDeleteCourierInfo() {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();

            //тут courier не будет получен, он будет NULL
            CourierInfoEntity courier = session.get(CourierInfoEntity.class, (short) 1);
            session.delete(courier);

            transaction.commit();

            CourierInfoEntity deletedCourier = session.get(CourierInfoEntity.class, (short) 1);
            assertNull(deletedCourier);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
