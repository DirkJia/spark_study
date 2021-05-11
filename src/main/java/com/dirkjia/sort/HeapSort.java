package com.dirkjia.sort;

public class HeapSort {
    public static void main(String[] args) {
        int[] data = new int[]{45,28,80,90,50,16,100,10};
        heapSort(data);
        for(int i:data){
            System.out.println(i);
        }
    }

    public static void heapSort(int[] data){
        for(int i=data.length/2-1;i>=0;i--){
            adjustHeap(data,i,data.length);
        }
        for(int i=data.length-1;i>0;i--){
            int temp = data[i];
            data[i]=data[0];
            data[0]=temp;
            adjustHeap(data,0,i);
        }
    }


    private static void adjustHeap(int[] data,int cur,int end){
        int parent = cur;
        int left = parent*2+1;
        while(left<end){
            int max = left;
            if(left+1<end && data[left+1]>data[left]){
                max = left+1;
            }
            if(data[parent]>data[max]){
                return;
            } else {
                int temp = data[parent];
                data[parent]=data[max];
                data[max]=temp;
                parent=max;
                left = parent*2+1;
            }
        }
    }
}
