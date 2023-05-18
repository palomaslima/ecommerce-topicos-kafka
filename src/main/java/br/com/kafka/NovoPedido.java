package br.com.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NovoPedido {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        String mensagemSucesso = "Mensagem enviada com sucesso: ";

        var producer = new KafkaProducer<String, String>(properties());
        String key = UUID.randomUUID().toString();
        String mensagem = "Produto2, 1500";
        String mensagemEmail = "Pedido recebido com sucesso";

        var record = new ProducerRecord<String, String>("ECOMMERCE_NOVO_PEDIDO", key, mensagem);
        var recordEmail = new ProducerRecord<String, String>("ECOMMERCE_EMAIL", key, mensagemEmail);

        Callback callback = (data, ex) -> {
            if (ex != null){
                ex.printStackTrace();
                return;
            }
            System.out.println(mensagemSucesso + data.topic() + " / " + data.partition() + " / " + data.timestamp());
        };

        producer.send(record, callback).get();
        producer.send(recordEmail, callback).get();

    }

    private static Properties properties() {
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }
}
