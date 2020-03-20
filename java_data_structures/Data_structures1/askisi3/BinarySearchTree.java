package trees;

import ce210.Item.Item;

/* Uses BSTNode<T>
 */
public class BinarySearchTree<K extends Comparable, E> {

    protected BSTNode<K, E> lastInsertedNode, lastDeletedNode;
    ;
    private BSTNode<K, E> Root;
    private int size;
    private boolean treeIntegrity = true;
    long no_comparisons = 0;

    public enum BSTNodeChildType {
        LEFT, RIGHT
    };

    public BinarySearchTree() {
        Root = null;
        lastDeletedNode = null;
        size = 0;
    }

    public BinarySearchTree(BSTNode<K, E> o) {
        Root = o;
        lastDeletedNode = null;
        size = 1;
    }

    public BinarySearchTree(K k, E e) {
        Root = new BSTNode<K, E>(k, e, null, null, null);
        lastDeletedNode = null;
        size = 1;
    }

    public BinarySearchTree(Item<K, E> i) {
        Root = new BSTNode<K, E>(i, null, null, null);
        lastDeletedNode = null;
        size = 1;
    }

    public void checkTreeIntegrity() {
        if (root().checkNodeIntegrity()); 
        else {
            treeIntegrity = false;
            System.out.println("Tree Integrity FAILED!");
        }
    }

    public int size() {
        return size;
    }

    public void setSize(int s) {
        size = s;
    }

    public BSTNode<K, E> root() {
        return Root;
    }

    public void setRoot(BSTNode<K, E> newRoot) {
        Root = newRoot;
    }

    public boolean isRoot(BSTNode<K, E> v) {
        return (v == Root);
    }

    public boolean isLeft(BSTNode<K, E> v) {
        if (v == Root) {
            //System.out.println("You are at root element.");
            return false;
        }
        return (v == v.getParent().getLeft());
    }

    public boolean isRight(BSTNode<K, E> v) {
        if (v == Root) {
            //System.out.println("You are at root element.");
            return false;
        }
        return (v == v.getParent().getRight());
    }

    public void exchangeElements(BSTNode<K, E> v, BSTNode<K, E> w) {
        K tempKey = v.getKey();
        E tempElement = v.getElement();
        v.setKey(w.getKey());
        v.setElement(w.getElement());
        w.setKey(tempKey);
        w.setElement(tempElement);
    }

    public BSTNode<K, E> getSibling(BSTNode<K, E> v) {
        if ((v == null) || isRoot(v)) {
            System.out.println("No sibling.");
            return null;
        }
        if (isRight(v)) {
            return v.getParent().getLeft();
        } else {
            return v.getParent().getRight();
        }
    }

    public void addLeaf(BSTNode<K, E> v, BSTNode<K, E> newNode, BSTNodeChildType type) {
        lastDeletedNode = null;
        if (type == BSTNodeChildType.LEFT) {
            if (v.getLeft() != null) {
                System.out.println("This kind of child exists!");
                return;
            }
            v.setLeft(newNode);
            newNode.setParent(v);
            lastInsertedNode = newNode;
            size++;
        } else {
            if (v.getRight() != null) {
                System.out.println("This kind of child exists!");
                return;
            }
            v.setRight(newNode);
            newNode.setParent(v);
            lastInsertedNode = newNode;
            size++;
        }
    }

    public E deleteNode(BSTNode<K, E> v) {
        lastInsertedNode = null;
        if (v.getLeft() != null && v.getRight() != null) {
            System.out.println("Unable to delete node");
            return null;
        }
        lastDeletedNode = v;
        if (isRoot(v)) {
            BSTNode<K, E> notNullSonofv = (v.getLeft() != null ? v.getLeft() : v.getRight());
            Root = notNullSonofv;
            if (Root != null) {
                Root.setParent(null);
            }
        } else {
            BSTNode<K, E> parentOfv = v.getParent();
            BSTNode<K, E> notNullSonofv = (v.getLeft() != null ? v.getLeft() : v.getRight());
            if (isLeft(v)) {
                parentOfv.setLeft(notNullSonofv);
            } else {
                parentOfv.setRight(notNullSonofv);
            }
            if (notNullSonofv != null) {
                notNullSonofv.setParent(parentOfv);
            }
        }
        size--;
        v.setLeft(null);
        v.setRight(null);
        v.setParent(null);
        return v.getElement();
    }

    public String toString() {
        return Root.toString();
    }

