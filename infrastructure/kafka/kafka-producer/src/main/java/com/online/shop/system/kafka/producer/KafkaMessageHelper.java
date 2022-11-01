package com.online.shop.system.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.shop.system.product.service.domain.exception.ProductDomainException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;


@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaMessageHelper {

    private final ObjectMapper objectMapper;


    public <T> T getEventPayload(String payload, Class<T> outputType) {
        try {
            return objectMapper.readValue(payload, outputType);
        } catch (JsonProcessingException e) {
            log.error("Could not read {} object!", outputType.getName(), e);
            throw new ProductDomainException("Could not read " + outputType.getName() + " object!", e);
        }
    }

    public <T, U> ListenableFutureCallback<SendResult<String, T>>
    getKafkaCallback(String responseTopicName, T avroModel) {
        return new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("Error while sending message {} to topic {}", avroModel.toString(), responseTopicName, throwable);
            }

            @Override
            public void onSuccess(SendResult<String, T> result) {
                RecordMetadata metadata = result.getRecordMetadata();
                log.info("Received new metadata. Topic: {}; Partition {}; Offset {}; Timestamp {}, at time {}",
                        metadata.topic(),
                        metadata.partition(),
                        metadata.offset(),
                        metadata.timestamp(),
                        System.nanoTime());
            }
        };
    }
}
