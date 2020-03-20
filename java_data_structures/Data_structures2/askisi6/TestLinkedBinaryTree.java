package ce210;
import TreeTraversals.*;
import java.util.*;
import java.io.*;

class TestLinkedBinaryTree {
  public static void main(String args[]) {
    
    SampleStringData data = new SampleStringData();
    String tmp = null;
    
    Random rand = new Random(1512);
    LinkedBinaryTree<String> tree = new LinkedBinaryTree<>( data.getNext() );
    int i=0; 
    while( (tmp = data.getNext()) != null && i++<15 ) {
      tree.insertRandom( tmp, rand );
    }
    
    /* comment out the following lines if you have
     * not installed Graphviz http://graphviz.org/
     */
 //   try {
    
   //   PrintWriter file = new PrintWriter("LinkedBinaryTree.dot"); //, "UTF-8");
     // file.println(tree.dotString());
  //    file.close();
      
      //Process p = Runtime.getRuntime().exec("dot -Tps LinkedBinaryTree.dot -o LinkedBinaryTree.ps");
    //  Process p = Runtime.getRuntime().exec("dot -Tpng LinkedBinaryTree.dot -o LinkedBinaryTree.png");
    //  p.waitFor();
     
  //  } catch(Exception ex) {
   //   ex.printStackTrace();
    //}
    /* comment out ends here */
    
    System.out.println("Printing Tree Pre-Order\n");
    System.out.println(tree.toStringPreOrder());
    
    System.out.println("Printing Tree Post-Order\n");
    System.out.println(tree.toStringPostOrder());
    
    System.out.println("Printing Tree In-Order\n");
    System.out.println(tree.toStringInOrder());
    
  }
}