package com.example.demo.mq;

import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * Created by admins on 2021/2/4.
 */
public class TestSubscribe2 {

		public static void main(String[] args) throws MqttException {

			double d = Math.random();
			ClientMQTT client2 = new ClientMQTT(d + "");
			client2.start();

	}
}
