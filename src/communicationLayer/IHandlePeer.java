package communicationLayer;

public interface IHandlePeer {
	public void sendWaitingAck();
	public void send(String msg);
	public String receive();
	public void leaveGroup();
}
