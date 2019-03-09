package communicationLayer;

import localDataLayer.QueueingModule;

public class HandlePeer implements IHandlePeer{
	private IQueueingModule cache;
	public HandlePeer(IQueueingModule cache)
	{
		this.cache=cache;
	}
}
