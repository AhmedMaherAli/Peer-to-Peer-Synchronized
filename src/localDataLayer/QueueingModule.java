package localDataLayer;

import communicationLayer.IQueueingModule;

public class QueueingModule implements IQueueingModule{
	private DataStore localData;
	public QueueingModule(DataStore localData)
	{
		this.localData=localData;
	}
}
