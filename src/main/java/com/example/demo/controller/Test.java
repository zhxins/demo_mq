package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.mqtt_ssl.MQTTPublishClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admins on 2021/2/2.
 */
@RestController
public class Test {

	@RequestMapping("test")
	public String test() {
		return "https test success";
	}

	@RequestMapping("publish")
	public String publish() {
		try {
			MQTTPublishClient mqttClientSend = new MQTTPublishClient("ssl://localhost:8883", String.valueOf(Math.random()));
			MqttMessage message = new MqttMessage();
			Map msg = new HashMap<String,String>();
			msg.put("tag", "hello  ssl");
			message.setPayload(JSON.toJSONString(msg).getBytes("UTF-8"));
			message.setQos(0);
			message.setRetained(false);
			System.out.println("MQTT senda111111111");
			mqttClientSend.publish("/ssl",message);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MqttException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "succ";
	}


}
