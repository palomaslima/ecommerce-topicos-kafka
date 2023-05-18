package br.com.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class FraudeDetectorConsumer {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        var consumer = new KafkaConsumer<String, String>(properties());
        consumer.subscribe(Collections.singleton("ECOMMERCE_NOVO_PEDIDO"));

        while(true) {
            var records = consumer.poll(Duration.ofMillis(100));

            if (!records.isEmpty()) {
                System.out.println("-------------------------");
                System.out.println("Foi encontrado " + records.count() + " registros.");

                for (var record : records) {
                    System.out.println(record.value());
                }
            }


        }

    }

    private static Properties properties() {
        var properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, FraudeDetectorConsumer.class.getName());
        properties.setProperty(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, "1");
        return properties;
    }
}
