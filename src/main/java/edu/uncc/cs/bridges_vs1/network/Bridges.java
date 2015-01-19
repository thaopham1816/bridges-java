package edu.uncc.cs.bridges_vs1.network;

/**
 * Connection to the Bridges server.
 * 
 * Initialize this class before using it, and call complete() afterward.
 * 
 * @author Sean Gallagher
 * @param <E>
 * @secondAuthor Mihai Mehedint
 */

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import edu.uncc.cs.bridges_vs1.sources.Tweet;
import edu.uncc.cs.bridges_vs1.structure.ADTVisualizer;
import edu.uncc.cs.bridges_vs1.structure.DLelement;
import edu.uncc.cs.bridges_vs1.structure.Element;
import edu.uncc.cs.bridges_vs1.structure.GraphList;
import edu.uncc.cs.bridges_vs1.structure.SLelement;
import edu.uncc.cs.bridges_vs1.validation.RateLimitException;

public class Bridges <E> {
	
	private static double assignmentDecimal = 0.0;
	public ADTVisualizer<E> visualizer ;
	private Element<E> root;
	private static int assignment;
	private static String key;

	private static DataFormatter formatter;
	private static String userName;
	public final Map<String, String> ADT_UPDATE = new HashMap <String, String>(){{
		put("graph","updateGraph");
		put("graphl","updateGraphL");
		put("stack","stack");
		put("queue","queue");
		put("tree","tree");
		put("llist", "updateSL");
		put("Dllist", "updateDL");
		}};
	public java.lang.reflect.Method method;
	
	/**
	 * Constructors
	 * @throws Exception 
	 */
	public Bridges() throws Exception {
		super();
		visualizer = new ADTVisualizer<>();
		Bridges.formatter = new DataFormatter();
	}

	public Bridges(int assignment, String key, String username) throws Exception{
		this();
		init(assignment, key, username);
	}
	
	public Bridges(int assignment, String key, SLelement<E> e, String username) throws Exception{
		this();
		init(assignment, key, e, username);
	}

	/**
	 * Initialize DataFormatters with Visualizer
	 * @param <E>
	 * @param assignment  The assignment number, for grading
	 * @param visualizer  The visualizer, for assignment
	 * @param username 
	 * @throws Exception 
	 */
	public <E> void init(int assignment, String key, String username){
		Bridges.setAssignment(assignment);
		Bridges.key = key;
		Bridges.userName = username;
		
	}
	
	/**
	 * Initialize DataFormatters with Visualizer
	 * @param <E>
	 * @param assignment  The assignment number, for grading
	 * @param visualizer  The visualizer, for assignment
	 * @param username TODO
	 */
	public void init(int assignment, String key, Element<E> e, String username) throws Exception{
		init(assignment, key, username);
		root = e;
	}
	

	
	/* Accessors and Mutators */
	public static String getAssignment() {
		return Double.toString(assignment+assignmentDecimal);
	}
	public static void setAssignment(int assignment) {
		Bridges.assignment = assignment;
	}
	
