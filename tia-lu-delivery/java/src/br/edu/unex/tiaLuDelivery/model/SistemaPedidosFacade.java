package br.edu.unex.tiaLuDelivery.model;

import java.util.List;

/**
 * Fachada do sistema de pedidos.
 *
 * Esta classe expõe uma interface simplificada para que a CLI e outras camadas
 * do sistema possam interagir com clientes, pedidos e cardápio sem conhecer
 * os detalhes internos de implementação.
 *
 * OBS: Os métodos ainda não estão implementados, pois dependem dos padrões
 * que serão entregues por outras equipes (Repository, Builder, State etc).
 */
public class SistemaPedidosFacade {

    // Registrar novo cliente
    public Customer registrarNovoCliente(String nome, String telefone) {
        throw new UnsupportedOperationException("registrarNovoCliente ainda não implementado");
    }

    // Criar novo pedido
    public Order criarPedido(Customer cliente, List<OrderItem> itens) {
        throw new UnsupportedOperationException("criarPedido ainda não implementado");
    }

    // Listar cardápio
    public List<MenuItem> listarCardapio() {
        throw new UnsupportedOperationException("listarCardapio ainda não implementado");
    }

    // Exibir pedidos de um cliente
    public List<Order> exibirPedidosCliente(Customer cliente) {
        throw new UnsupportedOperationException("exibirPedidosCliente ainda não implementado");
    }
}
