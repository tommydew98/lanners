package javashark;

import org.jnetpcap.Pcap;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.JPacketHandler;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.network.Ip6;
import org.jnetpcap.protocol.tcpip.Http;
import org.jnetpcap.protocol.tcpip.Tcp;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.jnetpcap.packet.format.FormatUtils.ip;

/**
 * Created by Tommy on 2017-04-11.
 */
public class PcapParse {

	private ArrayList<Integer> packSize = new ArrayList<>();
    private ArrayList<Long> retransmissions = new ArrayList<>();

    private static PcapParse instance = null;
    private String fileName;
    
    protected String hostAddress;

    protected PcapParse(){

    }
    public static PcapParse getInstance(){
        if(instance == null){
            instance = new PcapParse();
        }
        return instance;
    }


    public ArrayList<Integer> getPackList(){
        return packSize;
    }

    public void scan(String fileName){


        final StringBuilder mrString = new StringBuilder();
        Pcap pcap = Pcap.openOffline(fileName, mrString);

        if (pcap == null) {
            System.err.println(mrString);
            return;
        }

        packSize = new ArrayList<>();
        retransmissions = new ArrayList<>();
        pcap.loop(Pcap.LOOP_INFINITE, new JPacketHandler<StringBuilder>() {
            final Ip4 ipv4 = new Ip4();
            final Tcp tcp = new Tcp();
            
            //DNS and Http 
            final Http http = new Http();
            final DNS dns = new DNS();
            final Ip6 ipv6 = new Ip6();
            
            Long ack = Long.valueOf(1);
            Long seq = Long.valueOf(1);
            
            //Arraylist of host and requestHost addresses
            ArrayList<String> hostAddresses = new ArrayList<String>();
            ArrayList<String> reqAddresses = new ArrayList<String>();
            
            HashMap<String, Integer> DnsMap = new HashMap<String, Integer>();
            
            @Override
            public void nextPacket(JPacket jPacket, StringBuilder stringBuilder) {
            	//ipv4
                if (jPacket.hasHeader(Ip4.ID)){

                    jPacket.getHeader(ipv4);
                    packSize.add(jPacket.getTotalSize());

                }
                
                //DNS
                if (jPacket.hasHeader(Ip6.ID)){
               	 
               	 jPacket.getHeader(ipv6);
               	 
               	 hostAddress = (ip(ipv6.source()));
               	 
               	 try {
               		 	System.out.println("DNS Information:");
    					System.out.println(dns.getHost());
    					System.out.println(dns.getCanHost());
    					
			            Integer value = DnsMap.get(dns.getHost());
			            if (value == null)
			                value = 0;
			            value++;
			            DnsMap.put(dns.getHost(), value);
    				} 
               	 catch (UnknownHostException e) {
    					e.printStackTrace();
    				}
                }
                
                //Http
                if(jPacket.hasHeader(http)){
               	 
               	 String hostUrl = http.fieldValue((Http.Request.Host.Host));
               	 String reqUrl = http.fieldValue((Http.Request.Host.RequestUrl));
               	 
               	 System.out.println("Address Name: ");
               	 System.out.println(hostUrl);
               	 System.out.println(reqUrl);
               	 
               	 hostAddresses.add(hostUrl);
               	 reqAddresses.add(reqUrl);
               	 
                }
                
                //TCP
                if (jPacket.hasHeader(tcp)){

                    jPacket.getHeader(tcp);

                    if(ack.equals(tcp.ack()) && seq.equals(tcp.seq())){
                        //add frame# to list
                        retransmissions.add(jPacket.getFrameNumber());
                    } else {
                        ack = tcp.ack();
                        seq = tcp.seq();
                    }



                }
            }
        }, mrString);

        int total=0;
        for(int i : packSize){
            total= total+i;
        }
        

//Pcap.LOOP_INFINITE
        pcap.close();
    }
    
    public String getAddr(){
    	return hostAddress;
    }

    public int getPack(){
        return packSize.size();
    }

    public int getRetransmissions(){
        return retransmissions.size();
    }
    
}