	// This exists to prevent duplicate error traces.
	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		Bridges.userName = userName;
	}
	
	public static String getKey() {
		return key;
	}
	
	public static void setKey(String key) {
		Bridges.key = key;
	}

	
	/**
	 * This method returns the current visualizer
	 * @return visualizer
	 */
	public ADTVisualizer<E> getVisualizer() {
		return visualizer;
	}
	/**
	 * This method sets the new DataFormatter visualizer
	 * @param visualizer
	 */
	public void setVisualizer(ADTVisualizer<E> visualizer) {
		this.visualizer = visualizer;
	}
	
	/**
	 * This method sets the HashMap and the type of ADT for the ADTVisualizer object
	 * @param mapOfLinks
	 * @param visualizerType
	 * @throws Exception
	 */
	public void setDataStructure(HashMap<Element<E>, HashMap<String, Element<E>>> mapOfLinks,
			String visualizerType) throws Exception{
		visualizer.setMapOfLinks(mapOfLinks);
		visualizer.setVisualizerType(visualizerType);
		this.assignment = assignment;
		visualize();
	}
	
	/**
	 * This method sets the first element and the type of ADT for the ADTVisualizer object
	 * @param e - is a SLelement<E>
	 * @param visualizerType
	 * @throws Exception
	 */
	public void setDataStructure(SLelement<E> e, 
			String visualizerType) throws Exception{
		root = e;
		visualizer.setVisualizerType(visualizerType);
		this.assignment = assignment;
		visualize();
	}
	
	/**
	 * This method sets the first element and the type of ADT for the ADTVisualizer object
	 * @param e - is a DLelement<E>
	 * @param visualizerType
	 * @throws Exception
	 */
	public void setDataStructure(DLelement<E> e, 
			String visualizerType) throws Exception{
		root = e;
		visualizer.setVisualizerType(visualizerType);
		this.assignment = assignment;
		visualize();
	}
	
	/**
	 * This method is sets the adjacency list for the Graph ADT
	 * @param adjacencyList
	 * @param visualizerType
	 * @throws Exception
	 */
	public void setDataStructure(
			String visualizerType,
			HashMap<String, SLelement<E>> adjacencyList) throws Exception{
		visualizer.setAdjacencyList(adjacencyList);
		visualizer.setVisualizerType(visualizerType);
		visualize();
	}
	
	/**
	 * This method adds one Element to the ADTVisualizer object
	 * @param e
	 * @throws Exception
	 */
	public void add(Element<E> e) throws Exception{
		visualizer.add(e);
	}
	
	/**
	 * This methods sets a link between 2 given elements of the ADTVisualizer
	 * @param source
	 * @param target
	 * @throws Exception
	 */
	public void setLink(Element<E> source, Element<E> target) throws Exception{
		visualizer.setLink(source, target);
	}
	
	/**
	 * This method returns the current JSON
	 * @return JSON string
	 */
	public String getJSON(){
		if (visualizer.getVisualizerType().compareToIgnoreCase("graph")==0 && visualizer.getAdjacencyList() == null)
			return visualizer.getGraphRepresentation();
		else if (visualizer.getVisualizerType().equalsIgnoreCase("llist"))
			return visualizer.getSLRepresentation((SLelement<E>)root);
		else if (visualizer.getVisualizerType().equalsIgnoreCase("Dllist"))
			return visualizer.getDLRepresentation((DLelement<E>)root);
		else if (visualizer.getVisualizerType().compareToIgnoreCase("graph")==0 && visualizer.getAdjacencyList() != null)
			return visualizer.getGraphLRepresentation();
		else
			return visualizer.getGraphRepresentation();
	}
	
	/**
	 * This method calls the updateGraph() or updateSL() methods
	 * depending upon the type of ADT being created.
	 * These methods send the JSON to post() which ultimately executes the http request
	 * from the server
	 */
	/*
	public void visualize(){
		try {
			this.getClass().getMethod(ADT_UPDATE.get(visualizer.getVisualizerType()));
			//method.invoke(ADT_UPDATE.get(visualizer.getVisualizerType()));
			System.out.println(ADT_UPDATE.get(visualizer.getVisualizerType()));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	*/
	
	
	
	  public void visualize(){
		if (visualizer.visualizerType.equalsIgnoreCase("graph") && visualizer.getAdjacencyList()==null)
			this.updateGraph();
		else if (visualizer.visualizerType.equalsIgnoreCase("llist"))
			this.updateSL();
		else if (visualizer.visualizerType.equalsIgnoreCase("Dllist"))
			this.updateDL();
		else if (visualizer.visualizerType.equalsIgnoreCase("graph") && visualizer.getAdjacencyList()!=null)
			this.updateGraphL();
		else{
			if (visualizer.getMapOfLinks() == null) 
				throw new NullPointerException(); 
			this.updateGraph();
		}
		
	}
	
	
	/**
	 * Update visualization metadata of graph using adjacency matrix. This may be called many times.
	 * This is usually an expensive operation and involves connecting to the network.
	 * Calling this method is optional provided you call complete()
	 */
	public void updateGraph() {
        try {
			formatter.getBackend().post("/assignments/" + getAssignment(), visualizer.getGraphRepresentation());
		} catch (IOException e) {
			System.err.println("There was a problem sending the visualization"
					+ " representation to the server. Are you connected to the"
					+ " Internet? Check your network settings. Otherwise, maybe"
					+ " the central DataFormatters server is down. Try again later.\n"
					+ e.getMessage());
		} catch (RateLimitException e) {
			System.err.println("There was a problem sending the visualization"
					+ " representation to the server. However, it responded with"
					+ " an impossible 'RateLimitException'. Please contact"
					+ " DataFormatters developers and file a bug report; this error"
					+ " should not be possible.\n"
					+ e.getMessage());
		} 
        // Return a URL to the user
        System.out.println("Check out your visuals at " + formatter.getBackend().prepare("/assignments/" + getAssignment() + "/" + userName) );
        assignmentDecimal+=0.01;
	}
	
	/**
	 * Update visualization metadata of singly linked list. This may be called many times.
	 * This is usually an expensive operation and involves connecting to the network.
	 * Calling this method is optional provided you call complete()
	 */
	public void updateSL() {
        try {
        	formatter.getBackend().post("/assignments/" + getAssignment(), visualizer.getSLRepresentation((SLelement<E>)root));
		} catch (IOException e) {
			System.err.println("There was a problem sending the visualization"
					+ " representation to the server. Are you connected to the"
					+ " Internet? Check your network settings. Otherwise, maybe"
					+ " the central DataFormatters server is down. Try again later.\n"
					+ e.getMessage());
		} catch (RateLimitException e) {
			System.err.println("There was a problem sending the visualization"
					+ " representation to the server. However, it responded with"
					+ " an impossible 'RateLimitException'. Please contact"
					+ " DataFormatters developers and file a bug report; this error"
					+ " should not be possible.\n"
					+ e.getMessage());
		} 
        // Return a URL to the user
        System.out.println("Check out your visuals at " + formatter.getBackend().prepare("/assignments/" + getAssignment() + "/" + userName) );
        assignmentDecimal+=0.01;
	}
	
	/**
	 * Update visualization metadata of doubly linked list. This may be called many times.
	 * This is usually an expensive operation and involves connecting to the network.
	 * Calling this method is optional provided you call complete()
	 */
	public void updateDL() {
        try {
        	formatter.getBackend().post("/assignments/" + getAssignment(), visualizer.getDLRepresentation((DLelement<E>)root));
		} catch (IOException e) {
			System.err.println("There was a problem sending the visualization"
					+ " representation to the server. Are you connected to the"
					+ " Internet? Check your network settings. Otherwise, maybe"
					+ " the central DataFormatters server is down. Try again later.\n"
					+ e.getMessage());
		} catch (RateLimitException e) {
			System.err.println("There was a problem sending the visualization"
					+ " representation to the server. However, it responded with"
					+ " an impossible 'RateLimitException'. Please contact"
					+ " DataFormatters developers and file a bug report; this error"
					+ " should not be possible.\n"
					+ e.getMessage());
		} 
        // Return a URL to the user
        System.out.println("Check out your visuals at " + formatter.getBackend().prepare("/assignments/" + getAssignment() + "/" + userName) );
        assignmentDecimal+=0.01;
	}

	/**
	 * Update visualization metadata of Graph with Adjacency List. This may be called many times.
	 * This is usually an expensive operation and involves connecting to the network.
	 * Calling this method is optional provided you call complete()
	 */
	public void updateGraphL() {
        try {
        	formatter.getBackend().post("/assignments/" + getAssignment(), visualizer.getGraphLRepresentation());
		} catch (IOException e) {
			System.err.println("There was a problem sending the visualization"
					+ " representation to the server. Are you connected to the"
					+ " Internet? Check your network settings. Otherwise, maybe"
					+ " the central Bridges server is down. Try again later.\n"
					+ e.getMessage());
		} catch (RateLimitException e) {
			System.err.println("There was a problem sending the visualization"
					+ " representation to the server. However, it responded with"
					+ " an impossible 'RateLimitException'. Please contact"
					+ " DataFormatters developers and file a bug report; this error"
					+ " should not be possible.\n"
					+ e.getMessage());
		} 
        // Return a URL to the user
        System.out.println("Check out your visuals at " + formatter.getBackend().prepare("/assignments/" + getAssignment() + "/" + userName) );
        assignmentDecimal+=0.01;
	}

	/**
	 * Close down DataFormatters.
	 * 
	 * This only calls update() but it could conceivably do more.
	 * @param i 
	 */
	public void complete() {
		visualize();
	}	
}
