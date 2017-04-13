package javashark;

import java.net.InetAddress;
import java.net.UnknownHostException;

//Credits - http://www.avajava.com/tutorials/lessons/how-do-i-use-a-host-name-to-look-up-an-ip-address.html
//Author: Dickson Chan

public class DNS extends PcapParse {
	
	public static void main(String[] args) {
		
//		
//		try {
//			System.out.println("--------------------------");
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}
		
	}

	public static void displayStuff(String whichHost, InetAddress inetAddress) {
		
		//Test code, use only to test ipv6 for addresses
		
		System.out.println("--------------------------");
		System.out.println("Which Host:" + whichHost);
		System.out.println("Canonical Host Name:" + inetAddress.getCanonicalHostName());
		System.out.println("Host Name:" + inetAddress.getHostName());
		System.out.println("Host Address:" + inetAddress.getHostAddress());
	}
	
	public String getHost() throws UnknownHostException{
		
		PcapParse pp = new PcapParse();
		
		InetAddress ia = InetAddress.getByName(pp.getInstance().hostAddress);
		
		String host = ia.getHostName();
		
		return host;
	}
	
	public String getCanHost() throws UnknownHostException{
		
		PcapParse pp = new PcapParse();
		
		InetAddress ia = InetAddress.getByName(pp.getInstance().hostAddress);
		
		String canHost = ia.getCanonicalHostName();
		
		return canHost;
	}
	
	
}