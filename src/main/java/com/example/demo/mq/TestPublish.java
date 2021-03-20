package com.example.demo.mq;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Created by admins on 2021/2/4.
 */
public class TestPublish {

	/**
	 *  启动入口
	 * @param args
	 * @throws MqttException
	 */
	public static void main(String[] args) throws MqttException {
		ServerMQTT server = new ServerMQTT("server1");

		server.message = new MqttMessage();
		server.message.setQos(2);
		server.message.setRetained(true);
		server.message.setPayload("hello,topico你看到我的小熊了吗".getBytes());
//		server.message.setPayload(ZipUtil.compress("hello,topic222").getBytes());
		server.publish(server.topic11 , server.message);
		System.out.println(server.message.isRetained() + "------ratained状态");

		// 同时订阅自己发布的topic
//		server.client.subscribe(TOPIC);

	}

}
