package org.example.home;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CustomersTest extends AbstractTest {

    @Test
    void testInsertCustomer() {
        try (Session session = getSession()) {
            session.createSQLQuery("INSERT INTO customers (first_name, last_name, phone_number, district, street, house, apartment) " +
                    "VALUES ('John', 'Doe', '+7 999 123 4567', 'North', 'Main Street', 10, 20);").executeUpdate();

            Long customerId = (Long) session.createSQLQuery("SELECT last_insert_rowid();").uniqueResult();
            assertNotNull(customerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testSelectCustomer() {
        try (Session session = getSession()) {
            session.createSQLQuery("INSERT INTO customers (first_name, last_name, phone_number, district, street, house, apartment) " +
                    "VALUES ('Alice', 'Smith', '+7 999 987 6543', 'South', 'First Street', 5, 15);").executeUpdate();

            CustomersEntity customer = session.get(CustomersEntity.class, 1);
            assertNotNull(customer);
            assertEquals("Alice", customer.getFirstName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testUpdateCustomer() {
        try (Session session = getSession()) {
            session.createSQLQuery("INSERT INTO customers (first_name, last_name, phone_number, district, street, house, apartment) " +
                    "VALUES ('Bob', 'Johnson', '+7 999 456 7890', 'East', 'Second Street', 15, 30);").executeUpdate();

            CustomersEntity customer = session.get(CustomersEntity.class, 1);
            assertNotNull(customer);

            customer.setDistrict("West");
            session.update(customer);

            CustomersEntity updatedCustomer = session.get(CustomersEntity.class, 1);
            assertNotNull(updatedCustomer);
            assertEquals("West", updatedCustomer.getDistrict());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDeleteCustomer() {
        try (Session session = getSession()) {
            session.createSQLQuery("INSERT INTO customers (first_name, last_name, phone_number, district, street, house, apartment) " +
                    "VALUES ('Eve', 'Brown', '+7 999 333 7777', 'West', 'Third Street', 20, 25);").executeUpdate();

            CustomersEntity customer = session.get(CustomersEntity.class, 1);
            assertNotNull(customer);

            session.delete(customer);

            CustomersEntity deletedCustomer = session.get(CustomersEntity.class, 1);
            assertNull(deletedCustomer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
