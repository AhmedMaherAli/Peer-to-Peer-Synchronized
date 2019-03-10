package communicationLayer;

public class ReceivingThread extends Thread{
	private IHandlePeer handleNetwork;
	public ReceivingThread(IHandlePeer handleNetwork)
	{
		this.handleNetwork=handleNetwork;
	}
	@Override
	public void run()
	{
		while(true)
		{
			handleNetwork.receive();
		}
	}
}
