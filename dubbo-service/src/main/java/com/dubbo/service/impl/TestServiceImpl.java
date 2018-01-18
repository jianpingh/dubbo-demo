package com.dubbo.service.impl;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dubbo.service.TestService;

public class TestServiceImpl implements TestService{

	private static final Logger LOGGER = LoggerFactory.getLogger(TestService.class);

	public String sayHello(String name) {
		
		
		LOGGER.info("dubbo info test");
		String ip="";
		try {
			ip = getLinuxLocalIp();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		return name + "get say hello word service and server ip address is "+ip;
	}
	
	 private static String getLinuxLocalIp() throws SocketException {
	        String ip = "";
	        try {
	            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
	                NetworkInterface intf = en.nextElement();
	                String name = intf.getName();
	                if (!name.contains("docker") && !name.contains("lo")) {
	                    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
	                        InetAddress inetAddress = enumIpAddr.nextElement();
	                        if (!inetAddress.isLoopbackAddress()) {
	                            String ipaddress = inetAddress.getHostAddress().toString();
	                            if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
	                                ip = ipaddress;
	                                System.out.println(ipaddress);
	                            }
	                        }
	                    }
	                }
	            }
	        } catch (SocketException ex) {
	            System.out.println("获取ip地址异常");
	            ip = "127.0.0.1";
	            ex.printStackTrace();
	        }
	        System.out.println("IP:"+ip);
	        return ip;
	    }
}
