package communicationLayer;

public class FakeDataGenerator extends Thread{
	private IHandlePeer handleNetwork;
	private IQueueingModule cache;
	public FakeDataGenerator(IHandlePeer handleNetwork,IQueueingModule cache)
	{
		this.handleNetwork=handleNetwork;
		this.cache=cache;
	}
	@Override
	public void run()
	{
		
	}
}
