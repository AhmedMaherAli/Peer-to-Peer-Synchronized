package localDataLayer;

import java.util.LinkedList;
import java.util.Queue;

import communicationLayer.IQueueingModule;

public class QueueingModule implements IQueueingModule{
	private DataStore localData;
	private Queue<String> cache;
	private int limit;
	public QueueingModule(DataStore localData,int limit)
	{
		this.localData=localData;
		cache=new LinkedList<>();
		this.limit=limit;
	}
	public boolean insert(String msg)
	{
		if(cache.size()>=limit-1)
			return false;
		cache.add(msg);
		return true;
	}
	public boolean pushToDataStore(String msg)
	{
		return localData.insert(msg);
	}
}
