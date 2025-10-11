package br.edu.unex.tiaLuDelivery.cli;

import br.edu.unex.tiaLuDelivery.model.Customer;
import br.edu.unex.tiaLuDelivery.model.MenuItem;
import br.edu.unex.tiaLuDelivery.model.Order;
import br.edu.unex.tiaLuDelivery.repository.Repository;
import br.edu.unex.tiaLuDelivery.strategy.RelatorioDetalhadoStrategy;
import br.edu.unex.tiaLuDelivery.strategy.RelatorioSimplificadoStrategy;

import java.math.BigDecimal;
import java.util.Scanner;

public class TiaLuCLI {

    public void start() {

        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("Menu:");
            System.out.println("1 - Executar teste de estratégias (popula dados e gera relatórios)");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    testStrategy();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while(option != 0);

    }

    // Professor criei esse método para facilitar o teste das estratégias
    private void testStrategy() {
        Repository repo = Repository.getInstance();
        // Limpa dados antigos para o teste ser determinístico
        repo.getCustomers().clear();
        repo.getMenuItems().clear();
        repo.getOrders().clear();

        // Criar itens do cardápio
        MenuItem m1 = new MenuItem("Hambúrguer", new BigDecimal("12.50"), 10);
        MenuItem m2 = new MenuItem("Refrigerante", new BigDecimal("4.00"), 20);
        repo.addMenuItem(m1);
        repo.addMenuItem(m2);

        // Criar clientes
        Customer c1 = new Customer("Ana", "99999-0001");
        Customer c2 = new Customer("Bruno", "99999-0002");
        repo.addCustomer(c1);
        repo.addCustomer(c2);

        // Criar pedidos
        Order o1 = c1.createOrder();
        o1.addItem(m1, 2);
        o1.addItem(m2, 1);
        repo.addOrder(o1);

        Order o2 = c2.createOrder();
        o2.addItem(m2, 3);
        repo.addOrder(o2);

        System.out.println("Dados de teste populados.");

        System.out.println("\n--- Relatório Simplificado ---");
        new RelatorioSimplificadoStrategy().gerarRelatorio();

        System.out.println("\n--- Relatório Detalhado ---");
        new RelatorioDetalhadoStrategy().gerarRelatorio();
    }
}
