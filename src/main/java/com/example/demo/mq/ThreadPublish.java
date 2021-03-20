package com.example.demo.mq;

import com.example.demo.utils.ZipUtil;
import lombok.SneakyThrows;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Created by admins on 2021/2/4.
 */
public class ThreadPublish implements Runnable{

	@SneakyThrows
	@Override
	public void run() {
		ServerMQTT server = null;
		double d = Math.random();
		server = new ServerMQTT(Math.random() + "");
		System.out.println("d:" + d);
		String content = "你好，见笑fdsfsdfasfasdfasfasfsaf,我是中国。";
		server.message = new MqttMessage();
		server.message.setQos(2);
		server.message.setRetained(true);
		server.message.setPayload(ZipUtil.compress(content).getBytes());
		while (true) {
			try {
				server.publish(server.topic11, server.message);
				Thread.sleep(1000);
			} catch (MqttException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
