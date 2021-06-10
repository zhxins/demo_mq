package com.example.demo.model;

public class SnmpEntity {

	boolean sif;

	boolean sif_proto;

	boolean sif_error;

	boolean eigrp_error;

	float rx_pwr;
	boolean rx_pwr_alarm;    // 能否获取，true:能

	public boolean isSif() {
		return sif;
	}

	public void setSif(boolean sif) {
		this.sif = sif;
	}

	public boolean isSif_proto() {
		return sif_proto;
	}

	public void setSif_proto(boolean sif_proto) {
		this.sif_proto = sif_proto;
	}

	public boolean isSif_error() {
		return sif_error;
	}

	public void setSif_error(boolean sif_error) {
		this.sif_error = sif_error;
	}

	public boolean isEigrp_error() {
		return eigrp_error;
	}

	public void setEigrp_error(boolean eigrp_error) {
		this.eigrp_error = eigrp_error;
	}

	public float getRx_pwr() {
		return rx_pwr;
	}

	public void setRx_pwr(float rx_pwr) {
		this.rx_pwr = rx_pwr;
	}

	public boolean isRx_pwr_alarm() {
		return rx_pwr_alarm;
	}

	public void setRx_pwr_alarm(boolean rx_pwr_alarm) {
		this.rx_pwr_alarm = rx_pwr_alarm;
	}
}
