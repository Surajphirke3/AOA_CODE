import java.util.Scanner;

public class BinarySearch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter the elements (sorted): ");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.print("Enter the element you want to search: ");
        int key = scanner.nextInt();

   
        int result = binarySearch(arr, key);
        if (result != key) {
            System.out.println("Element found at index: " + result);
        } else {
            System.out.println("Element not found.");
        }

        scanner.close();
    }

    public static int binarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == key) {
                return mid; 
            }
             else if (arr[mid] < key) {
                low = mid + 1; 
            } else {
                high = mid - 1; 
            }
        }
        return 0; 
    }
}
