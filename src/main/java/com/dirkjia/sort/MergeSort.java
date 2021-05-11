package com.dirkjia.sort;

public class MergeSort {

    public static void main(String[] args) {
        int[] data = new int[]{45,28,80,90,50,16,100,10};
        mergeSort(data,0,data.length-1);
        for(int i:data){
            System.out.println(i);
        }
    }

    public static void mergeSort(int[] data,int left,int right){
        if(left>=right){
            return ;
        }
        int mid = left+(right-left)/2;
        mergeSort(data,left,mid);
        mergeSort(data,mid+1,right);
        merge(data,left,mid,right);
    }

    private static void merge(int[] data,int left,int mid,int right){
        int l1 = left;
        int l2 = mid+1;
        int[] temp = new int[data.length];
        int start = left;
        while(l1<=mid&&l2<=right){
            if(data[l1]<data[l2]){
                temp[start]=data[l1];
                l1++;
            } else {
                temp[start]=data[l2];
                l2++;
            }
            start++;
        }
        while(l1<=mid){
            temp[start]=data[l1];
            start++;
            l1++;
        }

        while(l2<=right){
            temp[start]=data[l2];
            start++;
            l2++;
        }
        for(int i=left;i<=right;i++){
            data[i]=temp[i];
        }

    }

}
