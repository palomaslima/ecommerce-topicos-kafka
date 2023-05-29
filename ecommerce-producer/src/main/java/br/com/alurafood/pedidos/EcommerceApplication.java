package br.com.alurafood.pedidos;

import br.com.alurafood.pedidos.messaging.PedidoProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "br.com.alurafood.pedidos")
@ComponentScan(basePackages = "br.com.alurafood.pedidos")
public class EcommerceApplication {

	static PedidoProducer pedidoProducer = new PedidoProducer();

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
		pedidoProducer.enviarMensagens();
	}

}
