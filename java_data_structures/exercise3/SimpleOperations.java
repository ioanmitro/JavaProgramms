public class SimpleOperations {
  
  public static Comparable larger(Comparable []array) {
	int i;
	Comparable max;
	max = array[0];
	for (i = 1; i < array.length; i++){
		if(max.compareTo(array[i]) == -1){
			max = array[i];
		}
	}
	return max;
  }

  public static Comparable smaller(Comparable []array) {
	int i;
	Comparable min;
	min = array[0];
	for (i = 1; i < array.length; i++){
		if(min.compareTo(array[i]) == 1){
			min = array[i];
		}
	}
	return min;
  }
  
  public static void main(String [] args) {
    java.util.Random rand = new java.util.Random();
    Rectangle []rectangles = new Rectangle[10];
    for(int i=0; i<rectangles.length; i++) 
      rectangles[i] = new Rectangle(0,0, rand.nextInt(20), rand.nextInt(20));
      
    for(Rectangle rect : rectangles)
      System.out.println(rect);
      
    Rectangle max = (Rectangle)larger(rectangles);
    System.out.println("Rectangle with maximum area is: "+max.getArea());
    Rectangle min = (Rectangle)smaller(rectangles);
    System.out.println("Rectangle with minimum area is: "+min.getArea());
  }
}
