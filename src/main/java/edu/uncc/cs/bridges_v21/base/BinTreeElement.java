package bridges.base;

/**
 * @author mihai Mehedint, K.R. Subramanian
 *
 * This class can be used to create tree element objects
 * with left and right pointers
 *
 * Added to be a subclass of TreeElement (6/22/16), KRS
 *
 */

public class BinTreeElement<E> extends TreeElement<E>{
	private BinTreeElement<E> left; //the left pointer
	private BinTreeElement<E> right; //the right pointer
	
	/** 
	 *
	 * 	Constructs an empty Binary Tree Element with right and left 
	 *	pointers set to null. 
	 *
	 **/
	public BinTreeElement(){
		super();
		super.setLeft(null);
		super.setRight(null);
	}
	
	/** 
	 *	Constructs a TreeElement holding an object "e" with right and left 
	 *	pointers set to null. 
	 *
	 *	@param e the generic object that TreeElement will hold 
	 *
	 **/
	public BinTreeElement (E e){
		super(e);
		super.setLeft(null);
		super.setRight(null);
	}
	
	/** Constructs a TreeElement with label set to "label", holding an 
	 *	object "e". 
	 *
	 * @param label the label of TreeElement that shows up on the 
	 *	Bridges visualization
	 * @param e the generic object that TreeElement will hold 
	 **/
	public BinTreeElement (String label, E e){
		super(label, e);
		super.setLeft(null);
		super.setRight(null);
	}
	
	/** Constructs an empty TreeElement left pointer pointing to 
	 *	"left" and right pointer pointing to "right". 
	 * 	@param left the TreeElement to be assigned to the left pointer of 
	 *		this TreeElement
	 * 	@param right the TreeElement to be assigned to the right pointer of 
	 *		this TreeElement
	 * 
	 **/
	public BinTreeElement(BinTreeElement<E> left, 
						BinTreeElement<E> right) {
		super();
		super.setLeft(null);
		super.setRight(null);
	}
	
	/** 
	 *	Constructs a TreeElement holding the object "e", left pointer 
	 *	pointing to "left" and right pointer pointing to "right".
	 *
	 * 	@param e the generic object that TreeElement will hold 
	 * 	@param left the TreeElement to be assigned to the left pointer of this 
	 *		TreeElement
	 * 	@param right the TreeElement to be assigned to the right pointer of 
	 *		this TreeElement
	 *
	 **/
	public BinTreeElement(E e, BinTreeElement<E> left, 
							BinTreeElement<E> right) {
		super();
		super.setLeft(null);
		super.setRight(null);
	}
	
	/**
	 *
	 * This method returns the left tree element pointer
	 * @return the left child of this TreeElement
	 *
	 */
	public BinTreeElement<E> getLeft() {
		return (BinTreeElement<E>) super.getLeft();
	}
	
	/**
	 *
	 * This method sets the left tree element pointer
	 * @param left the TreeElement that should be assigned to the left child
	 *
	 */
	public void setLeft(BinTreeElement<E> left) {
		super.setLeft(left);
	}
	

	/**
	 *
	 * This method returns the right tree element pointer
	 * @return the right child of this TreeElement
	 *
	 **/
	public BinTreeElement<E> getRight() {
		return (BinTreeElement<E>) super.getRight();
	}
	
	/**
	 *
	 * This method sets the right tree element pointer
	 * @param right the TreeElement that should be assigned to the right child
	 *
	 */
	public void setRight(BinTreeElement<E> right) {
		super.setRight(right);
	}
}
