package com.humanit.salary_api.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@EnableKafka
public class KafkaTopicConfig {
    @Value("${spring.kafka.topic.email_all_salaries}")
    private String emailAllSalariesTopic;

    @Value("${spring.kafka.topic.email_all_salaries_status}")
    private String emailAllSalariesByStatusTopic;

    @Value("${spring.kafka.topic.email_all_salaries_dates}")
    private String emailAllSalariesBetweenDatesTopic;

    @Value("${spring.kafka.topic.email_salary_collaborator_id}")
    private String emailAllSalariesByCollaboratorIdTopic;

    @Value("${spring.kafka.topic.email_all_own_salaries}")
    private String emailAllOwnSalariesTopic;

    @Bean
    public NewTopic emailAllSalariesTopic() {
        return new NewTopic(emailAllSalariesTopic, 1, (short) 1);
    }

    @Bean
    public NewTopic emailAllSalariesByStatusTopic() {
        return new NewTopic(emailAllSalariesByStatusTopic, 1, (short) 1);
    }

    @Bean
    public NewTopic emailAllSalariesBetweenDatesTopic() {
        return new NewTopic(emailAllSalariesBetweenDatesTopic, 1, (short) 1);
    }

    @Bean
    public NewTopic emailAllSalariesByCollaboratorIdTopic() {
        return new NewTopic(emailAllSalariesByCollaboratorIdTopic, 1, (short) 1);
    }

    @Bean
    public NewTopic emailAllOwnSalariesTopic() {
        return new NewTopic(emailAllOwnSalariesTopic, 1, (short) 1);
    }
}
