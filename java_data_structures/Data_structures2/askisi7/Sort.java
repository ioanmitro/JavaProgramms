/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ce210;

/**
 *
 * @author Happ
 */
public class Sort<T extends Comparable> {

    public int insertionSort(T a[], int left, int right) {
        int comparisons = 0;
        int swaps = 0;
        for (int i = right; i > left; i--) {
            if (a[i].compareTo(a[i - 1]) < 0) {
                swaps++;
                T temp = a[i];
                a[i] = a[i - 1];
                a[i - 1] = temp;
            }
            comparisons++;
        }

        for (int i = left + 2; i <= right; i++) {
            int j = i;
            T v = a[i];
            comparisons++;
            while (v.compareTo(a[j - 1]) < 0) {
                swaps++;
                a[j] = a[j - 1];
                j--;
            }
            a[j] = v;
        }
        return swaps;
    }

    public int selectionSort(T a[], int left, int right) {
        T temp;
        int comparisons = 0;
        int swaps = 0;
        for (int unorderedleft = left; unorderedleft < right; unorderedleft++) {
            int min = unorderedleft;
            for (int j = unorderedleft + 1; j <= right; j++) {
                comparisons++;
                if (a[j].compareTo(a[min]) < 0) {
                    min = j;
                }
            }
            temp = a[unorderedleft];
            a[unorderedleft] = a[min];
            a[min] = temp;
            swaps++;
        }

        return comparisons;
    }

    public int bubbleSort(T a[], int left, int right) {
        T temp;
        int comparisons = 0;
        int swaps = 0;
        for (int i = left; i < right; i++) {
            for (int j = right; j > i; j--) {
                comparisons++;
                if (a[j].compareTo(a[j - 1]) < 0) {
                    swaps++;
                    temp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = temp;
                }
            }
        }
        return comparisons;
    }

    public int shakerSort(T a[], int left, int right) {
        T temp;
        int comparisons = 0;
        int swaps = 0;
        boolean leftdirection = true;
        while (left < right) {
            if (leftdirection) {
                leftdirection = !leftdirection;
                for (int j = right; j > left; j--) {
                    comparisons++;
                    if (a[j].compareTo(a[j - 1]) < 0) {
                        swaps++;
                        temp = a[j - 1];
                        a[j - 1] = a[j];
                        a[j] = temp;
                    }
                }
                left++;
            } else {
                leftdirection = !leftdirection;
                for (int j = left; j < right; j++) {
                    comparisons++;
                    if (a[j + 1].compareTo(a[j]) < 0) {
                        temp = a[j + 1];
                        a[j + 1] = a[j];
                        a[j] = temp;
                        swaps++;
                    }
                }
                right--;
            }
        }

        return comparisons;
    }

    public int quickSort(T a[], int left, int right) {
        int i = left - 1, j = right;
        T temp, o = a[right];

        int comparisons = 0;
        int swaps = 0;
        while (true) {
            comparisons++;
            while (a[++i].compareTo(o) < 0) {
                comparisons++;
            }
            comparisons++;
            while (o.compareTo(a[--j]) < 0) {
                comparisons++;
                if (j == left) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }

            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            swaps++;
        }

        temp = a[i];
        a[i] = a[right];
        a[right] = temp;
        swaps++;

        if (left < i - 1) {
            comparisons += quickSort(a, left, i - 1);
        }
        if (i + 1 < right) {
            comparisons += quickSort(a, i + 1, right);
        }

        return swaps;
    }

    public int makeHeap(T a[], int k, int N) {
        int comparisons = 0;
        int swaps = 0;
        while (2 * k + 1 <= N) {
            int maxson = 2 * k + 1;
            T temp;
            comparisons++;
            if (maxson < N && a[maxson].compareTo(a[maxson + 1]) < 0) {
                maxson++;
            }
            comparisons++;
            if (a[maxson].compareTo(a[k]) < 0) {
                break;
            }
            temp = a[k];
            a[k] = a[maxson];
            a[maxson] = temp;
            k = maxson;
            swaps++;
        }

        return comparisons;
    }

    public int heapSort(T a[], int left, int right) {
        int swaps = 0;
        int N = right - left;
        int k = (N % 2 == 0) ? N / 2 - 1 : N / 2;
        T temp;
        for (; k >= 0; k--) {
            swaps += makeHeap(a, k, right);
        }

        while (right > left) {
            temp = a[left];
            a[left] = a[right];
            a[right] = temp;
            swaps++;
            swaps += makeHeap(a, left, --right);
        }
        return swaps;
    }

    public int mergeSort(T a[], int left, int right, T b[]) {
        int l, r, middle = (right + left) / 2;
        int comparisons = 0;
        int swaps = 0;
        if (left < middle) {
            swaps += mergeSort(a, left, middle, b);
        }
        if (middle + 1 < right) {
            swaps += mergeSort(a, middle + 1, right, b);
        }

        for (l = left; l < middle + 1; l++) {
            swaps++;
            b[l] = a[l];
        }
        l = left;
        for (r = middle; r < right; r++) {
            swaps++;
            b[right + middle - r] = a[r + 1];
        }

        for (int k = left; k <= right; k++) {
            comparisons++;
            if (b[r].compareTo(b[l]) < 0) {
                swaps++;
                a[k] = b[r--];
            } else {
                swaps++;
                a[k] = b[l++];
            }
        }
        return comparisons;
    }
}
