package tries;

public class ENDictTrieNode {

    public static final int LETTERS = 26;
    private ENDictTrieNode parent, child[];
    private boolean elementEnd; // indicates whether the node is a terminal node or not.

    private int depth;          // the depth of the node starting from root. 
    // Root has depth 0,children of root have depth 1 etc.

    public ENDictTrieNode() {
        parent = null;
        child = new ENDictTrieNode[LETTERS];
        elementEnd = false;
        depth = 0;
    }

    public ENDictTrieNode(ENDictTrieNode newParent, boolean ends, int newDepth) {
        parent = newParent;
        child = new ENDictTrieNode[LETTERS];
        for (int i = 0; i < LETTERS; i++) {
            child[i] = null;
        }
        elementEnd = ends;
        depth = newDepth;
    }

    public ENDictTrieNode getChild(int i) {
        if (i >= 0 && i < LETTERS) {
            return child[i];
        }
        return null;
    }

    public boolean setChild(int i, ENDictTrieNode newChild) {
        if (i >= 0 && i < LETTERS) {
            child[i] = newChild;
            return true;
        }
        return false;
    }

    public ENDictTrieNode getParent() {
        return parent;
    }

    public void setParent(ENDictTrieNode newParent) {
        parent = newParent;
    }

    public boolean elementEnds() {
        return elementEnd;
    }

    public void setElementEnds(boolean end) {
        elementEnd = end;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int newDepth) {
        depth = newDepth;
    }

    /**
     * Returns the child number the node represents for its parent. If node is
     * Root no parent exists. Function returns -1.
     */
    public int isChildNumber() {
        for (int i = 0; i < LETTERS; i++) {
            if (getParent() != null && getParent().getChild(i) == this) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the String that the node represents
     */
    public String getValue() {
        ENDictTrieNode node = this;
        StringBuffer str = new StringBuffer();
        while (node != null && node.getParent() != null) {
            int num = node.isChildNumber();
            if (num >= 0) {
                str.insert(0, (char) ('a' + num));
            }
            node = node.getParent();
        }
        if (str.length() == 0) {
            return "ROOT";
        }
        return str.toString();
    }

    public String dotString() {
        String str = "";
        if (elementEnds()) {
            str += getValue() + " [shape=circle, color=red]\n";
        } else {
            str += getValue() + " [shape=circle, color=black]\n";
        }

        for (int i = 0; i < LETTERS; i++) {
            if (child[i] != null) {
                str += getValue() + " -> " + child[i].getValue() + " [label=\" " + (new StringBuffer().append((char) ('a' + i)).toString()) + " \", dir=none]\n";
            }
        }

        for (int i = 0; i < LETTERS; i++) {
            if (child[i] != null) {
                str += child[i].dotString();
            }
        }

        return str;
    }
}
