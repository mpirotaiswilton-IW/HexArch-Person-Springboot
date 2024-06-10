package com.max_pw_iw.person_subscriber.adapter.in.event_subscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import com.solacesystems.jms.SolConnectionFactory;
import com.solacesystems.jms.SolJmsUtility;

@Configuration
@PropertySource({"classpath:application.properties"})
@EnableJms
public class ListenerConfig {

    private static final Logger logger = LoggerFactory.getLogger(ListenerConfig.class);

    @Autowired
    private Environment environment;

    @Bean
    public SolConnectionFactory solConnectionFactory() throws Exception {
        SolConnectionFactory connectionFactory = SolJmsUtility.createConnectionFactory();
        connectionFactory.setHost(environment.getProperty("solace.jms.host"));
        connectionFactory.setVPN(environment.getProperty("solace.jms.msgVpn"));
        connectionFactory.setUsername(environment.getProperty("solace.jms.clientUsername"));
        connectionFactory.setPassword(environment.getProperty("solace.jms.clientPassword"));
        return connectionFactory;
    }


    @Bean(name = "jmsListenerContainerFactory")
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory 
          = new DefaultJmsListenerContainerFactory();
        try{
            factory.setConnectionFactory(solConnectionFactory());
            } catch (Exception e) {
                logger.info("JMS connection failed with Solace." + e.getMessage());
                e.printStackTrace();
            }
        return factory;
    }
}
