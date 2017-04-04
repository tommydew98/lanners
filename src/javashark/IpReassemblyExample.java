//package javashark;
//
//import org.jnetpcap.Pcap;
//import org.jnetpcap.packet.PcapPacket;
//import org.jnetpcap.packet.PcapPacketHandler;
//
///**
// * Created by Tommy on 2017-03-27.
// */
//public class IpReassemblyExample implements PcapPacketHandler<Object> {
//    public void main(String[] args){
//        final String FILENAME = "myfile.pcap";
//        final StringBuilder errbuf = new StringBuilder();
//
//        final Pcap pcap = Pcap.openOffline(FILENAME, errbuf);
//        if (pcap == null) {
//            System.err.println(errbuf); // Error is stored in errbuf if any
//            return;
//        }
//        pcap.loop(
//                6, new IpReassemblyExample(5 *1000, new IpReassemblyBufferHandler(){}), "");
//                )
//        );
//    }
//
//    @Override
//    public void nextPacket(PcapPacket pcapPacket, Object object) {
//
//    }
//}
//</Object>