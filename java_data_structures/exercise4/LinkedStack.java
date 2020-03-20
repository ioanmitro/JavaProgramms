class LinkedNode <T> {
	private T o;
	private LinkedNode next;
  
 	public LinkedNode(LinkedNode nxt, T e) {
		next = nxt;
		o = e;
	}
  
	public LinkedNode(T e) {
		this(null, e);
	}
  
	public T getElement() { return o; }
		public LinkedNode getNext() { return next; }
  
		public void setElement(T e) { o = e; }
		public void setNext(LinkedNode node) { next = node; }
	}

public class LinkedStack <T> implements Stack <T>{
	int size;
	LinkedNode head;

	public LinkedStack() {
		size=0;
		head = null;
	}
  
	public int size() {
		return size;
	}
  
	public void push(T o) {
		// the following is OK even if head == null.
		head = new LinkedNode(head, o);
		size++;
	}
  
	public T pop() {
		LinkedNode pN = head;
		head = head.getNext();
		size--;
		return (T)pN.getElement();
	}
  
	public T top() {
		return (T)head;
	}
  
	public String toString() {
		String str = "@@@@@@@@ - Stack - @@@@@@@@\n";
		LinkedNode curr = head;
		while(curr != null)
		str += curr.getElement().toString();
		return str + "@@@@@@@@@@@@@@@@@@@@@@@@@@@\n";
	}
}
