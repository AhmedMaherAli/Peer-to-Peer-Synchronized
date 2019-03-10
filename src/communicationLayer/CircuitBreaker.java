package communicationLayer;

public class CircuitBreaker {
	private IHandlePeer handleNetwork;
	private boolean status;
	public CircuitBreaker(IHandlePeer handleNetwork)
	{
		status=true;
		this.handleNetwork=handleNetwork;
	}
	public void close()
	{
		status=true;
	}
	public void open()
	{
		handleNetwork.sendBreakAck();
		handleNetwork.leaveGroup();
		status=false;
	}
	public boolean status()
	{
		return status;
	}

}
