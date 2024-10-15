package org.example;

import org.example.manager.OrderManager;
import org.example.model.Order;
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
        when(scanner.nextLine()).thenReturn("Joan", "2", "TABLE-01", "0", "MENU-NIG", "MENU-NIG", "0");

        // Act
        boolean result = OrderManager.createOrder(scanner, restaurantDB);

        // Assert
        assertTrue(result);
        assertEquals(1, restaurantDB.getOrders().size());
        assertTrue(restaurantDB.getTables().get("TABLE-01").isBusy());
    }

    @Test
    void testCreateOrderMultipleTables() {
        // Arrange
        when(scanner.nextLine()).thenReturn("Joan", "4", "TABLE-01", "TABLE-02", "0", "MENU-NIG", "MENU-NIG", "MENU-VEG", "MENU-VEG", "0");

        // Act
        boolean result = OrderManager.createOrder(scanner, restaurantDB);

        // Assert
        assertTrue(result);
        assertEquals(1, restaurantDB.getOrders().size());
        assertTrue(restaurantDB.getTables().get("TABLE-01").isBusy());
        assertTrue(restaurantDB.getTables().get("TABLE-02").isBusy());
    }

    @Test
    void testCreateOrderInvalidPeopleQuantity() {
        // Arrange
        when(scanner.nextLine()).thenReturn("Joan", "-1", "2", "TABLE-02", "0", "MENU-VEG", "0");

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
        when(scanner.nextLine()).thenReturn("Joan", "2");
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

    @Test
    void testCalculateTotalPaymentWithSingleMenu() {
        // Arrange
        Order order = new Order();
        ArrayList<Menu> menus = new ArrayList<>();
        menus.add(new Menu("Test Menu", 100.0, "", true, true));
        order.setMenus(menus);

        // Act
        double result = OrderManager.calculateTotalPayment(order);

        // Assert
        assertEquals(121.0, result, 0.01); // 100 * 1.21 = 121
        assertEquals(121.0, order.getTotalPayment(), 0.01);
    }

    @Test
    void testCalculateTotalPaymentWithMultipleMenus() {
        // Arrange
        Order order = new Order();
        ArrayList<Menu> menus = new ArrayList<>();
        menus.add(new Menu("Menu 1", 50.0, "", true, true));
        menus.add(new Menu("Menu 2", 75.0, "", true, true));
        menus.add(new Menu("Menu 3", 25.0, "", true, true));
        order.setMenus(menus);

        // Act
        double result = OrderManager.calculateTotalPayment(order);

        // Assert
        assertEquals(181.5, result, 0.01); // (50 + 75 + 25) * 1.21 = 181.5
        assertEquals(181.5, order.getTotalPayment(), 0.01);
    }

    @Test
    void testCalculateIVAWithZero() {
        // Arrange
        double input = 0.0;

        // Act
        double result = OrderManager.calculateIVA(input);

        // Assert
        assertEquals(0.0, result, 0.01);
    }

    @Test
    void testCalculateIVAWithPositiveNumber() {
        // Arrange
        double input = 100.0;

        // Act
        double result = OrderManager.calculateIVA(input);

        // Assert
        assertEquals(121.0, result, 0.01); // 100 * 1.21 = 121
    }

    @Test
    void testCheckAvailableTables() {
        // Arrange
        // All tables are available by default after setUp()

        // Act
        int result = OrderManager.checkAvailableTables(restaurantDB);

        // Assert
        assertEquals(5, result);
    }

    @Test
    void testCheckAvailableTablesWithSomeBusy() {
        // Arrange
        restaurantDB.getTables().get("TABLE-01").setBusy(true);
        restaurantDB.getTables().get("TABLE-03").setBusy(true);

        // Act
        int result = OrderManager.checkAvailableTables(restaurantDB);

        // Assert
        assertEquals(3, result);
    }
}