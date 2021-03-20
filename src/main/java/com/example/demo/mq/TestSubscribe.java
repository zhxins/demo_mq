package com.example.demo.mq;

import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * Created by admins on 2021/2/4.
 */
public class TestSubscribe {

		public static void main(String[] args) throws MqttException {
			ClientMQTT client = new ClientMQTT();
			client.start();
	}
}
