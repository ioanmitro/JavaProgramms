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
public class BenchmarkAVLTree2<K extends Comparable, E> extends AVLTree<K, E> {
        public static void main(String args[]){
        int size = 100000;
        int i = 0;
        int temp =0;
       
        AVLTree2 my_AVLTree2 = new AVLTree2();
        
        SampleIntegerRandomData data = new SampleIntegerRandomData(3*size, size, new Date().getTime());
        
        for(i = 0; i < size; i++){
            temp = data.getNext();
            my_AVLTree2.insertItem(temp, temp);
        }
         System.out.println(my_AVLTree2.countersToStr());
        my_AVLTree2.resetCounters();
        for(i = 0;i < size / 2 ; i++){
            //System.out.println(i);
            temp = data.getNext();
           // System.out.println(temp);
            my_AVLTree2.findNode(temp, my_AVLTree2.root());
        }
         System.out.println(my_AVLTree2.countersToStr());
        my_AVLTree2.resetCounters();
        for(i = 0; i < size ; i++){
          //  System.out.println(i);
            my_AVLTree2.deleteItem((Integer)data.getNext());
        }
        System.out.println(my_AVLTree2.countersToStr());
        my_AVLTree2.resetCounters();
    }
}
