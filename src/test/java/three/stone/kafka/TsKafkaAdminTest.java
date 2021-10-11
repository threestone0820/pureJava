package three.stone.kafka;

import org.apache.kafka.clients.admin.TopicDescription;
import org.junit.Test;

public class TsKafkaAdminTest {
    private static final String TEST_ADMIN_TOPIC = "test-admin-topic";

    @Test
    public void testCreateTopic() {
        TsKafkaAdmin admin = TsKafkaAdmin.getAdmin();
        boolean createResult = admin.createTopic(TEST_ADMIN_TOPIC, 2, (short) 2);
        assert createResult;
        TopicDescription topicDescription = admin.describeTopic(TEST_ADMIN_TOPIC);
        assert topicDescription.partitions().size() == 2;
        boolean deleteResult = admin.deleteTopic(TEST_ADMIN_TOPIC);
        assert deleteResult;
        TopicDescription topicDescriptionAfter = admin.describeTopic(TEST_ADMIN_TOPIC);
        assert topicDescriptionAfter == null;
    }

    @Test
    public void testIncreasePartition() {
        TsKafkaAdmin admin = TsKafkaAdmin.getAdmin();
        boolean result1 = admin.increasePartition(MQConfig.TOPIC_DEMO, 3);
        assert result1;
        boolean result2 = admin.increasePartition(MQConfig.TOPIC_DEMO, 2);
        assert !result2;
    }
}
