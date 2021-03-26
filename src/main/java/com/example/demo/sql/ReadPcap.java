package com.example.demo.sql;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.EthernetPacket;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;
import org.pcap4j.packet.namednumber.EtherType;
import org.pcap4j.packet.namednumber.IpNumber;
import org.pcap4j.packet.namednumber.TcpPort;

import java.io.EOFException;
import java.net.Inet4Address;
import java.util.concurrent.TimeoutException;


@SuppressWarnings("javadoc")
public class ReadPcap {

	private static final int COUNT = 5000;

	private static final String PCAP_FILE_KEY = ReadPcap.class.getName() + ".pcapFile";
	private static final String PCAP_FILE = "E:\\workspace\\work_idea\\git\\apm\\data\\pcapfiles\\6_eth2_20210325100025.pcap";

	public static void main(String[] args) throws PcapNativeException, NotOpenException {
		PcapHandle handle;
		try {
			handle = Pcaps.openOffline(PCAP_FILE);
		} catch (PcapNativeException e) {
			handle = Pcaps.openOffline(PCAP_FILE);
		}

		for (int i = 0; i < COUNT; i++) {
			try {

				Packet packet = handle.getNextPacketEx();
				if(packet == null) {
					System.out.println("packet is null");
					break;
				}

				// 可以直接get你想要的报文类型，只要Pcap4J库原生支持
				EthernetPacket ethernetPacket = packet.get(EthernetPacket.class); // 以太网报文
				EtherType eth_type = ethernetPacket.getHeader().getType();
				//System.out.println(i);
				if(eth_type == EtherType.IPV4)
				{

					IpV4Packet ipv4_packet = packet.get(IpV4Packet.class);
					IpV4Packet.IpV4Header ipV4Packet_header = ipv4_packet.getHeader();

					if(ipV4Packet_header.getProtocol() == IpNumber.TCP)
					{
						TcpPacket tcp_packet = packet.get(TcpPacket.class);
						TcpPacket.TcpHeader tcp_header = tcp_packet.getHeader();
						if(!tcp_header.getPsh())
						{
							continue;
						}
						Inet4Address srcaddr = ipV4Packet_header.getSrcAddr();
						Inet4Address dstaddr = ipV4Packet_header.getDstAddr();
						TcpPort dstport = tcp_header.getDstPort();
						TcpPort srcport = tcp_header.getSrcPort();

						IpNumber protocol = ipV4Packet_header.getProtocol();
						System.out.println(i);
						System.out.println("seqno="+i+",（src ip,dst ip, src port,dst port, protocol):("+srcaddr+","+dstaddr+","+srcport+","+dstport+","+protocol+")");

						String tcpdata = new String(ipv4_packet.getPayload().getRawData());
//						System.out.println("tcp data is:" + tcpdata);

					}


				}else{

				}
                /*
                TcpPacket tcpPacket = packet.get(TcpPacket.class); // TCP报文
                IpV4Packet ipV4Packet = packet.get(IpV4Packet.class); // 直接获取IpV4报文
                System.out.println(srcAddr); // 输出源IP地址
                // 也可以通过getPayload()的方式一层一层读取
                EthernetHeader ethernetHeader = ethernetPacket.getHeader(); // 读取以太网帧头部
                IpV4Packet ipV4Packet2 = (IpV4Packet)ethernetPacket4j.getPayload(); // 注意get出来的类型，强转可能抛异常
                // 若需要解析的协议Pcap没有支持，那就需要自己实现这个报文的Java类，然后写反序列化方法了
                byte[] rawData = ethernetPacket.getRawData(); // 获取以太网的原始二进制数据
————————————————*/

				//System.out.println(packet);
			} catch (TimeoutException e) {
			} catch (EOFException e) {
				System.out.println("EOF");
				break;
			}
		}

		handle.close();
	}
}