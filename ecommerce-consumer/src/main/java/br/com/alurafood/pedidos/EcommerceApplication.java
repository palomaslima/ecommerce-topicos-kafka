package br.com.alurafood.pedidos;

import br.com.alurafood.pedidos.messaging.PedidoConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceApplication {

	static PedidoConsumer pedidoConsumer = new PedidoConsumer();

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

}
