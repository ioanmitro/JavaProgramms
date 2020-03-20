/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FIFO;
import FIFO.SinglyNodeList;
/**
 *
 * @author Happ
 */
public class FiFo <T>{
    
    public SinglyNodeList<T> list;
    
    
    public FiFo(){
        list = new SinglyNodeList<T>();
    }
    
    public int size(){
        return list.size();
    }
    
    public void enqueue(T element){
        list.insertFirst(element);
    }
    
    public T dequeue(){
        return list.deleteLast();
    }
    
    public T first(){
        return list.first().getElement();
    }
    
    public String toString(){
        return list.toString();
    }
    
}
