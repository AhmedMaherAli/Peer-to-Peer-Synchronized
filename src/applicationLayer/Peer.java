package applicationLayer;

import communicationLayer.CachingThread;
import communicationLayer.CircuitBreaker;
import communicationLayer.FakeDataGenerator;
import communicationLayer.HandlePeer;
import communicationLayer.IHandlePeer;
import communicationLayer.IQueueingModule;
import communicationLayer.ReceivingThread;
import localDataLayer.DataStore;
import localDataLayer.QueueingModule;

public class Peer {
	private DataStore localData;
	private IHandlePeer handleNetwork;
	private IQueueingModule queueingModule;
	private ReceivingThread receiveThread;
	private CachingThread cacheThread;
	private CircuitBreaker circuit;
	private FakeDataGenerator generator;
	public Peer(int cacheLimit,int dataLimit)
	{
		localData=new DataStore(dataLimit);
		queueingModule=new QueueingModule(localData,cacheLimit);
		handleNetwork=new HandlePeer(queueingModule);
		receiveThread=new ReceivingThread(handleNetwork);
		circuit=new CircuitBreaker(handleNetwork);
		cacheThread=new CachingThread(queueingModule, circuit);
		generator=new FakeDataGenerator(handleNetwork, queueingModule);
	}
	public void start()
	{
		generator.start();
		receiveThread.start();
		cacheThread.start();
	}
	
}
