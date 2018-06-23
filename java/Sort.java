package math;

import java.text.DecimalFormat;

public class Sort {

 public static void main(String[] args) {  
  double[] arr = getDoubleArrs(21); 
  System.out.println("原始数据为：");
  print(arr,7);
  
  System.out.println("排序后的数据为：");
  //bubbleSort(arr); // 冒泡排序
  quickSort(arr);  
    
  print(arr, 7);
 }
 
 private static void quickSort(double[] arr) {
  class QuickSort{
   int partition(double[] arr, int start, int end) {
    double point = arr[end];
    int index = start;
    for(int i=index; i<end; i++) {
     if(arr[i] < point) {
      swap(arr, index++, i);
     }
    }
    swap(arr, index, end);
    return index;
   }  
   void swap(double[] arr, int i, int j) {
    double temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
   }
   void qsort(double[] arr, int i, int j) {
    if(i>=j) {
     return;
    }
    int pos = partition(arr, i, j);
    qsort(arr, i, pos-1);
    qsort(arr, pos+1, j);
   }
  }
  
  QuickSort quickSort= new QuickSort();
  quickSort.qsort(arr, 0, arr.length-1);
 }
 
 private static void bubbleSort(double[] arr) {
  for(int i=0,length=arr.length; i<length; i++) {
   for(int j=i+1; j<length; j++) {
    if(arr[i] > arr[j]) {
     double temp = arr[i];
     arr[i] = arr[j];
     arr[j] = temp;
    }
   }
  }
 }
  
 private static double[] getDoubleArrs(int length) {  
  int[] bs = new int[]{10,100,1000};
  DecimalFormat[] dfs = {new DecimalFormat("#.0"),new DecimalFormat("#.00"),new DecimalFormat("#.000")};
  double[] arr = new double[length];
  for(int i=0; i<length; i++) {
   arr[i]=Double.valueOf(dfs[i%3].format(Math.random()*bs[i%3]));
  }
  return arr;
 }
 
 private static void print(double[] arr, int rtn) {
  int i=0;
  for(double data : arr) {
   System.out.print(data+"\t\t");   
   if(++i==rtn) {
    System.out.println();
    i=0;
   }
  }
  System.out.println();
 }

}