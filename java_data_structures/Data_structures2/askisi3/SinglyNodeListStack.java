/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SinglyNodeListStack;

/**
 *
 * @author Happ
 */
public class SinglyNodeListStack <T>{
    public SinglyNodeList<T> list;
    
    public SinglyNodeListStack(){
        list = new SinglyNodeList<T>();
    }
    
    public int size(){
        return list.size();
    }
    
    public void push(T element){
        list.insertFirst(element);
    }
    
    public T pop(){
        return list.deleteFirst();
    }
    
    public String toString(){
        return list.toString();
    }
    
    public T top(){
        return list.first().getElement();
    }
}
