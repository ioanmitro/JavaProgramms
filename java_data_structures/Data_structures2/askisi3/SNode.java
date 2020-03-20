package ce210lists;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Happ
 */
public class SNode <T>{
    private SNode<T> next;
    private T element_in_node;
    
    //O constructor
    public SNode(SNode<T> nodeNext, T new_element_in_node){
        next = nodeNext;
        element_in_node = new_element_in_node;
    }
    
    public T getElement(){
        return element_in_node;
    }
    
    public SNode<T> getNext(){
        return next;         
    }
    
    public void setElement(T new_element){
        element_in_node = new_element;
    } 
    public void reset() {
        next = null;
    }
    public void setNext(SNode<T> new_next){
        next = new_next;
    }
    
    public void purge() {
        reset();
        element_in_node = null;
    }
}
