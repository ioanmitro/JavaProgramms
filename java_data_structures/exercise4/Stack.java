public interface Stack <T>{
  public int size();           // επιστροφή του μεγέθους της στοίβας.
  public void push(T o);  // ένθεση στην κορυφή της στοίβας.
  public T pop();         // απόσβεση από την κορυφή της στοίβας.
  public T top();         // επιστροφή του κορυφαίου στοιχείου της στοίβας χωρίς διαγραφή του.
}