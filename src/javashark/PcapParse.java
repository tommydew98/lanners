package javashark;

import org.jnetpcap.Pcap;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.JPacketHandler;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Tcp;

import java.util.ArrayList;

import static org.jnetpcap.packet.format.FormatUtils.ip;

/**
 * Created by Tommy on 2017-04-11.
 */
public class PcapParse {



    private ArrayList<Integer> packSize = new ArrayList<>();
    private ArrayList<Long> retransmissions = new ArrayList<>();
    private String fileName;
    public PcapParse(String file){
        this.fileName = file;
    }

    public ArrayList<Integer> getPackList(){
        return packSize;
    }

    public void scan(){

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
            Long ack = Long.valueOf(1);
            Long seq = Long.valueOf(1);
            @Override
            public void nextPacket(JPacket jPacket, StringBuilder stringBuilder) {
                if (jPacket.hasHeader(Ip4.ID)){

                    jPacket.getHeader(ipv4);
                    packSize.add(jPacket.getTotalSize());
                    System.out.println(ip(ipv4.source()));
                    System.out.println((ip(ipv4.destination())));
                    System.out.println(jPacket.getTotalSize()+" bytes");

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
        System.out.println("Total # of packets: "+packSize.size());
        System.out.println(packSize);
        System.out.println(retransmissions);
        int total=0;
        for(int i : packSize){
            total= total+i;
        }
        System.out.println("Average Packet Size: "+total/packSize.size()+" bytes");
        System.out.println("Number of Retransmissions: "+retransmissions.size());

//Pcap.LOOP_INFINITE
        pcap.close();
    }

    public int getPack(){
        return packSize.size();
    }

    public int getRetransmissions(){
        return retransmissions.size();
    }
}
