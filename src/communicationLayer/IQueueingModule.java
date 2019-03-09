package communicationLayer;

public interface IQueueingModule {
	public boolean insert(String msg);
	public boolean pushToDataStore(String msg);
}
