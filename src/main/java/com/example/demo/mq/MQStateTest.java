package com.example.demo.mq;

/**
 *  activeMq 状态检测，包含queue和topic
 * Created by admins on 2021/3/18.
 */
import org.apache.activemq.broker.jmx.BrokerViewMBean;
import org.apache.activemq.broker.jmx.QueueViewMBean;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class MQStateTest {
	private BrokerViewMBean mBean = null;
	private MBeanServerConnection connection = null;

	public MQStateTest() throws Exception {
		JMXServiceURL url = new JMXServiceURL(
				"service:jmx:rmi:///jndi/rmi://localhost:1099/jmxrmi");
		JMXConnector connector = JMXConnectorFactory.connect(url, null);
		connector.connect();
		connection = connector.getMBeanServerConnection();

		//必须和刚才配置的名称相同
		ObjectName name = new ObjectName(
				"org.apache.activemq:brokerName=localhost,type=Broker");
		mBean = (BrokerViewMBean) MBeanServerInvocationHandler
				.newProxyInstance(connection, name, BrokerViewMBean.class, true);
	}

	public void read() {
//		ObjectName[] ObjectNames = mBean.getQueues();
		ObjectName[] ObjectNames = mBean.getTopics();

		for (ObjectName queueName : ObjectNames) {
			QueueViewMBean queueMBean = (QueueViewMBean) MBeanServerInvocationHandler
					.newProxyInstance(connection, queueName,
							QueueViewMBean.class, true);
			// 消息队列名称
			System.out.println("queueName = " + queueMBean.getName());
			// 队列中剩余的消息数
			System.out.println("待消费消息数  = " + queueMBean.getQueueSize());
			// 消费者数z
			System.out.println("当前消费者（监听者）= " + queueMBean.getConsumerCount());
			// 出队数
			System.out.println("消息出队数 = " + queueMBean.getDequeueCount());
			// 消息总数
			System.out.println("消息总数 =" + queueMBean.getEnqueueCount());
		}
	}

	public static void main(String[] args) throws Exception {
		MQStateTest t = new MQStateTest();
		t.read();

	}
}
