import java.util.*;

public class QuickSort {

    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1); 
            quickSort(array, pi + 1, high); 
        }
    }

    public static int partition(int[] array, int low, int high) {
        int pivot = array[high];  // Taking the last element as pivot
        int i = (low - 1);  // Index of smaller element

        // Traverse through all elements, compare each with pivot
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {                  
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        
        // Swap the pivot element with the element at (i + 1)
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        
        return i + 1;  // Return the partition index
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Corrected Scanner initialization
        System.out.print("Enter the number of elements: ");
        int n = sc.nextInt(); // Corrected nextint() to nextInt()
        int[] array = new int[n];
        
        System.out.println("Enter " + n + " integers:");
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        
        System.out.println("Original array:");
        printArray(array);
        
        // Sorting the array using QuickSort
        quickSort(array, 0, array.length - 1);
        
        System.out.println("Sorted array:");
        printArray(array);
        
        sc.close(); // Close the scanner after use
    }
}
