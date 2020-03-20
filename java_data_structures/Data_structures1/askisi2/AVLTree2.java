/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trees;

/**
 *
 * @author anon
 */
public class AVLTree2 <K extends Comparable, E> extends BinarySearchTree<K, E>{
    long no_reconstructions = 0;
    long no_l_rots = 0;
    long no_r_rots = 0;
    long no_dl_rots = 0;
    long no_dr_rots = 0;
    
    public AVLTree2() {
        super();
    }

    public AVLTree2(AVLNode<K, E> o) {
        super((BSTNode<K, E>) o);
    }

    public AVLTree2(K k, E e) {
        AVLNode<K, E> node = new AVLNode(k, e, null, null, null);
        super.setRoot(node);
        super.setSize(1);
    }

    private int getRightSonHeight(BSTNode<K, E> v) {
        if (v == null) {
            System.out.println("Empty node");
            return -1;
        }
        return (v.getRight() != null ? ((AVLNode<K, E>) v.getRight()).getHeight() : 0);
    }

    private int getLeftSonHeight(BSTNode<K, E> v) {
        if (v == null) {
            System.out.println("Empty node");
            return -1;
        }
        return (v.getLeft() != null ? ((AVLNode<K, E>) v.getLeft()).getHeight() : 0);
    }

    private void remedyHeight(BSTNode<K, E> v) {
        if (v == null) {
            System.out.println("Unable to remedy node height!");
            return;
        }
        ((AVLNode<K, E>) v).setHeight(1 + java.lang.Math.max(getRightSonHeight(v), getLeftSonHeight(v)));
    }

    private boolean isBalanced(BSTNode<K, E> v) {
        if (v == null) {
            System.err.println("[isBalanced] Empty node!");
            return false;
        }
        int balance = getLeftSonHeight(v) - getRightSonHeight(v);
        return (balance >= -2 && balance <= 2);
    }

    private BSTNode<K, E> rebalanceSon(BSTNode<K, E> v) {
        if (v == null) {
            System.err.println("[rebalanceSon] Empty node!");
            return null;
        }
        if (getLeftSonHeight(v) > getRightSonHeight(v)) {
            return v.getLeft();
        } else if (getLeftSonHeight(v) < getRightSonHeight(v)) {
            return v.getRight();
        } else if (isLeft(v)) {
            return v.getLeft();
        } else {
            return v.getRight();
        }
    }

    private void rebalance(BSTNode<K, E> v) {
        if (v == null) {
            System.err.println("[rebalance] Empty node!");
            return;
        }
        BSTNode<K, E> u, w;
        while (v != null) {
            remedyHeight(v);
            if (!isBalanced(v)) {
                w = rebalanceSon(v);
                u = rebalanceSon(w);
                v = reconstruct(v, w, u);
                remedyHeight(v.getLeft());
                remedyHeight(v.getRight());
                remedyHeight(v);
            }
            v = v.getParent();
        }
    }

