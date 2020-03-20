package tries;

public class ENDictTrie {

    private int size = 0;
    private ENDictTrieNode Root;

    public ENDictTrie() {
        setRoot(new ENDictTrieNode(null, false, 0));
    }

    public ENDictTrieNode root() {
        return Root;
    }

    public void setRoot(ENDictTrieNode newRoot) {
        Root = newRoot;
    }

    public int size() {
        return size;
    }

    public void setSize(int newSize) {
        size = newSize;
    }

    public ENDictTrieNode findNode(String word) {
        return findNode(root(), word, 0);
    }

    /**
     * Returns the node that is closer to the search path of word. For example
     * if you search for the word "mayday" but you have already inserted the
     * word may (path 'ROOT' -> 'm' -> 'a' -> 'y'), findNode will return node
     * 'y' from the path above.
     */
    public ENDictTrieNode findNode(ENDictTrieNode v, String word, int depth) {
        if (v == null) {
            return null;
        }
        int wordlength = word.length();
        if (wordlength == depth) {
            return v;
        }
        if (wordlength > depth) {
            if ((v.getChild(word.charAt(depth) - 'a')) != null) {
                return findNode(v.getChild(word.charAt(depth) - 'a'), word, depth + 1);
            } else {
                return v;
            }
        }
        return null;
    }

    /**
     * Searches for String word in the trie. If the path for the word is found
     * sets the field 'elementEnds' for the terminal node to true. If the path
     * for the word is not found, creates the missing part of the path provided
     * the some part already exists, or creates the full path if no path exists.
     * Afterwards sets the field 'elementEnds' for the terminal node to true.
     */
    public boolean insertItem(String word) {

        ENDictTrieNode node = findNode(word);

        if (node.getDepth() == word.length()) {
            node.setElementEnds(true);
            return true;
        }

        if (node.getDepth() > word.length()) {
            System.out.println("Something Nasty Happened!");
            return false;
        }

        while (node.getDepth() < word.length()) {
            int depth = node.getDepth();
            node.setChild(word.substring(node.getDepth()).charAt(0) - 'a', new ENDictTrieNode(node, false, depth + 1));
            node = node.getChild(word.substring(node.getDepth()).charAt(0) - 'a');
        }
        if (node.getDepth() == word.length()) {
            node.setElementEnds(true);
        }
        size++;
        return true;
    }

    /**
     * Searches for word in the trie. Returns the String representation of the
     * matching path. The matching path is extracted using findNode method.
     */
    public String findInfo(String word) {
        ENDictTrieNode insNode = findNode(word);
        return insNode.getValue();
    }

    public String dotString(String title) {
        String str = "digraph ENDictTrie {\n";
        str += "fontcolor=\"navy\";\nfontsize=20;\nlabelloc=\"t\";\nlabel=\"" + title + "\"\n";
        str += root().dotString();
        str += "}\n";
        return str;
    }

}
