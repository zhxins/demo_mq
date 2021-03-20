package com.example.demo.mq;

import com.example.demo.utils.ZipUtil;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class PushCallback implements MqttCallback{


	public PushCallback() {}
	ServerMQTT serverMQTT;
	public PushCallback(ServerMQTT serverMQTT) {
		this.serverMQTT = serverMQTT;
	}

	public void connectionLost(Throwable cause) {
		// 连接丢失后，一般在这里面进行重连
		System.out.println("连接断开，可以做重连");
		while (true){
			try {
				//如果没有发生异常说明连接成功，如果发生异常，则死循环
				Thread.sleep(1000);
				serverMQTT.reConnect();
				// 在订阅者的回调函数再次重新订阅
//				serverMQTT.client.subscribe();

				System.out.println("重连成功");
				break;
			}catch (Exception e){
				continue;
			}
		}
	}

	public void deliveryComplete(IMqttDeliveryToken token) {
		System.out.println("deliveryComplete---------" + token.isComplete());
	}

	public void messageArrived(String topic, MqttMessage message) throws Exception {
		// subscribe后得到的消息会执行到这里面
		System.out.println("接收消息主题 : " + topic);
		System.out.println("接收消息主题id : " + message.getId());
		System.out.println("接收消息Qos : " + message.getQos());
		System.out.println("接收消息内容 : " + ZipUtil.uncompress(new String(message.getPayload())));
	}
}