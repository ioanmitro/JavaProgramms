package trees;

import ce210.Item.Item;
import java.io.*;

public class RedBlackTree<K extends Comparable, E> extends BinarySearchTree<K, E> {

    private boolean rbTreeIntegrity = true;
    long recoulorings = 0;
    long reconstructions = 0;
    long no_l_rots = 0;
    long no_r_rots = 0;
    long no_dr_rots = 0;
    long no_dl_rots = 0;

    public boolean getRBTreeIntegrity() {
        return rbTreeIntegrity;
    }

    public void checkTreeIntegrity() {
        if (((RedBlackNode) root()).checkNodeIntegrity())
      ; // Tree integrity is fine.
        else {
            rbTreeIntegrity = false;
            System.out.println("RBTree Integrity FAILED!");
        }
    }

    public RedBlackTree() {
        super();
    }

    public RedBlackTree(RedBlackNode<K, E> o) {
        super((BSTNode<K, E>) o);
        o.setColor(RedBlackColor.BLACK);
    }

    public RedBlackTree(K k, E e) {
        RedBlackNode<K, E> node = new RedBlackNode(k, e, null, null, null);
        super.setRoot(node);
        super.setSize(1);
    }

    public void exchangeElements(BSTNode<K, E> v, BSTNode<K, E> w) {
        super.exchangeElements(v, w);
    }

    private boolean setBlack(BSTNode<K, E> v) {
        if (v == null) {
            System.err.println("Attemping to set black a null node.");
            return false;
        }
        ((RedBlackNode) v).setColor(RedBlackColor.BLACK);
        return true;
    }

    private boolean setRed(BSTNode<K, E> v) {
        if (v == null) {
            System.err.println("Attemping to set red a null node.");
            return false;
        }
        ((RedBlackNode<K, E>) v).setColor(RedBlackColor.RED);
        return true;
    }

    private boolean isBlack(BSTNode<K, E> v) {
        if (v == null) {
            return true;
        }
        return (((RedBlackNode<K, E>) v).getColor() == RedBlackColor.BLACK);
    }

    private boolean isRed(BSTNode<K, E> v) {
        if (v == null) {
            return false;
        }
        return (((RedBlackNode<K, E>) v).getColor() == RedBlackColor.RED);
    }

    private RedBlackColor getColor(BSTNode<K, E> v) {
        if (v == null) {
            return RedBlackColor.BLACK;
        }
        return ((RedBlackNode<K, E>) v).getColor();
    }

    private boolean hasOneRedSon(BSTNode<K, E> v) {
        if (v == null) {
          //  System.err.println("Node is NULL and has no children!");
            return false;
        }
        return isRed(v.getLeft()) || isRed(v.getRight());
    }

    private BSTNode<K, E> getRedSon(BSTNode<K, E> v) {
        if (v == null) {
          //  System.out.println("Node is NULL and has no children!");
            return null;
        }
        if (isRed(v.getLeft())) {
            return v.getLeft();
        }
        if (isRed(v.getRight())) {
            return v.getRight();
        }
        return null;
    }

