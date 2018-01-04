package ule.com.etl.common;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

/**
 * Created by Leslie Lee on 2017/12/28
 * 自定义的数据分区器，功能是：决定输入的key/value键值对的message发送到Topic的那个分区中，
 * 返回分区id，范围:[0,分区数量); 这里的实现比较简单，根据key中的数字决定分区的值
 */
public class JavaKafkaProducerPartitioner implements Partitioner{

    /**
     * 无参构造函数
     */
    public JavaKafkaProducerPartitioner() {
        this(new VerifiableProperties());
    }

    /**
     * 构造函数，必须给定
     * @param properties 上下文
     */
    public JavaKafkaProducerPartitioner(VerifiableProperties properties) {
        // nothings
    }
    public int partition(Object key, int numPartitions) {
        int num = Integer.valueOf(((String) key).replaceAll("key_", "").trim());
        return num % numPartitions;
    }

}
