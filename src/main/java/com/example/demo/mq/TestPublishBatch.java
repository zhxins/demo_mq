package com.example.demo.mq;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Created by admins on 2021/2/4.
 */
public class TestPublishBatch {

	/**
	 *  启动入口
	 * @param args
	 * @throws MqttException
	 */
	public static void main(String[] args) throws MqttException {

		for (int i = 0; i < 3000; i++) {
			Thread thread = new Thread(new ThreadPublish());
			thread.start();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}
}
