package org.example;

import org.example.manager.OrderManager;
import org.example.model.Table;
import org.example.model.Menu;
import org.example.repository.RestaurantDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateOrderTest {

    @Mock
    private Scanner scanner;

    private RestaurantDB restaurantDB;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurantDB = new RestaurantDB();
        restaurantDB.setName("Plaça Catalunya Restaurant");
        restaurantDB.setSize(10);
        createFakeMenus(restaurantDB);
        createFakeTables(restaurantDB);
    }

    private void createFakeTables(RestaurantDB r1) {
        Table t1 = new Table("Table 01", "Table type Modern", 4, false);
        Table t2 = new Table("Table 02", "Table type Modern", 2, false);
        Table t3 = new Table("Table 03", "Table type Modern", 2, false);
        Table t4 = new Table("Table 04", "Table type Modern", 4, false);
        Table t5 = new Table("Table 05", "Table type Modern", 4, false);

        r1.getTables().put("TABLE-01", t1);
        r1.getTables().put("TABLE-02", t2);
        r1.getTables().put("TABLE-03", t3);
        r1.getTables().put("TABLE-04", t4);
        r1.getTables().put("TABLE-05", t5);
    }

    private void createFakeMenus(RestaurantDB r1) {
        Menu m1 = new Menu("Menu Night", 8.5, "", true, true);
        Menu m2 = new Menu("Menu Vegan", 10.5, "", true, true);
        Menu m3 = new Menu("Menu Kids", 12.5, "", true, true);

        r1.getMenus().put("MENU-NIG", m1);
        r1.getMenus().put("MENU-VEG", m2);
        r1.getMenus().put("MENU-KID", m3);
    }

    @Test
    void testCreateOrderSuccessful() {
        // Arrange
        when(scanner.nextLine()).thenReturn( "Joan", "2", "TABLE-01", "MENU-NIG", "MENU-NIG", "MENU-NIG", "0");

        // Act
        boolean result = OrderManager.createOrder(scanner, restaurantDB);

        // Assert
        assertTrue(result);
        assertEquals(1, restaurantDB.getOrders().size());
        assertTrue(restaurantDB.getTables().get("TABLE-01").isBusy());
    }

    @Test
    void testCreateOrderFilled() {
        // Arrange
        when(scanner.nextLine()).thenReturn("Joan", "2", "TABLE-01", "MENU-NIG", "MENU-NIG", "MENU-NIG", "0");

        // Act
        boolean result = OrderManager.createOrder(scanner, restaurantDB);

        // Assert
        assertTrue(result);
        assertEquals(2, restaurantDB.getOrders().size());
        assertTrue(restaurantDB.getTables().get("TABLE-01").isBusy());
    }

    @Test
    void testCreateOrderInvalidPeopleQuantity() {
        // Arrange
        when(scanner.nextLine()).thenReturn("Jane", "-1", "2", "TABLE-02", "MENU-VEG", "n");

        // Act
        boolean result = OrderManager.createOrder(scanner, restaurantDB);

        // Assert
        assertTrue(result);
        assertEquals(1, restaurantDB.getOrders().size());
        assertTrue(restaurantDB.getTables().get("TABLE-02").isBusy());
    }

    @Test
    void testCreateOrderNoTableAvailable() {
        // Arrange
        when(scanner.nextLine()).thenReturn("Alice", "2");
        // Set all tables as busy
        for (Table table : restaurantDB.getTables().values()) {
            table.setBusy(true);
        }

        // Act
        boolean result = OrderManager.createOrder(scanner, restaurantDB);

        // Assert
        assertFalse(result);
        assertTrue(restaurantDB.getOrders().isEmpty());
    }
}