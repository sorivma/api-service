package com.sorivma.apiservice.core.config

import com.sorivma.apiservice.core.constants.RMQConstants
import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitTransactionMessageConfig {
    @Bean
    fun messageConverter(): MessageConverter = Jackson2JsonMessageConverter()

    @Bean
    fun queue(): Queue = Queue(RMQConstants.MAIN_QUEUE_NAME)

    @Bean
    fun exchange(): Exchange = TopicExchange(RMQConstants.Transactions.TRANSACTION_EXCHANGE_NAME)

    @Bean
    fun transactionBinding(): Binding =
        BindingBuilder.bind(queue()).to(exchange()).with(RMQConstants.Transactions.ROUTING_KEY).noargs()

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = messageConverter()
        return rabbitTemplate
    }
}