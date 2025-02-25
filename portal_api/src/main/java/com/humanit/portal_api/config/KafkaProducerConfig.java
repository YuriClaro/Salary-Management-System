package com.humanit.portal_api.config;

import com.humanit.portal_api.dto.export.DatesRequestDTO;
import com.humanit.portal_api.dto.export.EmailDTO;
import com.humanit.portal_api.dto.export.EmailWithCollaboratorIdDTO;
import com.humanit.portal_api.dto.export.EmailWithStatusDTO;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ProducerFactory<String, EmailDTO> producerEmailFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, EmailDTO> kafkaEmailTemplate() {
        return new KafkaTemplate<>(producerEmailFactory());
    }

    @Bean
    public ProducerFactory<String, EmailWithCollaboratorIdDTO> producerEmailWithCollaboratorIdFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }
    @Bean
    public KafkaTemplate<String, EmailWithCollaboratorIdDTO> kafkaEmailWithCollaboratorIdTemplate() {
        return new KafkaTemplate<>(producerEmailWithCollaboratorIdFactory());
    }

    @Bean
    public ProducerFactory<String, DatesRequestDTO> producerAllSalariesBetweenDatesFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }
    @Bean
    public KafkaTemplate<String, DatesRequestDTO> kafkaAllSalariesBetweenDatesTemplate() {
        return new KafkaTemplate<>(producerAllSalariesBetweenDatesFactory());
    }

    @Bean
    public ProducerFactory<String, EmailWithStatusDTO> producerAllSalariesByStatusFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }
    @Bean
    public KafkaTemplate<String, EmailWithStatusDTO> kafkaAllSalariesByStatusTemplate() {
        return new KafkaTemplate<>(producerAllSalariesByStatusFactory());
    }

}
