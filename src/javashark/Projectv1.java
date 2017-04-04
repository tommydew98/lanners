package javashark;

import org.jnetpcap.Pcap;
import org.jnetpcap.packet.JFlowMap;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.JPacketHandler;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Icmp;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.network.Ip6;
import org.jnetpcap.protocol.tcpip.Http;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;
import org.jnetpcap.packet.format.FormatUtils;

import java.util.ArrayList;

import static org.jnetpcap.packet.format.FormatUtils.ip;

/**
 * Created by Tommy on 2017-04-01.
 */
public class Projectv1 {
     public static void main(String[] args){
         final String myPcap = "myfile1.pcap";
         final StringBuilder mrString = new StringBuilder();
         final Pcap pcap = Pcap.openOffline(myPcap, mrString);

         if (pcap == null) {
             System.err.println(mrString);
             return;
         }

//         pcap.loop(20, new JPacketHandler<StringBuilder>() {
//
//             final Tcp tcp = new Tcp();
//
//             final Http http = new Http();
//
//             final Ip4 ipv4 = new Ip4();
//
//             final Icmp icmp = new Icmp();
//
//             final Ethernet ethernet = new Ethernet();
//
//             final Ip6 ip6 = new Ip6();
//
//             final Udp upd = new Udp();
//
//             public void nextPacket(JPacket packet, StringBuilder mrString){
//                 if (packet.hasHeader(Icmp.ID)){
//                     packet.getHeader(icmp);
//                     System.out.println(icmp.toString());
//
//                 }
//                 if (packet.hasHeader(Ip4.ID)){
//                     packet.getHeader(ipv4);
//                    System.out.println(ipv4.source());
//                    System.out.println((ipv4.destination()));
//
//                 }
//
//                 System.out.printf("frame #%d%n", packet.getFrameNumber());
//             }
//
//
//         }, mrString);
         JFlowMap superFlowMap = new JFlowMap();

         ArrayList<Integer> packSize = new ArrayList<>();
         pcap.loop(Pcap.LOOP_INFINITE, new JPacketHandler<StringBuilder>() {
             final Ip4 ipv4 = new Ip4();
             @Override
             public void nextPacket(JPacket jPacket, StringBuilder stringBuilder) {
                  if (jPacket.hasHeader(Ip4.ID)){

                      jPacket.getHeader(ipv4);
                      packSize.add(jPacket.getTotalSize());
                      System.out.println(ip(ipv4.source()));
                      System.out.println((ip(ipv4.destination())));
                      System.out.println(jPacket.getTotalSize()+" bytes");

                 }
             }
         }, mrString);
         //System.out.printf("superFlowMap::%s%n", superFlowMap);
         System.out.println("Total # of packets: "+packSize.size());
         System.out.println(packSize);
         int total=0;
         for(int i : packSize){
             total= total+i;
         }
         System.out.println("Average Packet Size: "+total/packSize.size()+" bytes");

//Pcap.LOOP_INFINITE
         pcap.close();
     }
}
