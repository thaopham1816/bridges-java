package sketch;

public class BSTEdge extends AbstractEdge{

	private BSTNode outgoing;
	/**
	 * Creates an Edge between two nodes.
	 * 
	 * @param source The source node.
	 * @param destination The destination node.
	 * @param identifier The name of the Edge.
	 */
	public BSTEdge(BSTNode source, BSTNode destination, String identifier) {		
		super(source, destination, identifier);
		
		outgoing = destination;//edge outgoing pointer to the destination vertex	
	}
	/**
	 * Sets the outgoing pointer of the calling edge.
	 * 
	 * @param n The new node being referenced.
	 */
	public void setOutgoing(BSTNode n){
		this.outgoing = n;
	}
	/**
	 * 
	 * @return The pointer of the outgoing connection.
	 */
	public BSTNode getOutgoing(){
		return outgoing;
	}
}