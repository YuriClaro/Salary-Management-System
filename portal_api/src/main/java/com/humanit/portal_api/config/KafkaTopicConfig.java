package com.humanit.portal_api.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@EnableKafka
public class KafkaTopicConfig {
    @Value("${spring.kafka.topic.all_salary}")
    private String allSalaryTopic;

    @Value("${spring.kafka.topic.salary_collaborator_id}")
    private String salaryByCollaboratorIdTopic;

    @Value("${spring.kafka.topic.all_salaries_between_dates}")
    private String allSalariesBetweenDatesTopic;

    @Value("${spring.kafka.topic.all_salaries_status}")
    private String allSalariesByStatusTopic;

    @Value("${spring.kafka.topic.all_own_salaries}")
    private String allOwnSalariesTopic;

    @Bean
    public NewTopic allSalaryTopic() {
        return new NewTopic(allSalaryTopic, 1 ,(short) 1);
    }

    @Bean
    public NewTopic salaryByCollaboratorId() {
        return new NewTopic(salaryByCollaboratorIdTopic, 1 ,(short) 1);
    }

    @Bean
    public NewTopic allSalariesBetweenDates() {
        return new NewTopic(allSalariesBetweenDatesTopic, 1 ,(short) 1);
    }

    @Bean
    public NewTopic allSalariesByStatus() {
        return new NewTopic(allSalariesByStatusTopic, 1 ,(short) 1);
    }

    @Bean
    public NewTopic allOwnSalaries() {
        return new NewTopic(allOwnSalariesTopic, 1 ,(short) 1);
    }
}
