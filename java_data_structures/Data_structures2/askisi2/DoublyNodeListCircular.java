package DoublyNodeListDequeue;

import ce210lists.DNode;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Happ
 */
public class DoublyNodeListCircular<T> {
    protected int no_elements;
    protected DNode<T> head, tail;
    
    public void DoublyNodeListCircular(){
        head = null;
        tail = null;
        no_elements = 0;
    }
    
    public boolean isEmpty(){
        return (head == null);
    }
    
    public int size(){
        return no_elements;
    }
    
    public DNode<T> first(){
        if(isEmpty()){
            System.out.println("List is full");
            return null;
        }
        return head;
    }
    
    public DNode<T> last(){
        if(isEmpty()){
            System.out.println("List is full");
            return null;
        }
        return tail;
    }
    
    
    
    
    public void insertFirst(T element){
        DNode<T> node_to_be_added = new DNode<T>(head, tail, element);
        
        if(no_elements == 0){
            node_to_be_added.setNext(node_to_be_added);
            node_to_be_added.setPrev(node_to_be_added);
            head = node_to_be_added;
            tail = node_to_be_added;
            no_elements++;
            return;
        }    
        no_elements++;
        head = node_to_be_added;
        tail.setNext(head);
        return;

    }
    
    public DNode<T> insertAfter(DNode<T> before, T element){
        
        if(no_elements == 0){
            DNode<T> new_node = new DNode<T>(null, null, element);
            head = new_node;
            head.setNext(head);
            head.setPrev(head);
            no_elements++;
            return new_node;
        }
       
        no_elements++;
        DNode<T> new_node = new DNode<T>(before.getNext(), before, element);
        if(before == tail){
            tail = new_node;
        }
        before.getNext().setPrev(new_node);
        before.setNext(new_node);
        

        return new_node;
    }
    
    public DNode<T> insertBefore(DNode<T> after, T element){
        
        if(no_elements == 0){
            DNode<T> new_node = new DNode<T>(null, null, element);
            head = new_node;
            tail = new_node;
            head.setNext(head);
            head.setPrev(head);
            return new_node;
        }
        no_elements++;
      
        DNode<T> new_node = new DNode<T>(after, after.getPrev(), element);
        if(after == head ){
            head = new_node;
        }
        after.getPrev().setNext( new_node);
        after.setPrev(new_node);


        return new_node;
    }
    
    public DNode<T> insertLast(T element){
        
        if(no_elements == 0){
            DNode<T> new_node = new DNode<T>(null, null, element);
            head = new_node;
            tail = new_node;
            head.setNext(head);
            head.setPrev(head);
            no_elements++;
            return new_node;
        }
        no_elements++;
        DNode<T> new_node =new  DNode<T>(head, tail, element);
        tail.setNext(new_node);
        head.setPrev(new_node);
        tail = new_node;
        return new_node;
    }
    
    public void setEmpty(){
        no_elements = 0;
        head = null;
        tail = null;
        
    }
    
    public void printList(){
            System.out.println(toString());
    }
    
    public String toString(){
        String str = "";
        if(isEmpty()){
            str = "List empty.\n";
            return str;
        }
        DNode<T> runner = head;
        while(true){
           // System.out.println(runner.getElement());
            str = str + runner.getElement() + '\n';
            if(runner == tail){
                break;
            }
            //System.out.println(tail.getElement());
            runner = runner.getNext();
        }
        return str;
    }
    
    public T delete(DNode<T> delete_node){
        if(isEmpty()){
            System.out.println("Cannot delete lsit is empty\n");
            return null;
        }
        if(delete_node == head){
            head = delete_node.getNext();
        }
        if(delete_node == tail){
            tail = delete_node.getPrev();
        }
        no_elements--;
        delete_node.getPrev().setNext(delete_node.getNext());
        delete_node.getNext().setPrev(delete_node.getPrev());
        delete_node.setNext(null);
        delete_node.setPrev(null);
        return delete_node.getElement();
    }
    
    public T delete(int pos){
        DNode<T> current = head;
        for(int i = 0; i < pos; i++){
            current = current.getNext();
        }
        return delete(current);
    }
    
    public void catenate(DoublyNodeListCircular<T> new_list){
        if(new_list.isEmpty()){
            return;
        }
        
        if(isEmpty()){
            head = new_list.first();
            tail = new_list.last();
            return;
        }
        new_list.first().setPrev(tail);//joint 1
        tail.setNext(new_list.first());//joint 1 
        head.setPrev(new_list.last());//joint 2
        new_list.last().setNext(head);//joint 2
        tail = new_list.last();
        no_elements += new_list.size();
        new_list.setEmpty();
    }
}
