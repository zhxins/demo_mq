package com.example.demo.mqtt_ssl;

import com.alibaba.fastjson.JSON;
import com.example.demo.utils.ZipUtil;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.HashMap;
import java.util.Map;

public class TestSSLPublish {

	public static void main(String[] args) throws Exception {
		MQTTPublishClient mqttClientSend = new MQTTPublishClient("ssl://192.168.52.72:8883", String.valueOf(Math.random()));
		MqttMessage message = new MqttMessage();
		Map msg = new HashMap<String,String>();
		msg.put("tag", "hello  ssl 不留言标准");
		String comp = ZipUtil.compress(JSON.toJSONString(msg));
		message.setPayload(comp.getBytes("UTF-8"));
		message.setQos(2);
		message.setRetained(false);
		System.out.println("MQTT send");
		mqttClientSend.publish("/ssl",message);
	}
}

