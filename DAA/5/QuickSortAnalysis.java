import java.util.*;

public class QuickSortAnalysis {

   
    static int deterministicPartition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return (i + 1);
    }

   
    static void deterministicQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = deterministicPartition(arr, low, high);
            deterministicQuickSort(arr, low, pi - 1);
            deterministicQuickSort(arr, pi + 1, high);
        }
    }

    
    static int randomizedPartition(int[] arr, int low, int high) {
        Random rand = new Random();
        int randomPivot = low + rand.nextInt(high - low + 1);

       
        int temp = arr[randomPivot];
        arr[randomPivot] = arr[high];
        arr[high] = temp;

        
        return deterministicPartition(arr, low, high);
    }

  
    static void randomizedQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = randomizedPartition(arr, low, high);
            randomizedQuickSort(arr, low, pi - 1);
            randomizedQuickSort(arr, pi + 1, high);
        }
    }

   
    static void printArray(int[] arr) {
        for (int value : arr)
            System.out.print(value + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        int[] arr1 = new int[n];

        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++)
            arr1[i] = sc.nextInt();

        int[] arr2 = Arrays.copyOf(arr1, n);

        System.out.println("\nOriginal Array:");
        printArray(arr1);

   
        long start1 = System.nanoTime();
        deterministicQuickSort(arr1, 0, n - 1);
        long end1 = System.nanoTime();
        long deterministicTime = end1 - start1;

        System.out.println("\nSorted Array using Deterministic Quick Sort:");
        printArray(arr1);
        System.out.println("Execution Time (ns): " + deterministicTime);

    
        long start2 = System.nanoTime();
        randomizedQuickSort(arr2, 0, n - 1);
        long end2 = System.nanoTime();
        long randomizedTime = end2 - start2;

        System.out.println("\nSorted Array using Randomized Quick Sort:");
        printArray(arr2);
        System.out.println("Execution Time (ns): " + randomizedTime);

       
        System.out.println("\n--- Time Comparison ---");
        System.out.println("Deterministic Quick Sort Time: " + deterministicTime + " ns");
        System.out.println("Randomized Quick Sort Time:   " + randomizedTime + " ns");

        sc.close();
    }
}
