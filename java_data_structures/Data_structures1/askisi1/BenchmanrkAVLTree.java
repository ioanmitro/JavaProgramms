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
public class BenchmanrkAVLTree<K extends Comparable, E> extends AVLTree<K, E> {
    public static void main(String args[]){
        int size = 100000;
        int i = 0;
        int temp =0;
       
        AVLTree my_AVLTree = new AVLTree();
        
        SampleIntegerRandomData data = new SampleIntegerRandomData(3*size, size, new Date().getTime());
        
        for(i = 0; i < size; i++){
            temp = data.getNext();
            my_AVLTree.insertItem(temp, temp);
        }
        System.out.println(my_AVLTree.countersToStr());
        my_AVLTree.resetCounters();
        for(i = 0;i < size / 2 ; i++){
            //System.out.println(i);
            temp = data.getNext();
           // System.out.println(temp);
            my_AVLTree.findNode(temp, my_AVLTree.root());
        }
        System.out.println(my_AVLTree.countersToStr());
        my_AVLTree.resetCounters();
        for(i = 0; i < size ; i++){
           // System.out.println(i);
            my_AVLTree.deleteItem((Integer)data.getNext());
        }
        System.out.println(my_AVLTree.countersToStr());
        my_AVLTree.resetCounters();
    }
}
