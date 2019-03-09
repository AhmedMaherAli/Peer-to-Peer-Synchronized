package localDataLayer;

import java.util.ArrayList;

public class DataStore {
	private ArrayList<String> localData;
	private int limit;
	public DataStore(int limit) {
		localData=new ArrayList<>();
		this.limit=limit;
	}
	public boolean insert(String msg)
	{
		if(localData.size()>=limit)
			return false;
		localData.add(msg);
		return true;
	}
	public int dataSize()
	{
		return localData.size();
	}

}
