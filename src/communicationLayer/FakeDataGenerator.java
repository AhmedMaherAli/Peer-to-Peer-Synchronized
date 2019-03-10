package communicationLayer;

import java.util.Random;

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
		while(true)
		{
			 Random random = new Random();
		        char[] word = new char[26]; // words of length 3 through 10. (1 and 2 letter words are boring.)
		        for(int j = 0; j < word.length; j++)
		        {
		            word[j] = (char)('a' + random.nextInt(26));
		        }
		        String next_word = new String(word);
		        handleNetwork.transfer(next_word);
		        boolean isNotFullCache=cache.insert(next_word);
		        if(!isNotFullCache)
					try {
						this.sleep(6000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    try {
				this.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
