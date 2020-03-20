/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trees;
import java.util.Date;
import ce210.sampleData.*;

/**
 *
 * @author anon
 */
public class BenchmarkRedBlackTree<K extends Comparable, E>extends RedBlackTree  {
        public static void main(String args[]){
        int size = 100000;
        int i = 0;
        int temp =0;
       
        RedBlackTree my_RBTree = new RedBlackTree();
        
        SampleIntegerRandomData data = new SampleIntegerRandomData(3*size, size, new Date().getTime());
        
        for(i = 0; i < size; i++){
            temp = data.getNext();
            my_RBTree.insertItem(temp, temp);
        }
        System.out.println(my_RBTree.countersToStr());
        my_RBTree.resetCounters();
        for(i = 0;i < size / 2 ; i++){
            //System.out.println(i);
            temp = data.getNext();
           // System.out.println(temp);
            my_RBTree.findNode(temp, my_RBTree.root());
        }
        System.out.println(my_RBTree.countersToStr());
        my_RBTree.resetCounters();
        for(i = 0; i < size ; i++){
          //  System.out.println(i);
            my_RBTree.deleteItem((Integer)data.getNext());
        }
        System.out.println(my_RBTree.countersToStr());
        my_RBTree.resetCounters();
    }
}
