package com.example.demo.mqtt_ssl;

public class TestSSLSubcribe {

	public static void main(String[] args) {
		MQTTSubcribeClient mqttReceiver = new MQTTSubcribeClient("ssl://192.168.52.72:8883", String.valueOf(Math.random()));
	}
}

