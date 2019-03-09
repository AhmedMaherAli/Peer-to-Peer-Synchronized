package communicationLayer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

import localDataLayer.QueueingModule;

public class HandlePeer implements IHandlePeer{
	private IQueueingModule cache;
	private MulticastSocket socket;
	private InetAddress group;
	public HandlePeer(IQueueingModule cache)
	{
		this.cache=cache;
		try {
			group = InetAddress.getByName("230.0.0.0");
			socket = new MulticastSocket(4441);
			socket.joinGroup(group);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void send(String msg)
	{
		DatagramSocket socket;
		InetAddress group;
		byte[] buf;    
		try {
			socket = new DatagramSocket();
			group = InetAddress.getByName("230.0.0.0");
	        buf = msg.getBytes();
	 
	        DatagramPacket packet 
	          = new DatagramPacket(buf, buf.length, group, 4441);
	        socket.send(packet);
	        socket.close();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void sendWaitingAck()
	{
		this.send("Rx21&%ReDa;s)EXs@!Q31OKz|");
	}
	public String receive()
	{
		byte[] buf = new byte[256];
		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		try {
			socket.receive(packet);
			 String received = new String(
		              packet.getData(), 0, packet.getLength());
			 return received;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "#";
		
	}
	public void leaveGroup()
	{
		try {
			socket.leaveGroup(group);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
