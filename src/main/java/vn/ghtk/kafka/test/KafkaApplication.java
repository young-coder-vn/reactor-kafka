package vn.ghtk.kafka.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverRecord;
import reactor.kafka.sender.KafkaSender;

@SpringBootApplication
public class KafkaApplication implements CommandLineRunner {

    @Autowired
    private KafkaReceiver<String, String> kafkaReceiver;

    @Autowired
    Push push;

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Flux<ReceiverRecord<String, String>> kafkaFlux = kafkaReceiver.receive();

        kafkaFlux
                .subscribeOn(Schedulers.parallel())
                .subscribe(r -> {
                    if (r.value().equals("Message_1"))
                        System.out.println("Reactive_1: " + System.currentTimeMillis());
                    if (r.value().equals("Message_" + push.recordInteger))
                        System.out.println("Reactive_" + push.recordInteger + ": " + System.currentTimeMillis());
                    r.receiverOffset().acknowledge();
                });

//        kafkaFlux
////                .checkpoint("Messages are started being consumed")
//                .doOnNext(r -> r.receiverOffset().acknowledge())
//                .map(ReceiverRecord::value)
////                .doOnNext(System.out::println)
//                .log()
////                .checkpoint("Messages are done consumed")
//                .subscribeOn(Schedulers.parallel())
//                .subscribe();
    }
}
