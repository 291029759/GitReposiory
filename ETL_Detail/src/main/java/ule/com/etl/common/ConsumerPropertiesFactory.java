package ule.com.etl.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Leslie Lee on 2017/10/23
 */
public class ConsumerPropertiesFactory {
    public static String groupId;   //指定consumer所属的consumer group
    /**
     * 当发现offset超出合理范围（out of range)时，应该设成的大小（默认是设成offsetRequest中指定的值）：
     * smallest: 自动把该consumer的offset设为最小的offset；
     * largest: 自动把该consumer的offset设为最大的offset；
     *  anything else: throw exception to the consumer；
     */
    public static String autoOffsetReset;
    public static String zookeeperConnect;					//指定zookeeper连接字符串， 格式如hostname:port/chroot。chroot是一个namespace
  //  public static String topicName = "";
    public static String zookeeperSessionTimeoutMs = "";     //Consumer向ZK中更新offset的时间间隔
    public static String zookeeperSyncTimeMs = "";           //	zk follower落后于zk leader的最长时间
    public  static String autoCommitIntervalMs = "";		//往zookeeper上写offset的频率
   // public static String metadataBrokerList = "";           //使用这个参数传入boker和分区的静态信息，如host1:port1,host2:port2, 这个可以是全部boker的一部分
    static {
        Properties properties = new Properties();
        ClassLoader cl = ConsumerPropertiesFactory.class.getClassLoader();
        InputStream is = cl.getResourceAsStream("prd/kafkaConsumer.properties");
      //  InputStream is = cl.getResourceAsStream("beta/kafkaConsumer.properties");
        try {
            properties.load(is);
            zookeeperConnect = properties.getProperty("zookeeper.connect", "");
           // metadataBrokerList=properties.getProperty("metadata.broker.list", "");
            autoOffsetReset = properties.getProperty("auto.offset.reset", "");
            groupId = properties.getProperty("group.id", "");
           // topicName=properties.getProperty("topicName", "");
            zookeeperSessionTimeoutMs=properties.getProperty("zookeeper.session.timeout.ms", "");
            zookeeperSyncTimeMs=properties.getProperty("zookeeper.sync.time.ms", "");
            autoCommitIntervalMs=properties.getProperty("auto.commit.interval.ms", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
