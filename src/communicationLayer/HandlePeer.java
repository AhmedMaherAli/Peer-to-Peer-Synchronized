package communicationLayer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import localDataLayer.QueueingModule;

public class HandlePeer implements IHandlePeer{
	private IQueueingModule cache;
	private MulticastSocket socket;
	private InetAddress group;
	private boolean StopSending;
	public HandlePeer(IQueueingModule cache)
	{
		this.cache=cache;
		StopSending=false;
		try {
			group = InetAddress.getByName("230.0.0.0");
			socket = new MulticastSocket(4441);
			socket.joinGroup(group);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void send(String msg)
	{
		DatagramSocket socket1;
		byte[] buf;    
		try {
			socket1 = new DatagramSocket();
	        buf = msg.getBytes();
	 
	        DatagramPacket packet 
	          = new DatagramPacket(buf, buf.length, group, 4441);
	        socket1.send(packet);
	        System.out.println(packet.getAddress()+": message is sended");
	        socket1.close();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void transfer(String msg)
	{
		if(StopSending)
		{
			try {
				TimeUnit.SECONDS.sleep(8000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			StopSending=false;
			
		}
		this.send(msg);
		
	}
	public void sendWaitingAck()
	{
		this.send("Rx21&%ReDa;s)EXs@!Q31OKz|");
	}
	public void sendBreakAck()
	{
		this.send("Iam leaved");
	}
	public void receive()
	{
		byte[] buf = new byte[256];
		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		try {
			socket.receive(packet);
			 String received = new String(
		              packet.getData(), 0, packet.getLength());
			 if(received.equals("Rx21&%ReDa;s)EXs@!Q31OKz|"))
			 {
				 StopSending=true;
			 }
			 else if(received.length()==26)
			 {
				boolean isNotFull= cache.insert(received);
				if(!isNotFull)
				{
					System.out.println("my cache is full");
					this.send("Rx21&%ReDa;s)EXs@!Q31OKz|");
				}
			 }
			 System.out.println(packet.getAddress()+" "+received);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void leaveGroup()
	{
		try {
			socket.leaveGroup(group);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
