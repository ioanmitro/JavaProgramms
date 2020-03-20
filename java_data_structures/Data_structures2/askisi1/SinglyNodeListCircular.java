/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SinglyNodeList;

import ce210lists.SNode;

/**
 *
 * @author Happ
 */

public class SinglyNodeListCircular<T> {
    protected int no_elements;
    protected SNode<T>  head, tail;
    
    public SinglyNodeListCircular(){
        no_elements = 0;
        head = null;
        tail = null;
    }
    
    public int size(){
        return no_elements;
    }
    

    
    public boolean isEmpty(){
        if(no_elements < 1){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean isFirst(SNode<T> test_node){
        return test_node == head;
    }
    
    public SNode<T> first(){
        if(isEmpty()){
            System.out.println("Empty List");
        }
        return head;
    }
    
    public SNode<T> last(){
        if(isEmpty()){
            System.out.println("Empty List");
            
        }
        return tail;
    }
    
    public SNode <T> insertAfter(SNode<T> p, T element){
        if(isEmpty()){
            System.out.println("Empty List");
            
            return null;
        }
        
        SNode<T> node_to_be_added = new SNode<T>(p.getNext(), element);
        p.setNext(node_to_be_added);
        if(p == tail){
            tail = node_to_be_added;
        }
        no_elements++;
        return node_to_be_added;
    }
    
    public SNode<T> insertFirst(T element){
        SNode <T> node_to_be_added = new SNode<T>(head, element);
        no_elements++;
        head = node_to_be_added;
        if(no_elements == 1){
            head.setNext(head);
            tail = head;
        }
        return node_to_be_added;
    }
    
    public SNode<T> insertLast(T element){
        SNode <T> node_to_be_added = new SNode<T>(head, element);
        no_elements++;
        tail.setNext(node_to_be_added);
        tail = node_to_be_added;
        if(no_elements ==1 ){
            head = tail;
        }
        return node_to_be_added;
    }
    
    public T deleteAfter(SNode<T> delete_node){
        if(isEmpty()){
            System.out.println("Can't Delete from empty list");
            return null;
        }
        if(delete_node == tail){
            deleteFirst();
        }
        no_elements--;
        SNode<T> delete_next = delete_node.getNext();
        delete_node.setNext(delete_next.getNext());
        delete_next.setNext(null);
        return delete_next.getElement();
    }
    
    public T deleteFirst(){
        SNode<T> temp = head;
        if(isEmpty()){
            System.out.println("Cant delete from empty list");
            return null;
        }
        
        no_elements--;
        if(no_elements == 1){
            head = null;
            return head.getElement();
        }
        
        tail.setNext(head.getNext());
        head.setNext(null);
        head = tail.getNext();
         
        return temp.getElement();
    }
    public void setEmpty(){
        no_elements = 0;
        head = null;
        tail = null;
        
    }
    public T deleteLast(){
        if(isEmpty()){
            System.out.println("List is empty. Cant delete");
            return null;
        }
        
        if(no_elements == 1){
            SNode<T> temp = head;
            head = null;
            return temp.getElement();
        }
        no_elements--;
        SNode<T> temp = head;
        SNode<T> old_tail = tail;
        while(true){
            if(temp.getNext() == tail){
                break;
            }
            temp = temp.getNext();
        }
        temp.setNext(head);
        tail.setNext(null);
        tail = temp;
        return old_tail.getElement();
    }
    
    public void catenate(SinglyNodeListCircular list_to_add){
        if(list_to_add.isEmpty()){
            return;
        }
        if(tail == null){
            tail = list_to_add.first();
            head = tail;
        }else{
            tail.setNext(list_to_add.first());
            list_to_add.last().setNext(head);
        }
        tail = list_to_add.last();
        no_elements = no_elements + list_to_add.size();
        list_to_add.setEmpty();    
    }
    
    public String toString(){
        String str = "";
        if(isEmpty()){
            str = "List empty.\n";
            return str;
        }
        SNode<T> runner = head;
        while(true){
            str = str + runner.getElement() + '\n';
            if(runner == tail){
                break;
            }
            runner = runner.getNext();
        }
        return str;
    }
    
    public void printList(){
            System.out.println(toString());
        
    }
}
