package com.dirkjia.sort;

public class QuickSort {
    public static void main(String[] args) {
        int[] data = new int[]{45,28,80,90,50,16,100,10};
        quickSort(data,0,data.length-1);
        for(int i:data){
            System.out.println(i);
        }
    }

    private static void quickSort(int[] data,int left,int right){
        if(left<right){
            int mid = qmf(data,left,right);
            quickSort(data,left,mid-1);
            quickSort(data,mid+1,right);
        }
    }

    private static int qmf(int[] data,int left,int right){
        int baseindex = left;
        int base = data[baseindex];
        while(left<right){
            //先从右边找，很重要，不然就会导致基准出问题
            while(left<right && data[right]>=base){
                right--;
            }
            while(left<right && data[left]<=base){
                left++;
            }
            if(left<right){
                int temp = data[left];
                data[left]=data[right];
                data[right]=temp;
            }
        }
        //基准归位----
        data[baseindex]=data[left];
        data[left]=base;
        return left;
    }
}
