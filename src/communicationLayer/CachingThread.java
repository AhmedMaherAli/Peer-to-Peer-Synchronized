package communicationLayer;

public class CachingThread extends Thread{
	private IHandlePeer handleNetwork;
	private CircuitBreaker circuit;
	public CachingThread(IHandlePeer handleNetwork,CircuitBreaker circuit)
	{
		this.handleNetwork=handleNetwork;
		this.circuit=circuit;
	}
	@Override
	public void run()
	{
		
	}
}
