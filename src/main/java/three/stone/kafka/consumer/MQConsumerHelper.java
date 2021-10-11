package three.stone.kafka.consumer;

import com.google.common.collect.ImmutableMap;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.consumer.OffsetAndTimestamp;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import three.stone.kafka.MQConfig;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import static three.stone.kafka.MQConfig.TOPIC_DEMO;

public class MQConsumerHelper {
    private static final Logger logger = LogManager.getLogger();

    private static MQConsumerHelper instance;
    private static final String DEMO_CLIENT_ID = "consumer_demo_client_1";
    private static final String GROUP_ID = "consumer_group_id_2";

    private Properties initConfig() {
        Properties config = new Properties();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, MQConfig.BROKER);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        config.put(ConsumerConfig.CLIENT_ID_CONFIG, DEMO_CLIENT_ID);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        config.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, TtlInterceptor.class.getName());
        return config;
    }

    public KafkaConsumer<String, String> getConsumerClient() {
        Properties config = initConfig();
        return new KafkaConsumer<>(config);
    }

    public static MQConsumerHelper getInstance() {
        if (null == instance) {
            instance = new MQConsumerHelper();
        }
        return instance;
    }

    private MQConsumerHelper() {
    }

    public static void main(String[] args) {
        KafkaConsumer<String, String> consumer = MQConsumerHelper.getInstance().getConsumerClient();
        consumer.subscribe(Collections.singleton(TOPIC_DEMO));
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
                for (ConsumerRecord<String, String> record : records) {
                    logger.info("message {} topic {} partition {}", record.value(), record.topic(), record.partition());
                }
            }
        } finally {
            consumer.close();
        }
    }
}