    private BSTNode<K, E> reconstruct(BSTNode<K, E> v, BSTNode<K, E> w, BSTNode<K, E> u) {
        // simple rotation right
        no_reconstructions++;
        if (isLeft(w) && isLeft(u)) {
            if (!isRoot(v)) {
                if (isLeft(v)) {
                    v.getParent().setLeft(w);
                } else {
                    v.getParent().setRight(w);
                }
                w.setParent(v.getParent());
            } else {
                setRoot(w);
                w.setParent(null);
            }
            v.setLeft(w.getRight());
            if (w.getRight() != null) {
                w.getRight().setParent(v);
            }
            w.setRight(v);
            v.setParent(w);
            no_l_rots++;
            return w;
        } // simple rotation left
        else if (isRight(w) && isRight(u)) {
            if (!isRoot(v)) {
                if (isRight(v)) {
                    v.getParent().setRight(w);
                } else {
                    v.getParent().setLeft(w);
                }
                w.setParent(v.getParent());
            } else {
                setRoot(w);
                w.setParent(null);
            }
            v.setRight(w.getLeft());
            if (w.getLeft() != null) {
                w.getLeft().setParent(v);
            }
            w.setLeft(v);
            v.setParent(w);
            no_l_rots++;
            return w;
        } // double rotation left
        // u - isLeft
        // w - isRight
        else if (isLeft(u)) {
            v.setRight(u.getLeft());
            if (u.getLeft() != null) {
                u.getLeft().setParent(v);
            }
            w.setLeft(u.getRight());
            if (u.getRight() != null) {
                u.getRight().setParent(w);
            }
            if (!isRoot(v)) {
                if (isRight(v)) {
                    v.getParent().setRight(u);
                } else {
                    v.getParent().setLeft(u);
                }
                u.setParent(v.getParent());
            } else {
                setRoot(u);
                u.setParent(null);
            }
            v.setParent(u);
            w.setParent(u);
            u.setLeft(v);
            u.setRight(w);
            no_dl_rots++;
            return u;
        } // double rotation right
        // u - isRight
        // w - isLeft
        else {
            v.setLeft(u.getRight());
            if (u.getRight() != null) {
                u.getRight().setParent(v);
            }
            w.setRight(u.getLeft());
            if (u.getLeft() != null) {
                u.getLeft().setParent(w);
            }
            if (!isRoot(v)) {
                if (isLeft(v)) {
                    v.getParent().setLeft(u);
                } else {
                    v.getParent().setRight(u);
                }
                u.setParent(v.getParent());
            } else {
                setRoot(u);
                u.setParent(null);
            }
            v.setParent(u);
            w.setParent(u);
            u.setLeft(w);
            u.setRight(v);
            no_dr_rots++;
            return u;
        }
    }

    public E findInfo(K key) {
        return super.findInfo(key, root());
    }

    public BSTNode<K, E> insertItem(K key, E e) {
        AVLNode<K, E> newNode = new AVLNode(key, e, null, null, null);
        BSTNode<K, E> insNode = super.insertItem(newNode);
        if (insNode == null) {
            return null;
        }
        rebalance(insNode);
        return insNode;
    }

    public BSTNode<K, E> deleteItem(K key) {
        BSTNode<K, E> delNode = super.deleteItem(key);
        if (delNode == null) {
            return null;
        }
        rebalance(delNode);
        return delNode;
    }
    
    public long getComparisons(){
        return super.no_comparisons;
    }

    public long getReconstructions(){
        return no_reconstructions;
    }
    
    public long getLeftRotations(){
        return no_l_rots;
    }
    
    public long getRightRotations(){
        return no_r_rots;
    }
    
    public long getDoubleRightRotations(){
        return no_dr_rots;
    }

    public long getDoubleLeftRotations(){
        return no_dl_rots;
    }
    
    public void resetCounters(){
        no_comparisons = 0;
        no_l_rots = 0;
        no_reconstructions = 0;
        no_r_rots = 0;
        no_dl_rots = 0;
        no_dr_rots = 0;
    }
    public String countersToStr () {
    String str = "";
    str += "Number of comparisons: "+ getComparisons() +"\n";
    str += "Number of reconstructions: "+ getReconstructions() +"\n";
    str += "Number of simple left rotations : "+getLeftRotations() +"\n";
    str += "Number of simple right rotations : "+getRightRotations() +"\n";
    str += "Number of double left rotations : "+getDoubleLeftRotations() +"\n";
    str += "Number of double right rotations : "+getDoubleRightRotations() +"\n";
    if( getReconstructions() != ( getLeftRotations() + getRightRotations() + getDoubleRightRotations() + getDoubleLeftRotations() ) ) {
      System.out.println("Wrong counting process!");
      System.exit(1);
    }
    return str;
  }
    public String dotString() {
        String str = "digraph AVLTree {\n" + dotString(root()) + "}\n";
        return str;
    }

    public String dotString(String title) {
        return dotString("AVLTree", title);
    }

}
