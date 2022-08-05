//package vn.ghtk.kafka.test;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import reactor.kafka.receiver.KafkaReceiver;
//import reactor.kafka.receiver.ReceiverOptions;
//import reactor.kafka.receiver.internals.ConsumerFactory;
//import reactor.kafka.receiver.internals.DefaultKafkaReceiver;
//import reactor.kafka.sender.KafkaSender;
//import reactor.kafka.sender.SenderOptions;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class KafkaNonBlockingConfig {
//
//    private final Logger log = LoggerFactory.getLogger(KafkaNonBlockingConfig.class);
//
//    private String host = "host.docker.internal:9092";
//
//    @Bean
//    public KafkaReceiver<String, String> kafkaReceiver() {
//
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, host);
//        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "Reactor-Client");
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "Reactor-Group");
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
//        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
//        ReceiverOptions<String, String> options = ReceiverOptions.<String, String>create(props).subscription(Collections.singleton("hungnvtest"))
//                .addAssignListener(partitions -> log.debug("onPartitionsAssigned {}", partitions))
//                .addRevokeListener(partitions -> log.debug("onPartitionsRevoked {}", partitions));
//        return KafkaReceiver.create(options);
//    }
//
//    @Bean
//    public KafkaSender<String, String> kafkaSender() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, host);
//        props.put(ProducerConfig.CLIENT_ID_CONFIG, "Reactor-Client");
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//        SenderOptions<String, String> senderOptions = SenderOptions.create(props);
//        return KafkaSender.create(senderOptions);
//    }
//}
