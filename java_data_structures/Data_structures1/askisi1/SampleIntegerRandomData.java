package ce210.sampleData;

import java.lang.*;
import java.util.*;

public class SampleIntegerRandomData { 
  private int size;
  private int usedData=0;
  private int maximum_integer;
  Random rand = null;
  private static final int MAX_SIZE=100000;
  
  public enum INIT_SEED { RANDOM, FIXED }
  
  public Integer getNext() {
    if(usedData++ < size) {
      return new Integer(rand.nextInt(maximum_integer));
    }
    System.out.println("Generated "+size+" data!!!");
    return null;
  }   

  public int getSize() {
    return size;
  }
    
  public int getUsedData() {
    return usedData;
  }
        
  public SampleIntegerRandomData(int maxelements, int maxint, long seed)  {        
    size = maxelements;
    maximum_integer = maxint;
    rand = new Random( seed );
  }
  
  public SampleIntegerRandomData()  {        
    this(MAX_SIZE, 2*MAX_SIZE, 12345678L);
  }
}
