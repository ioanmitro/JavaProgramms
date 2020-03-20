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
public class DNode<T> {
    private DNode<T> next, prev;
    private T element_in_node;
    
    //O constructor
    public DNode(DNode<T> nodeNext,DNode<T> nodePrev, T new_element_in_node){
        next = nodeNext;
        prev = nodePrev;
        element_in_node = new_element_in_node;
    }
    
    public T getElement(){
        return element_in_node;
    }
    
    public DNode<T> getNext(){
        return next;         
    }
    
    public DNode<T> getPrev(){
        return prev;
    }
    
    public void setElement(T new_element){
        element_in_node = new_element;
    } 
    
    public void setNext(DNode<T> new_next){
        next = new_next;
    }
    
    public void setPrev(DNode<T> new_prev){
        prev = new_prev;
    }
}
