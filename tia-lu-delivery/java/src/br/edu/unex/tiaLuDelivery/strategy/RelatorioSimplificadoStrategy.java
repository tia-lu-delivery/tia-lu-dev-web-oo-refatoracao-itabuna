package br.edu.unex.tiaLuDelivery.strategy;

import br.edu.unex.tiaLuDelivery.model.Order;
import br.edu.unex.tiaLuDelivery.repository.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class RelatorioSimplificadoStrategy {

    public void gerarRelatorio() {
        Repository repo = Repository.getInstance();
        List<Order> orders = repo.getOrders();

        int totalPedidos = orders == null ? 0 : orders.size();
        BigDecimal totalValor = BigDecimal.ZERO;

        if (orders != null) {
            for (Order o : orders) {
                if (o != null && o.total() != null) {
                    totalValor = totalValor.add(o.total());
                }
            }
        }

        System.out.println("Resumo do Dia:");
        System.out.println("Total de pedidos: " + totalPedidos);
        System.out.println("Valor total arrecadado: R$ " + totalValor.setScale(2, RoundingMode.HALF_EVEN));
    }
}
