/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoublyNodeListDequeue;
/**
 *
 * @author Happ
 */
public class DoublyNodeListDequeue <T>{
    
    public DoublyNodeListCircular<T> list;
    
    public DoublyNodeListDequeue(){
        list = new DoublyNodeListCircular<T>();
    }
    
    public int size(){
        return list.size();
    }
    
    public void push(T element){
        list.insertFirst(element);
    }
    
    public T pop(){
        return list.delete(0);
    }
    
    public T eject(){
        return list.delete(list.last());
    }
    
    public void inject(T element){
        list.insertLast(element);
    }
    
    public T first(){
        return list.first().getElement();
    }
    
    public T last(){
        return list.last().getElement();
    }
    
    public String toString(){
        return list.toString();
    }
    
    
}
