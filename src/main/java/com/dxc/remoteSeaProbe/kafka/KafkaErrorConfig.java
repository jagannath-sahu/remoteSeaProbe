package com.dxc.remoteSeaProbe.kafka;

import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaErrorConfig {

    @Bean
    public DefaultErrorHandler errorHandler(@Qualifier("jsonKafkaTemplate") KafkaTemplate<String, Object> template) {
        DeadLetterPublishingRecoverer recoverer =
                new DeadLetterPublishingRecoverer(template,
                        (record, ex) ->
                                new TopicPartition(
                                        "posdaas.security-match.dlq",
                                        record.partition()
                                )
                );

        //Processing fails then Retry 3 times
        //Still fails then Sent to DLQ
        //Offset Committed only after DLQ
        DefaultErrorHandler handler =
                new DefaultErrorHandler(recoverer, new FixedBackOff(2000L, 3));

        handler.addNotRetryableExceptions(IllegalArgumentException.class);
        return handler;
    }
}

