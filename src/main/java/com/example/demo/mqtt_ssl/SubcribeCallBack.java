package com.example.demo.mqtt_ssl;

import com.example.demo.utils.ZipUtil;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

@Slf4j
public class SubcribeCallBack implements MqttCallbackExtended {

	@Override
	public void connectionLost(Throwable throwable) {
		log.warn("client lost connection,reconnecting");
	}

	@Override
	public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {

		try {
			// TODO 在实际生产环境中在此处应该有一个监听事件，一旦接收的订阅的消息，即添加到监听事件
			// subscribe后得到的消息会执行到这里面
			log.info("接收消息主题 : " + s);
			log.info("接收消息Qos : " + mqttMessage.getQos());
			log.info("接收消息内容 : " + new String(mqttMessage.getPayload()));
			String str = ZipUtil.uncompress(mqttMessage.toString());
			log.info("从MQTT收到的消息为:" + str + " ;TOPIC:" + s);
		} catch (Exception e) {
			log.error("SubcribeCallBack error:" + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
		log.info("deliveryComplete---------" + iMqttDeliveryToken.isComplete());
	}

	@Override
	public void connectComplete(boolean b, String s) {
		log.info("receive connectted");

		// 可以重新订阅,用于自动重连后订阅都收不到消息的情况
		String[] topic1 = {"/ssl"};
		try {
			MQTTSubcribeClient.client.subscribe(topic1);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

}

