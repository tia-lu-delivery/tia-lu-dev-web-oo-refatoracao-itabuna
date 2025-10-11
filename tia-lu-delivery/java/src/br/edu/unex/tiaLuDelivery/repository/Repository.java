package br.edu.unex.tiaLuDelivery.repository;

import br.edu.unex.tiaLuDelivery.model.Customer;
import br.edu.unex.tiaLuDelivery.model.MenuItem;
import br.edu.unex.tiaLuDelivery.model.Order;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    private static Repository instancia;

    private final List<Customer> customers;
    private final List<MenuItem> menuItems;
    private final List<Order> orders;

    private Repository() {
        this.customers = new ArrayList<>();
        this.menuItems = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    public static synchronized Repository getInstance() {
        if (instancia == null) {
            instancia = new Repository();
        }
        return instancia;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addCustomer(Customer customer) {
        if (customer != null) customers.add(customer);
    }

    public void addMenuItem(MenuItem menuItem) {
        if (menuItem != null) menuItems.add(menuItem);
    }

    public void addOrder(Order order) {
        if (order != null) orders.add(order);
    }
}