    private void insertionRebalance(BSTNode<K, E> v) {
        if (v == null) {
            System.err.println("Node is empty and cannot be rebalanced!");
            return;
        }

        try {
            int j = 0;

            BSTNode<K, E> u = null, w = v;
            v = v.getParent();
            while (isRed(v) && isRed(w)) {
                // sibling of node v is black
                if (isBlack(getSibling(v))) {
                    //rotation simple or double
                    u = w;
                    w = v;
                    v = v.getParent();
                    v = reconstruct(v, w, u);
                    setRed(v.getLeft());
                    setRed(v.getRight());
                    recoulorings = recoulorings + 2;
                    setBlack(v);
                } // sibling of node v is red
                else {
                    recoulorings++;
                    setBlack(v);
                    recoulorings++;
                    setBlack(getSibling(v));
                    v = v.getParent();
                    if (isRoot(v)) {
                        break;
                    }
                    setRed(v);
                    recoulorings++;
                    u = w;
                    w = v;
                    v = v.getParent();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private int countBlackDepth(BSTNode<K, E> node) {
        int depth = 0, depthLeft, depthRight;
        if (node != null) {
            depthLeft = countBlackDepth(node.getLeft());
            depthRight = countBlackDepth(node.getRight());
            if (depthLeft > depthRight) {
                depth = depthLeft;
            } else {
                depth = depthRight;
            }
        }
        if (isBlack(node)) {
            depth++;
        }
        return depth;
    }

    private void deletionRebalance(BSTNode<K, E> y) {
        if (y == null) {
            System.err.println("Node is empty and cannot be rebalanced!");
            return;
        }

        BSTNode<K, E> sibling = countBlackDepth(y.getLeft()) > countBlackDepth(y.getRight()) ? y.getLeft() : y.getRight();
        BSTNode<K, E> childOfSibling;

        while (y != null) {
            if (isBlack(sibling)) {
                RedBlackColor parentColor = getColor(y);
                //case C1
                if (hasOneRedSon(sibling)) {
                    //System.out.println("Case 1");
                    y = reconstruct(y, sibling, getRedSon(sibling));
                    setBlack(y.getLeft());
                    setBlack(y.getRight());
                    recoulorings = recoulorings + 2;
                    if (parentColor == RedBlackColor.BLACK) {
                        recoulorings++;
                        setBlack(y);
                    } else {
                        recoulorings++;
                        setRed(y);
                    }
                    return;
                }

                // case C2a
                
                setBlack(y);
                recoulorings++;
                if (sibling != null) {
                    recoulorings++;
                    setRed(sibling);
                }
                if (parentColor == RedBlackColor.RED) {
                    return;
                } // case C2b  
                else {
                    if (isRoot(y)) {
                        return;
                    }
                    sibling = getSibling(y);
                    y = y.getParent();
                }
            } // case C3, sibling is red
            else {
                //System.out.println("Case 3");
                BSTNode<K, E> newSibling;
                if (isLeft(sibling)) {
                    newSibling = sibling.getRight();
                    childOfSibling = sibling.getLeft();
                } else {
                    newSibling = sibling.getLeft();
                    childOfSibling = sibling.getRight();
                }
                // if( childOfSibling == null ) System.out.println("Shout!");
                // if( getColor(sibling) == RedBlackColor.RED ) System.out.println("--> RED");
                // sibling goes to top. newSibling goes below node y. 
                // We want to know whether newSibling has a red node below it (Case 1) or not (Case 2).
                setBlack(reconstruct(y, sibling, childOfSibling));
                recoulorings++;
                setRed(getSibling(childOfSibling));
                recoulorings++;
                sibling = newSibling;
            }
        }
    }

    public BSTNode<K, E> insertItem(Item<K, E> item) {
        RedBlackNode<K, E> newNode = new RedBlackNode<>(item, null, null, null);
        BSTNode<K, E> insNode = super.insertItem(newNode);
        if (insNode == null) {
            return null;
        }
        if (isRoot(insNode)) {
            recoulorings++;
            setBlack(insNode);
        } else {
            //System.out.println("-- Before Rebalance --");
            //checkTreeIntegrity();
            insertionRebalance(insNode);
            //System.out.println("-- After Rebalance --");
            checkTreeIntegrity();
        }
        return insNode;
    }

    public BSTNode<K, E> insertItem(K key, E e) {
        return insertItem(new Item<K, E>(key, e));
    }

    public BSTNode<K, E> deleteItem(K key) {
        BSTNode<K, E> parentOfDeletedNode = super.deleteItem(key);
        if (parentOfDeletedNode == null) {
            //System.out.println("Node NOT FOUND!");
            return null;
        }
        //int _key = (Integer)key;
        if (isRed(parentOfDeletedNode) && isRed(parentOfDeletedNode.getParent())) {
            recoulorings++;
            setBlack(parentOfDeletedNode);
        } else if (isRed(parentOfDeletedNode) && isRed(parentOfDeletedNode.getLeft())) {
            recoulorings++;
            setBlack(parentOfDeletedNode.getLeft());
        } else if (isRed(parentOfDeletedNode) && isRed(parentOfDeletedNode.getRight())) {
            recoulorings++;
            setBlack(parentOfDeletedNode.getRight());
        } else if (((RedBlackNode) lastDeletedNode).getColor() == RedBlackColor.RED || isRoot(parentOfDeletedNode)) {
        } else {
            deletionRebalance(parentOfDeletedNode);
        }
        return parentOfDeletedNode;
    }

    public E findInfo(K key) {
        return super.findInfo(key, root());
    }

    private BSTNode<K, E> reconstruct(BSTNode<K, E> v, BSTNode<K, E> w, BSTNode<K, E> u) {
        reconstructions++;
        if (isLeft(w) && isLeft(u)) {
            //System.out.println("Check ll case");
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
            no_r_rots++;
            return w;
            //srr
        } else if (isRight(w) && isRight(u)) {
            //System.out.println("Check ll case");
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
            //srl
        } else if (isLeft(u)) {
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
            //drl
        } else {
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
            //drr
        }
    }

    public long getComparisons() {
        return super.no_comparisons;
    }// επιστρέφει τον αριθμό των συγκρίσεων.

    public long getRecolourings() {
        return recoulorings;
    }// επιστρέφει τον αριθμό των αναχρωματισμών.

    public long getReconstructions() {
        return reconstructions;
    }// επιστρέφει τον συνολικό αριθμό των περιστροφών που συμβαίνουν στο δένδρο (απλές ή διπλές περιστροφές μετρούν σαν μία περιστροφή)

    public long getLeftRotations() {
        return no_l_rots;
    }// επιστρέφει τον αριθμό των αριστερών απλών περιστροφών. 

    public long getRightRotations() {
        return no_r_rots;
    }// επιστρέφει τον αριθμό των δεξιών απλών περιστροφών.

    public long getDoubleRightRotations() {
        return no_dr_rots;
    }// επιστρέφει τον αριθμό των δεξιών διπλών περιστροφών.

    public long getDoubleLeftRotations() {
        return no_dl_rots;
    }// επιστρέφει τον αριθμό των αριστερών διπλών περιστροφών.

    public void resetCounters() {
        //TODO
        super.no_comparisons = 0;
        reconstructions = 0;
        no_l_rots = 0;
        no_r_rots = 0;
        no_dr_rots = 0;
        no_dl_rots = 0;
        recoulorings = 0;
    }

    public String dotString() {
        String str = "digraph RedBlackTree {\n" + dotString(root()) + "}\n";
        return str;
    }

    public String dotString(BSTNode<K, E> node) {
        String str = node.getKey() + " [shape=circle, style=filled , fontcolor=white, color=";
        String color = "black";

        if (((RedBlackNode) node).getColor() == RedBlackColor.RED) {
            color = "red";
        }
        str += color + "]\n";
        if (node.getLeft() != null) {
            str += node.getKey() + " -> " + node.getLeft().getKey() + "\n";
            str += dotString(node.getLeft());
        } else {
            int hashCode = str.hashCode();
            str += hashCode + " [shape=box, color=black, width=0.2, height=0.2 fixedsize=true, style=filled, label=\"\"]\n";
            str += node.getKey() + " -> " + hashCode + "\n";
        }
        if (node.getRight() != null) {
            str += node.getKey() + " -> " + node.getRight().getKey() + "\n";
            str += dotString(node.getRight());
        } else {
            int hashCode = str.hashCode();
            str += hashCode + " [shape=box, color=black, width=0.2, height=0.2 fixedsize=true, style=filled, label=\"\"]\n";
            str += node.getKey() + " -> " + hashCode + "\n";
        }
        return str;
    }

    public String countersToStr() {
        String str = "";
        str += "Number of comparisons: " + getComparisons() + "\n";
        str += "Number of recolourings: " + getRecolourings() + "\n";
        str += "Number of reconstructions: " + getReconstructions() + "\n";
        str += "Number of simple left rotations : " + getLeftRotations() + "\n";
        str += "Number of simple right rotations : " + getRightRotations() + "\n";
        str += "Number of double left rotations : " + getDoubleLeftRotations() + "\n";
        str += "Number of double right rotations : " + getDoubleRightRotations() + "\n";
        if (getReconstructions() != (getLeftRotations() + getRightRotations() + getDoubleRightRotations() + getDoubleLeftRotations())) {
            System.out.println("Wrong counting process!");
            System.exit(1);
        }
        return str;
    }

}
