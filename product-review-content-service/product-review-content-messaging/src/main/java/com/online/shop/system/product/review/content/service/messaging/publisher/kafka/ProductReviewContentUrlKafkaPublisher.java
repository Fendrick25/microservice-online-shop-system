package com.online.shop.system.product.review.content.service.messaging.publisher.kafka;

import com.online.shop.system.kafka.avro.model.ProductReviewContentUrlAvroModel;
import com.online.shop.system.kafka.producer.KafkaMessageHelper;
import com.online.shop.system.kafka.producer.service.KafkaProducer;
import com.online.shop.system.product.review.content.service.domain.config.ProductReviewContentServiceConfigData;
import com.online.shop.system.product.review.content.service.domain.dto.ProductReviewContentUrl;
import com.online.shop.system.product.review.content.service.domain.ports.output.message.publisher.ProductReviewContentUrlMessagePublisher;
import com.online.shop.system.product.review.content.service.messaging.mapper.ProductReviewContentMessagingDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductReviewContentUrlKafkaPublisher implements ProductReviewContentUrlMessagePublisher {

    private final KafkaProducer<String, ProductReviewContentUrlAvroModel> kafkaProducer;
    private final ProductReviewContentServiceConfigData productReviewContentServiceConfigData;
    private final KafkaMessageHelper kafkaMessageHelper;
    private final ProductReviewContentMessagingDataMapper productReviewContentMessagingDataMapper;
    @Override
    public void publish(ProductReviewContentUrl productReviewContentUrl) {
        String id = UUID.randomUUID().toString();
        ProductReviewContentUrlAvroModel productReviewContentUrlAvroModel = productReviewContentMessagingDataMapper
                .productReviewContentUrlToProductReviewContentUrlAvroModel(productReviewContentUrl);

        try {
            log.info(productReviewContentServiceConfigData.getProductReviewContentTopicName());
            kafkaProducer.send(productReviewContentServiceConfigData.getProductReviewContentTopicName(),
                    UUID.randomUUID().toString(),
                    productReviewContentUrlAvroModel,
                    kafkaMessageHelper.getKafkaCallback(productReviewContentServiceConfigData.getProductReviewContentTopicName(),
                            productReviewContentUrlAvroModel));

            log.info("ProductReviewContentUrlAvroModel sent to Kafka for product id: {} and id: {}",
                    productReviewContentUrlAvroModel.getProductID(), id);
        } catch (Exception e) {
            log.error("Error while sending ProductReviewContentUrlAvroModel" +
                            " to kafka with product id: {} and id: {}, error: {}",
                    productReviewContentUrlAvroModel.getProductID(), id, e.getMessage());
        }

    }
}
