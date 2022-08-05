package vn.ghtk.kafka.test;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

@RestController
public class Push {

    public Integer recordInteger;

    @Autowired
    private KafkaSender<String, String> kafkaSender;

    @GetMapping("/push/{record}")
    public long run(@PathVariable Integer record) throws Exception {
        this.recordInteger = record;
        long startTime = System.currentTimeMillis();
        Flux outboundFlux = Flux.range(1, record)
                .map(i -> SenderRecord.create(new ProducerRecord<>("hungnvtest", "Message_" + i), i));
        kafkaSender.send(outboundFlux)
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe();
//        Flux flux = Flux.range(1, record);
//        flux.doOnNext(x -> {
//            kafkaSender.send(Mono.just(
//                            SenderRecord.create(
//                                    new ProducerRecord<>("hungnvtest", UUID.randomUUID().toString()), UUID.randomUUID().toString())))
//                    .doOnError(System.out::println)
//                    .subscribeOn(Schedulers.boundedElastic())
//                    .subscribe();
//        }).subscribe();
        return (System.currentTimeMillis() - startTime);
    }
}
