package ce210;
import ce210.SampleIntegerRandomData;

public class TestQuickSort {
  public static void main(String args[]) {
    SampleIntegerRandomData data = new SampleIntegerRandomData();
    Integer a[] = new Integer[data.getSize()];
    Integer temp = null;
    Sort<Integer> sort = new Sort<Integer>();
    int result = 0;
    int i=0;
    while( (temp = data.getNext()) != null) {
      a[i] = temp;
      i++;
    }
    
    System.out.println("Array lines are :"+data.getSize());
    
   // printVolosDistTable(a);
    result = sort.quickSort(a,0,a.length-1);
    System.out.println("Sorted!");
    System.out.println(result);

    //printVolosDistTable(a);
    
  }
  
  static void printVolosDistTable(VolosDist a[]) {
    for(int i=0; i<a.length; i++) {
      System.out.println(i+". "+a[i].toString());
    }
  }
}