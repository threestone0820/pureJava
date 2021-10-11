package three.stone.kafka.producer.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class StatisticInterceptor implements ProducerInterceptor<String, String> {
    private Logger logger = LogManager.getLogger();
    private static int NUMBER_OF_MESSAGE = 0;
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> producerRecord) {
        NUMBER_OF_MESSAGE += 1;
//        logger.info("Has sent {} messages", NUMBER_OF_MESSAGE);
        return producerRecord;
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
