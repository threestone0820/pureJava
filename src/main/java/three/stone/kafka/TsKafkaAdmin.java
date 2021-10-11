package three.stone.kafka;

import com.google.common.collect.ImmutableMap;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewPartitions;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.common.KafkaFuture;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import three.stone.exception.Exceptions;

import java.io.Closeable;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TsKafkaAdmin implements Closeable {
    private static final Logger logger = LogManager.getLogger();

    private static final String BROKER_SERVER = MQConfig.BROKER;
    private static TsKafkaAdmin instance = null;

    private AdminClient client;

    private TsKafkaAdmin(AdminClient client) {
        this.client = client;
    }

    public static TsKafkaAdmin getAdmin() {
        if (null == instance) {
            Properties config = new Properties();
            config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_SERVER);
            config.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 3000);
            instance = new TsKafkaAdmin(AdminClient.create(config));
        }

        return instance;
    }

    public boolean createTopic(String topicName, int numberOfPartition, short replicationFactor) {
        try {
            return handleKafkaVoidFuture(
                    client.createTopics(Collections.singleton(
                            new NewTopic(topicName, numberOfPartition, replicationFactor)))
                    .all());
        } catch (Exception e) {
            logger.error("Failed to create topic:{} numberOfPartition:{} replicationFactor:{}  e:{}",
                    topicName, numberOfPartition, replicationFactor, Exceptions.toString(e));
            return false;
        }
    }

    public boolean deleteTopic(String topicName) {
        try {
            return handleKafkaVoidFuture(
                    client.deleteTopics(Collections.singleton(topicName)).all()
            );
        } catch (Exception e) {
            logger.error("Failed to delete topic:{} e:{}", topicName, Exceptions.toString(e));
            return false;
        }
    }

    public TopicDescription describeTopic(String topicName) {
        try {
            return client
                    .describeTopics(Collections.singleton(topicName))
                    .all()
                    .get(30, TimeUnit.SECONDS)
                    .get(topicName);
        } catch (Exception e) {
            logger.error("Failed to describe topic:{} e:{}", topicName, Exceptions.toString(e));
            return null;
        }
    }

    public boolean increasePartition(String topicName, int numberOfPartition) {
        Map<String, NewPartitions> newPartitions = ImmutableMap.of(topicName, NewPartitions.increaseTo(numberOfPartition));
        try {
            handleKafkaVoidFuture(client.createPartitions(newPartitions).all());
            return true;
        } catch (Exception e) {
            logger.error("Failed to increase partition topic:{} numberOfPartition:{} e {}",
                    topicName, numberOfPartition, Exceptions.toString(e));
            return false;
        }
    }

    @Override
    public void close() {
        client.close();
    }

    private static boolean handleKafkaVoidFuture(KafkaFuture<Void> future) throws Exception {
        future.get(30, TimeUnit.SECONDS);
        return true;
    }
}
