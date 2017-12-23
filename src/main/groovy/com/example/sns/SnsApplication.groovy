package com.example.sns

import com.amazonaws.services.sns.AmazonSNS
import groovy.util.logging.Slf4j
import java.util.concurrent.atomic.AtomicInteger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.messaging.handler.annotation.Headers
import org.springframework.scheduling.annotation.Scheduled

@Slf4j
@SpringBootApplication
class SnsApplication {

    @Autowired
    private AmazonSNS sns
    /**
     * Simple counter to show how messages are sequenced in the queue.
     */
    final AtomicInteger counter = new AtomicInteger( 0 )

    @Scheduled( fixedRate = 5000L )
    void producer() {
        def template = new NotificationMessagingTemplate( sns )
        def message = Integer.toHexString( counter.getAndIncrement() )
        log.info( 'Producing message {}', message )
        template.sendNotification( 'example-topic', message, "Subject ${message}" )
    }

    @SqsListener( 'sqs-experiment' )
    static void primaryConsumer( @Headers Map<String, String> headers, String payload ) {
        log.info( 'Consuming {} from sqs-experiment', payload )
        headers.every { key, value ->
            log.info( '    {}: {}', key, value )
        }
    }

    static void main( String[] args ) {
        SpringApplication.run SnsApplication, args
    }
}
