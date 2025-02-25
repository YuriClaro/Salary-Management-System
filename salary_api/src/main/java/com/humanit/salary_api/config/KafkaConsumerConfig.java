package com.humanit.salary_api.config;

import com.humanit.salary_api.dto.DatesRequestDTO;
import com.humanit.salary_api.dto.EmailDTO;
import com.humanit.salary_api.dto.EmailWithCollaboratorIdDTO;
import com.humanit.salary_api.dto.EmailWithStatusDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.group.all_salary}")
    private String allSalaryGroup;

    @Value("${spring.kafka.group.salary_collaborator_id}")
    private String salaryByCollaboratorId;

    @Value("${spring.kafka.group.all_salaries_between_dates}")
    private String allSalariesBetweenDates;

    @Value("${spring.kafka.group.all_salaries_status}")
    private String allSalariesByStatus;

    @Value("${spring.kafka.group.all_own_salaries}")
    private String allOwnSalaries;

    @Bean
    public ConsumerFactory<String, EmailDTO> consumerEmailFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, allSalaryGroup);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        JsonDeserializer<EmailDTO> jsonDeserializer = new JsonDeserializer<>(EmailDTO.class);
        jsonDeserializer.setRemoveTypeHeaders(false);
        jsonDeserializer.setUseTypeMapperForKey(true);
        jsonDeserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EmailDTO> kafkaListenerEmailContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, EmailDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerEmailFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, EmailWithCollaboratorIdDTO> consumerEmailWithCollaboratorIdFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, salaryByCollaboratorId);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        JsonDeserializer<EmailWithCollaboratorIdDTO> jsonDeserializer = new JsonDeserializer<>(EmailWithCollaboratorIdDTO.class);
        jsonDeserializer.setRemoveTypeHeaders(false);
        jsonDeserializer.setUseTypeMapperForKey(true);
        jsonDeserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EmailWithCollaboratorIdDTO> kafkaListenerEmailWithCollaboratorIdContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, EmailWithCollaboratorIdDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerEmailWithCollaboratorIdFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, DatesRequestDTO> consumerAllSalariesBetweenDatesFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, allSalariesBetweenDates);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        JsonDeserializer<DatesRequestDTO> jsonDeserializer = new JsonDeserializer<>(DatesRequestDTO.class);
        jsonDeserializer.setRemoveTypeHeaders(false);
        jsonDeserializer.setUseTypeMapperForKey(true);
        jsonDeserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, DatesRequestDTO> kafkaListenerAllSalariesBetweenDatesContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, DatesRequestDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerAllSalariesBetweenDatesFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, EmailWithStatusDTO> consumerAllSalariesByStatusFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, allSalariesByStatus);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        JsonDeserializer<EmailWithStatusDTO> jsonDeserializer = new JsonDeserializer<>(EmailWithStatusDTO.class);
        jsonDeserializer.setRemoveTypeHeaders(false);
        jsonDeserializer.setUseTypeMapperForKey(true);
        jsonDeserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EmailWithStatusDTO> kafkaListenerAllSalariesByStatusContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, EmailWithStatusDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerAllSalariesByStatusFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, EmailDTO> consumerAllOwnSalariesFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, allOwnSalaries);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        JsonDeserializer<EmailDTO> jsonDeserializer = new JsonDeserializer<>(EmailDTO.class);
        jsonDeserializer.setRemoveTypeHeaders(false);
        jsonDeserializer.setUseTypeMapperForKey(true);
        jsonDeserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EmailDTO> kafkaListenerAllOwnSalariesFactory() {
        ConcurrentKafkaListenerContainerFactory<String, EmailDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerEmailFactory());
        return factory;
    }
}
