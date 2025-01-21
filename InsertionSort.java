
class InsertionSort {
  
 
    void sort(int a[])
    {
        int key = a.length;
        for (int i = 1; i < key; ++i) {
            int k = a[i];
            int j = i - 1;


            while (j >= 0 && a[j] > k) {
                a[j + 1] = a[j];
                j = j - 1;
            }
          
            a[j + 1] = k;
        }
    }

    public static void main(String args[])
    {
        int a[] = { 12,2,56,89,4};

        InsertionSort ob = new InsertionSort();
        ob.sort(a);

        int key = a.length;
        for (int i = 0; i < key; ++i)
            System.out.print(a[i] + " ");

    }
}