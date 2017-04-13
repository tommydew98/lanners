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

import java.net.UnknownHostException;
import java.util.ArrayList;

import static org.jnetpcap.packet.format.FormatUtils.ip;

/**
 * Created by Tommy on 2017-04-01.
 */
public class Projectv1 {
    public static String hostAddress;

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
         ArrayList<Long> retransmissions = new ArrayList<>();
         pcap.loop(Pcap.LOOP_INFINITE, new JPacketHandler<StringBuilder>() {
             final Ip4 ipv4 = new Ip4();
             final Tcp tcp = new Tcp();
             final Http http = new Http();
             final DNS dns = new DNS(); //ADD THIS
             final Ip6 ipv6 = new Ip6(); //ADD THIS

             Long ack = Long.valueOf(1);
             Long seq = Long.valueOf(1);

             ArrayList<String> hostAddresses = new ArrayList<String>();
             ArrayList<String> reqAddresses = new ArrayList<String>();

             @Override
             public void nextPacket(JPacket jPacket, StringBuilder stringBuilder) {

                 System.out.println(jPacket.getHeader(ipv6)); // Checking if ipv6 is present in .pcap

                  if (jPacket.hasHeader(Ip4.ID)){

                      jPacket.getHeader(ipv4);
                      packSize.add(jPacket.getTotalSize());
                      System.out.println(ip(ipv4.source()));
                      System.out.println((ip(ipv4.destination())));
                      System.out.println(jPacket.getTotalSize()+" bytes");

                 }

                 if (jPacket.hasHeader(Ip6.ID)){

                     jPacket.getHeader(ipv6);

                     hostAddress = (ip(ipv6.source()));

                     try {
                         System.out.println(dns.getHost());
                         System.out.println(dns.getCanHost());
                     }
                     catch (UnknownHostException e) {
                         e.printStackTrace();
                     }
                 }

                 if(jPacket.hasHeader(http)){

                     String hostUrl = http.fieldValue((Http.Request.Host.Host));
                     String reqUrl = http.fieldValue((Http.Request.Host.RequestUrl));

                     System.out.println("Address Name: ");
                     System.out.println(hostUrl);
                     System.out.println(reqUrl);

                     hostAddresses.add(hostUrl);
                     reqAddresses.add(reqUrl);

                 }

                 if (jPacket.hasHeader(tcp)){

                     jPacket.getHeader(tcp);

                     if(ack.equals(tcp.ack()) && seq.equals(tcp.seq())){
                         //add frame# to list
                        retransmissions.add(jPacket.getFrameNumber());
                     } else {
                         ack = tcp.ack();
                         seq = tcp.seq();
                     }

                     System.out.println("ACK "+tcp.ack());
                     System.out.println("SEQ "+tcp.seq());


                 }
             }
         }, mrString);
         //System.out.printf("superFlowMap::%s%n", superFlowMap);
         System.out.println("Total # of packets: "+packSize.size());
         System.out.println(packSize);
         System.out.println(retransmissions);
         int total=0;
         for(int i : packSize){
             total= total+i;
         }

         int size = retransmissions.size();

         System.out.println("Average Packet Size: "+total/packSize.size()+" bytes");
         System.out.println("Number of Retransmissions: "+retransmissions.size());

//Pcap.LOOP_INFINITE
         pcap.close();
     }

}
