package ce210;


public class TestHeapSort {
  public static void main(String args[]) {
    SampleIntegerDescendingData data = new SampleIntegerDescendingData();
    Integer a[] = new Integer[data.getSize()];
    Integer b[] = new Integer[data.getSize()];
    Integer temp;
    Sort<Integer> sort = new Sort<Integer>();
    int result = 0;
    int i=0;
    while( (temp = data.getNext()) != null) {
      a[i] = temp;
      i++;
    }
    
    System.out.println("Array lines are :"+data.getSize());
    
   // printVolosDistTable(a);
    System.out.println("\n############################\n");
    result = sort.heapSort(a, 0, a.length-1);
    System.out.println("Sorted!");
    System.out.println(result);
    System.out.println("\n############################\n");
   // printVolosDistTable(a);
    
  }
  
  static void printVolosDistTable(VolosDist a[]) {
    for(int i=0; i<a.length; i++) {
      System.out.println(i+". "+a[i].toString());
    }
  }
}