    /**
     * Returns the node that either contains the specified key or the node that
     * we should add a new node with the specified key below it.
     */
    public BSTNode<K, E> findNode(K key, BSTNode<K, E> v) {
        K nodeKey = v.getKey();
        int compareResult = key.compareTo(nodeKey);
        no_comparisons++;
        if (compareResult < 0) {
            if (v.getLeft() == null) {
                return v;
            } else {
                return findNode(key, v.getLeft());
            }
        } else if (compareResult > 0) {
            if (v.getRight() == null) {
                return v;
            } else {
                return findNode(key, v.getRight());
            }
        } else {
            return v;
        }
    }

    public E findInfo(K key, BSTNode<K, E> v) {
        BSTNode<K, E> node = findNode(key, v);
        no_comparisons++;
        if (node.getKey().compareTo(key) == 0) {
            return node.getElement();
        }
        return null;
    }

    /**
     * Returns the inserted node.
     */
    public BSTNode<K, E> insertItem(K key, E e) {
        Item<K, E> item = new Item<>(key, e);
        return insertItem(item);
    }

    /**
     * Returns the inserted node.
     */
    public BSTNode<K, E> insertItem(Item<K, E> item) {
        BSTNode<K, E> nullPointerNode = new BSTNode<>(item, null, null, null);
        return insertItem(nullPointerNode);
    }

    /**
     * Returns the inserted node.
     */
    public BSTNode<K, E> insertItem(BSTNode<K, E> newNullPointerNode) {
        lastDeletedNode = null;
        if (size() == 0) {
            setRoot(newNullPointerNode);
            setSize(1);
            return root();
        }

        BSTNode<K, E> insNode = findNode(newNullPointerNode.getKey(), root());
        K keyOfinsNode = insNode.getKey();
        int compareResult = newNullPointerNode.getKey().compareTo(keyOfinsNode);
        no_comparisons++;
        /* Key already exists */
        if (compareResult == 0) {
            return null;
        }
        if (compareResult < 0) {
            addLeaf(insNode, newNullPointerNode, BSTNodeChildType.LEFT);
            return insNode.getLeft();
        } else {
            addLeaf(insNode, newNullPointerNode, BSTNodeChildType.RIGHT);
            return insNode.getRight();
        }
    }

    /**
     * Deletes the tree node with the specified key, if such a node exists.
     * Returns the neighbouring node affected by the deletion.
     */
    public BSTNode<K, E> deleteItem(K key) {
        lastInsertedNode = null;
        if (size() == 0) {
            return null;
        }

        BSTNode<K, E> delNode = findNode(key, root());
        K keyOfdelNode = delNode.getKey();
        int compareResult = key.compareTo(keyOfdelNode);
        no_comparisons++;
        /* Item was not found */
        if (compareResult != 0) {
            return null;
        }
        BSTNode<K, E> returnNode = null;
        if (delNode.getLeft() == null || delNode.getRight() == null) {
            if (delNode.getLeft() != null) {
                returnNode = delNode.getLeft();
            } else if (delNode.getRight() != null) {
                returnNode = delNode.getRight();
            } else {
                returnNode = delNode.getParent();
            }
            deleteNode(delNode);
            return returnNode;
        } else {
            BSTNode<K, E> cursor = delNode.getRight();
            BSTNode<K, E> tmp, parentDelNode;
            while ((tmp = cursor.getLeft()) != null) {
                cursor = tmp;
            }
            exchangeElements(cursor, delNode);
            parentDelNode = cursor.getParent();
            deleteNode(cursor);
            return parentDelNode;
        }
    }

    public String dotString(BSTNode<K, E> node) {
        String str = node.getKey() + " [shape=circle, color=";
        String color = "black";
        if (lastInsertedNode == node) {
            color = "navy";
        }
        if (lastDeletedNode == node) {
            color = "red";
        }
        str += color + ", fontcolor=\"" + color + "\"]\n";
        if (node.getLeft() != null) {
            str += node.getKey() + " -> " + node.getLeft().getKey() + "\n";
            str += dotString(node.getLeft());
        }
        if (node.getRight() != null) {
            str += node.getKey() + " -> " + node.getRight().getKey() + "\n";
            str += dotString(node.getRight());
        }
        return str;
    }

    public String dotString(String title) {
        return dotString("\"Binary Search Tree\"", title);
    }

    public String dotString(String type, String title) {
        String str = "digraph " + type + " {\n";
        str += "fontcolor=\"navy\";\nfontsize=20;\nlabelloc=\"t\";\nlabel=\"" + title + "\"\n";
        str += dotString(root());
        if (lastDeletedNode != null) {
            str += dotString(lastDeletedNode);
        }
        str += "}\n";
        return str;
    }

}
