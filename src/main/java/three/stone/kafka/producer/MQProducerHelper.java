package three.stone.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import three.stone.exception.Exceptions;
import three.stone.kafka.MQConfig;
import three.stone.kafka.producer.interceptor.PrefixInterceptor;
import three.stone.kafka.producer.interceptor.StatisticInterceptor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

public class MQProducerHelper {
    private static final Logger logger = LogManager.getLogger();
    private static final String DEMO_CLIENT_ID = "producer_demo_client_1";


    private static MQProducerHelper instance;
    private Producer<String, String> producer;

    private Properties initConfig() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, MQConfig.BROKER);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 2000);
        properties.put(ProducerConfig.SOCKET_CONNECTION_SETUP_TIMEOUT_MS_CONFIG, 2000);
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, DEMO_CLIENT_ID);
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, getInterceptors(Arrays.asList(PrefixInterceptor.class, StatisticInterceptor.class)));
        return properties;
    }

    private static String getInterceptors(List<Class<? extends ProducerInterceptor<?, ?>>> interceptors) {
        if (interceptors.isEmpty()) {
            throw new IllegalArgumentException("interceptors is empty");
        }
        StringBuilder builder = new StringBuilder(interceptors.get(0).getName());
        for (int i = 1; i < interceptors.size(); i++) {
            builder.append(",");
            builder.append(interceptors.get(i).getName());
        }
        return builder.toString();
    }

    private void initProducer() {
        Properties properties = initConfig();
        this.producer = new KafkaProducer<>(properties);
    }


    public void produceMessage(String topic, String message) {
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, message);
        this.producer.send(producerRecord, (recordMetadata, e) -> {
            if (e == null) {
                logger.info("produce message succeed, metadata:{} message:{}",
                        recordMetadata.toString(), message);
            } else {
                logger.warn("produce message failed, e:{}", Exceptions.toString(e));
            }
        });
    }

    public static MQProducerHelper getInstance() {
        if (instance == null) {
            instance = new MQProducerHelper();
            instance.initProducer();
        }
        return instance;
    }

    private MQProducerHelper() { }

    public static void main(String[] args) throws IOException, InterruptedException {
        while (true) {
            MQProducerHelper.getInstance().produceMessage(MQConfig.TOPIC_DEMO, "Hello kafka_" + ThreadLocalRandom.current().nextInt(1000));
            Thread.sleep(1000);
        }
    }
}
