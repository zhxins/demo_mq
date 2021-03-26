package com.example.demo.sql;

import com.alibaba.fastjson.JSON;
import com.example.demo.model.ApmIpTrafficEntity;
import org.pcap4j.core.*;
import org.pcap4j.packet.EthernetPacket;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;
import org.pcap4j.packet.namednumber.EtherType;
import org.pcap4j.packet.namednumber.IpNumber;

import java.io.EOFException;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeoutException;


@SuppressWarnings("javadoc")
public class ReadPacketFile {

	private static final int COUNT = 5000;

	private static final String PCAP_FILE = "E:\\workspace\\work_idea\\git\\apm\\data\\pcapfiles\\6_eth2_20210325100025.pcap";

	static ConcurrentHashMap<String, ApmIpTrafficEntity> ut_chmIpTraffic = new ConcurrentHashMap<String, ApmIpTrafficEntity>();

	public static void main(String[] args) {

		String path = "E:\\workspace\\work_idea\\git\\apm\\data\\pcapfiles\\";
		File file = new File(path);
		if (file.isDirectory()) {

			String[] filelist = file.list();
			for(String fileName:filelist) {
				System.out.println(path + fileName);

				try {
					readFile(path + fileName);
				} catch (PcapNativeException e) {
					e.printStackTrace();
				} catch (NotOpenException e) {
					e.printStackTrace();
				} catch (EOFException e) {
					e.printStackTrace();
				} catch (TimeoutException e) {
					e.printStackTrace();
				}

			}
		}
	}

	public static void readFile(String pcap_file) throws PcapNativeException, NotOpenException, EOFException, TimeoutException {

		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

		PcapHandle handle;
		try {
			handle = Pcaps.openOffline(pcap_file);
		} catch (PcapNativeException e) {
			handle = Pcaps.openOffline(pcap_file);
		}

		for (int i = 1; i < COUNT; i++) {
			try {
				Packet packet = handle.getNextPacketEx();
				if (packet == null) {
					System.out.println("packet is null");
					break;
				}

				// 可以直接get你想要的报文类型，只要Pcap4J库原生支持
				EthernetPacket ethernetPacket = packet.get(EthernetPacket.class); // 以太网报文
				EtherType eth_type = ethernetPacket.getHeader().getType();
				//System.out.println(i);
				if (eth_type == EtherType.IPV4) {

					IpV4Packet ipv4_packet = packet.get(IpV4Packet.class);
					IpV4Packet.IpV4Header ipV4Packet_header = ipv4_packet.getHeader();

					if (ipV4Packet_header.getProtocol() == IpNumber.TCP) {
						TcpPacket tcp_packet = packet.get(TcpPacket.class);
						TcpPacket.TcpHeader tcp_header = tcp_packet.getHeader();
						if (!tcp_header.getPsh()) {
							continue;
						}
						String sAddr = ipV4Packet_header.getSrcAddr().toString().replace("/", "");
						String dAddr = ipV4Packet_header.getDstAddr().toString().replace("/", "");
						int dPort = tcp_header.getDstPort().valueAsInt();
						int sPort = tcp_header.getSrcPort().valueAsInt();

						IpNumber protocol = ipV4Packet_header.getProtocol();
						System.out.println(i);
						// packet 时间戳
						System.out.println(handle.getTimestamp() + "===:" + packet.length());
						System.out.println("seqno=" + i + ",（src ip,dst ip, src port,dst port, protocol):(" + sAddr + "," + dAddr + "," + sPort + "," + dPort + "," + protocol + ")");

						String tcpdata = new String(ipv4_packet.getPayload().getRawData());
//						System.out.println("tcp data is:" + tcpdata);

						String key = String.format("%s;%s;%d;%d;%d", sAddr, dAddr, protocol.value().intValue(), sPort, dPort);

						ApmIpTrafficEntity apmIpTraffic = ut_chmIpTraffic.get(key);
						ApmIpTrafficEntity ut_apmIpTraffic = null;
						if (apmIpTraffic == null) {

							ut_apmIpTraffic = new ApmIpTrafficEntity();
							ut_apmIpTraffic.setsIpAddr(sAddr);
							ut_apmIpTraffic.setdIpAddr(dAddr);
							ut_apmIpTraffic.setsPort(sPort);
							ut_apmIpTraffic.setdPort(dPort);
							ut_apmIpTraffic.setDt(sdf.format(handle.getTimestamp()));

							ut_apmIpTraffic.setsProt(protocol.name());
							ut_apmIpTraffic.setBy(packet.length());
							ut_apmIpTraffic.setFr(1);
							ut_chmIpTraffic.put(key, ut_apmIpTraffic);

						} else {
							apmIpTraffic.setDt(sdf.format(handle.getTimestamp()));
							apmIpTraffic.setBy(apmIpTraffic.getBy() + packet.length());
							apmIpTraffic.setFr(apmIpTraffic.getFr() + 1);

							ut_apmIpTraffic = ut_chmIpTraffic.get(key);
							ut_apmIpTraffic.setDt(sdf.format(handle.getTimestamp()));
							ut_apmIpTraffic.setFr(apmIpTraffic.getFr());
//							apmIpTrafficEntity_ut(ut_apmIpTraffic, apmIpTraffic.getBy() - ut_apmIpTraffic.getBy());
							ut_apmIpTraffic.setBy(apmIpTraffic.getBy());
						}

					}
				}
			} catch (TimeoutException e) {
			} catch (EOFException e) {
				System.out.println("EOF");
				break;
			}
		}
		handle.close();

		List<ApmIpTrafficEntity> list = new ArrayList<ApmIpTrafficEntity>(ut_chmIpTraffic.values());
		Collections.sort(list);
		System.out.println(list.size());
		System.out.println(JSON.toJSON(list.subList(0, 5)));

		File file = new File(pcap_file);
		file.delete();
	}
}