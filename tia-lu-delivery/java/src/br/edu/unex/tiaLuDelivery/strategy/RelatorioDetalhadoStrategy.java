package br.edu.unex.tiaLuDelivery.strategy;

import br.edu.unex.tiaLuDelivery.model.Order;
import br.edu.unex.tiaLuDelivery.model.OrderItem;
import br.edu.unex.tiaLuDelivery.repository.Repository;

import java.util.List;

public class RelatorioDetalhadoStrategy {

    public void gerarRelatorio() {
        Repository repo = Repository.getInstance();
        List<Order> orders = repo.getOrders();

        System.out.println("Pedidos do Dia:");

        if (orders == null || orders.isEmpty()) {
            System.out.println("Nenhum pedido registrado.");
            return;
        }

        for (Order o : orders) {
            System.out.println("Pedido ID: " + o.getId());
            System.out.println("Cliente: " + (o.getCustomer() != null ? o.getCustomer().getName() : "N/A"));
            System.out.println("Status: " + o.getStatus());
            System.out.println("Itens:");
            for (OrderItem item : o.getItems()) {
                System.out.println("- " + item.getMenuItem().getName()
                        + " x" + item.getAmount()
                        + " = " + item.subtotal());
            }
            System.out.println("Total: " + o.total());
            System.out.println("----------------------");
        }
    }
}
