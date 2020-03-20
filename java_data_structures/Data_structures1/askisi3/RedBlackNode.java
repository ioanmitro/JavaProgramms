package trees;

import ce210.Item.Item;

public class RedBlackNode<K extends Comparable,E> extends BSTNode<K,E> {

  private RedBlackColor color;
  private int leftBlackDepth, rightBlackDepth;
  private int level;
  
  public RedBlackNode(K k, E e, RedBlackNode<K,E> p, RedBlackNode<K,E> l, RedBlackNode<K,E> r) {
    super(k,e,p,l,r);
    color = RedBlackColor.RED;
  }
  
  public RedBlackNode(Item<K,E> item, RedBlackNode<K,E> p, RedBlackNode<K,E> l, RedBlackNode<K,E> r) {
    super(item, p, l, r);
    color = RedBlackColor.RED;
  }
  
  public RedBlackNode(RedBlackNode<K,E> p, RedBlackNode<K,E> l, RedBlackNode<K,E> r) {
    this( null, null, p, l, r );
    color = RedBlackColor.RED;
  }
  
  public boolean checkNodeIntegrity() {
    if( !super.checkNodeIntegrity() ) 
      return false;
    getLevel();
    if( getBlackDepth() < 0 ) {
      System.err.println("Node: "+this+" failed on black depth!");
      return false;
    }
    if( twoConsequtiveReds() ) {
      System.err.println("Node: "+this+" failed on consequtive reds!");
      return false;
    }    
    return true;    
  }
  
  public int getLevel() {
    if( getLeft() == null && getRight() == null ) level = 0;
    if( getLeft() != null ) { level = ((RedBlackNode)getLeft()).getLevel() + 1; }
    int rightLevel = 0;
    if( getRight() != null )
      rightLevel = ((RedBlackNode)getRight()).getLevel() + 1;
    if( rightLevel > level )
      level = rightLevel;
    return level;
  }
  
  public int getBlackDepth() {    
    if( getLeft() == null )
      leftBlackDepth = (getColor() == RedBlackColor.BLACK ? 1 : 0);
    else {
      leftBlackDepth = ((RedBlackNode)getLeft()).getBlackDepth();
      if( leftBlackDepth < 0 ) 
        return leftBlackDepth;
      leftBlackDepth += (getColor() == RedBlackColor.BLACK ? 1 : 0);
    }
    
    if( getRight() == null )
      rightBlackDepth = (getColor() == RedBlackColor.BLACK ? 1 : 0);
    else {
      rightBlackDepth = ((RedBlackNode)getRight()).getBlackDepth();
      if( rightBlackDepth < 0 ) 
        return rightBlackDepth;
      rightBlackDepth += (getColor() == RedBlackColor.BLACK ? 1 : 0);
    }
    if( rightBlackDepth != leftBlackDepth ) {
      System.err.println("[getBlackDepth - ERROR]: "+this);
      return -1;
    }
    else
      return leftBlackDepth;
  }
  
  public boolean twoConsequtiveReds() {
    boolean twoConsequtiveRedNodes=false, leftN=false, rightN = false;
    if( getLeft()!= null && ((RedBlackNode)getLeft()).twoConsequtiveReds() ) 
        return true;
    if( getRight()!= null && ((RedBlackNode)getRight()).twoConsequtiveReds() ) 
        return true;
    if( color == RedBlackColor.RED ) {
      if( getLeft()!= null && ((RedBlackNode)getLeft()).getColor() == RedBlackColor.RED ) 
        twoConsequtiveRedNodes = leftN = true;
      if( getRight()!= null && ((RedBlackNode)getRight()).getColor() == RedBlackColor.RED ) 
        twoConsequtiveRedNodes = rightN = true;
    }
    if( twoConsequtiveRedNodes ) {
      System.err.print("twoConsequtiveRedNodes on node: "+this+" and ");
      if( leftN )
        System.err.println(" "+getLeft() );
      if( rightN )
        System.err.println(" "+getRight() );
    }
    return twoConsequtiveRedNodes;
  }
  
  public RedBlackColor getColor() {
    return color;
  }
  
  public void setColor(RedBlackColor c) {
    color = c;
  }
  
  public String toString() {
    String str = super.toString();
    str += this.color == RedBlackColor.RED ? " - COLOR: RED - " : " - COLOR: BLACK - ";
    str += "DEPTH: "+level+" | ";
    return str;
  }
  
  public String dotString() {
    String str = getKey() + " [shape=circle, color=black]\n";
    if(getLeft()!=null)
      str += getKey() +" -> "+ getLeft().getKey();
    if(getRight()!=null)
      str += getKey() +" -> "+ getRight().getKey();
    str += getLeft().dotString();
    str += getRight().dotString();
    return str;
  }
}
