package br.com.alurafood.pedidos.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class PedidoProducer {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public void enviarMensagens(){

      kafkaTemplate.send("ECOMMERCE_NOVO_PEDIDO", "mensagem");

    }

}
