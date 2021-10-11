package three.stone.kafka;

import com.google.common.collect.ImmutableMap;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.junit.Test;
import three.stone.kafka.consumer.MQConsumerHelper;
import three.stone.utils.TimeConstants;

import java.time.Duration;
import java.util.Collections;

import static three.stone.kafka.MQConfig.TOPIC_DEMO;

public class KafkaConsumerTest {
    private static final long NOW = System.currentTimeMillis();
    private static final TopicPartition PARTITION_ZERO = new TopicPartition(TOPIC_DEMO, 0);
    private static final TopicPartition PARTITION_ONE = new TopicPartition(TOPIC_DEMO, 1);
    private static int NUMBER_OF_POLL = 5;


    @Test
    public void testSeekByTimestamp() throws Exception {
        KafkaConsumer<String, String> consumer = MQConsumerHelper.getInstance().getConsumerClient();
        consumer.subscribe(Collections.singleton(TOPIC_DEMO));
        consumer.offsetsForTimes(ImmutableMap.of(
                PARTITION_ZERO, NOW - TimeConstants.DAYS,
                PARTITION_ONE, NOW - 2 * TimeConstants.DAYS));

        try {

            while (NUMBER_OF_POLL-- > 0) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
                for (ConsumerRecord<String, String> record : records.records(PARTITION_ZERO)) {
                    assert (record.timestamp() > NOW - TimeConstants.DAYS);
                }

                for (ConsumerRecord<String, String> record : records.records(PARTITION_ONE)) {
                    assert (record.timestamp() > NOW - 2 * TimeConstants.DAYS);
                }
            }
        } finally {
            consumer.close();
        }
    }
}
