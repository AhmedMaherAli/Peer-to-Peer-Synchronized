package communicationLayer;

public class CachingThread extends Thread{
	private IQueueingModule cache;
	private CircuitBreaker circuit;
	public CachingThread(IQueueingModule cache,CircuitBreaker circuit)
	{
		this.cache=cache;
		this.circuit=circuit;
	}
	@Override
	public void run()
	{
		while(true)
		{
			String msg=cache.poll();
			if(msg!=null)
			{
				boolean isStoreNotFull=cache.pushToDataStore(msg);
				if(!isStoreNotFull)
				{
					circuit.open();
				}
			}
		}
	}
}
