package tries;

import trees.*;
//import ce210.amortized.*;
import ce210.sampleData.*;
import java.io.*;
import java.util.*;

class TestEnglishDictionaryTrie {
  public static void main(String args[]) {
    ENDictTrie trie = new ENDictTrie();
    String input; 
    String filename = "english-dict.txt";
    int i=1;
    
    if( args.length > 0 )
      filename = args[0];
    
    try {  
      BufferedReader in = new BufferedReader(new FileReader(filename));
      while( (input = in.readLine()) != null && input.length() > 0 ) {
        trie.insertItem(input);
        System.out.println(i+". "+input);
        i++;
      }      
      
      PrintWriter file = new PrintWriter("EnglishDictionaryTrie-Insert.dot");
      file.println(trie.dotString("Entered "+input));
      file.close();
      
      Process p = Runtime.getRuntime().exec("dot -Tpng EnglishDictionaryTrie-Insert.dot -o EnglishDictionaryTrie-Insert.png");
      p.waitFor();

    } catch(Exception ex) {
      ex.printStackTrace();
    }
  }
}