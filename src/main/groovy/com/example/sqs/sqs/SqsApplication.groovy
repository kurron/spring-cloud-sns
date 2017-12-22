package com.example.sqs.sqs

import com.amazonaws.services.sqs.AmazonSQSAsync
import groovy.util.logging.Slf4j
import java.util.concurrent.atomic.AtomicInteger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.messaging.handler.annotation.Headers
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.support.MessageBuilder
import org.springframework.scheduling.annotation.Scheduled

@Slf4j
@SpringBootApplication
class SqsApplication {

    /**
     * Manages interactions with the queue.
     */
    @Autowired
    private AmazonSQSAsync sqs

    /**
     * Simple counter to show how messages are sequenced in the queue.
     */
    final AtomicInteger counter = new AtomicInteger( 0 )

    @Scheduled( fixedRate = 1000L )
    void producer() {
        def template = new QueueMessagingTemplate( sqs )
        def payload = Integer.toHexString( counter.getAndIncrement() )
        def message = MessageBuilder.withPayload( payload )
                                    .build()
        log.info( 'Producing message {}', payload )
        template.send( 'sqs-experiment', message )
    }

    @SqsListener( 'sqs-experiment' )
    @SendTo( 'secondary-queue' )
    static String primaryConsumer( @Headers Map<String, String> headers, String payload ) {
        log.info( 'Consuming {} from sqs-experiment', payload )
        headers.every { key, value ->
            log.info( '    {}: {}', key, value )
        }
        payload.toUpperCase()
    }

    @SqsListener( 'secondary-queue' )
    static void secondaryConsumer( @Headers Map<String, String> headers, String payload ) {
        log.info( 'Consuming {} from secondary-queue', payload )
        headers.every { key, value ->
            log.info( '    {}: {}', key, value )
        }
    }

    static void main( String[] args ) {
        SpringApplication.run SqsApplication, args
    }
}
