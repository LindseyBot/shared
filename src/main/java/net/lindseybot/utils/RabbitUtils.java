package net.lindseybot.utils;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.notfab.lindsey.shared.rpc.CustomAmqpProxyFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.remoting.client.AmqpProxyFactoryBean;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

public class RabbitUtils {

    public static Jackson2JsonMessageConverter jacksonConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(),
                ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    public static AmqpProxyFactoryBean createRemoteService(Class<?> serviceInterface, RabbitTemplate template) {
        AmqpProxyFactoryBean amqpProxyFactoryBean = new CustomAmqpProxyFactory();
        amqpProxyFactoryBean.setAmqpTemplate(template);
        amqpProxyFactoryBean.setServiceInterface(serviceInterface);
        amqpProxyFactoryBean.setRoutingKey(serviceInterface.getSimpleName());
        return amqpProxyFactoryBean;
    }

}
