package communicationLayer;

public class CircuitBreaker {
	private IHandlePeer handleNetwork;
	public CircuitBreaker(IHandlePeer handleNetwork)
	{
		this.handleNetwork=handleNetwork;
	}

}
