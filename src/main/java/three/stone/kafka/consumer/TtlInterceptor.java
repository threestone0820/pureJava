package three.stone.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import three.stone.utils.TimeConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TtlInterceptor implements ConsumerInterceptor<String, String > {
    private Logger logger = LogManager.getLogger();

    @Override
    public ConsumerRecords<String, String> onConsume(ConsumerRecords<String, String> consumerRecords) {
        Map<TopicPartition, List<ConsumerRecord<String, String>>> map = new HashMap<>();
        Set<TopicPartition> partitions = consumerRecords.partitions();
        for (TopicPartition partition : partitions) {
            List<ConsumerRecord<String, String>> records = consumerRecords.records(partition);
            List<ConsumerRecord<String, String>> filteredRecords = new ArrayList<>();
            for (ConsumerRecord<String, String> record : records) {
                if (record.timestamp() < System.currentTimeMillis() - 5 * TimeConstants.SECONDS) {
                    logger.warn("Too old message {}", record.value());
                } else {
                    filteredRecords.add(record);
                }
            }
            if (!filteredRecords.isEmpty()) {
                map.put(partition, filteredRecords);
            }
        }

        return new ConsumerRecords<>(map);
    }

    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> map) {
        map.forEach((k, v) -> {
            logger.info("Commit topic {} partition {} offset {}", k.topic(), k.partition(), v.offset());
        });
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
