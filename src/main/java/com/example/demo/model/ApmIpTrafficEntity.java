package com.example.demo.model;

public class ApmIpTrafficEntity implements Comparable<ApmIpTrafficEntity> {

	//
	private String sIpAddr;
	private String dIpAddr;
	private int prot;
	private String sProt;

	private int sPort;
	private int dPort;
	private int fr;
	private int by;

	private String dt;
    private String ut;

	public String getUt() {
		return ut;
	}

	public void setUt(String ut) {
		this.ut = ut;
	}

	public String getsIpAddr() {
		return sIpAddr;
	}

	public void setsIpAddr(String sIpAddr) {
		this.sIpAddr = sIpAddr;
	}

	public String getdIpAddr() {
		return dIpAddr;
	}

	public void setdIpAddr(String dIpAddr) {
		this.dIpAddr = dIpAddr;
	}



	public int getProt() {
		return prot;
	}

	public void setProt(int prot) {
		this.prot = prot;
	}

	public String getsProt() {
		return sProt;
	}

	public void setsProt(String sProt) {
		this.sProt = sProt;
	}

	public int getsPort() {
		return sPort;
	}

	public void setsPort(int sPort) {
		this.sPort = sPort;
	}

	public int getdPort() {
		return dPort;
	}

	public void setdPort(int dPort) {
		this.dPort = dPort;
	}

	public int getFr() {
		return fr;
	}

	public void setFr(int fr) {
		this.fr = fr;
	}

	public int getBy() {
		return by;
	}

	public void setBy(int by) {
		this.by = by;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

	@Override
	public int compareTo(ApmIpTrafficEntity o) {
		if (this.by > o.getBy()) {
			return -1;
		} else if (this.by < o.getBy()) {
			return 1;
		} else {
			return 0;
		}
	}

}
