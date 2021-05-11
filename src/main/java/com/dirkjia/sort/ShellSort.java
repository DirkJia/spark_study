package com.dirkjia.sort;

public class ShellSort {
    public static void main(String[] args) {
        int[] data = new int[]{45,28,80,90,50,16,100,10};
        shellSort(data);
        for(int i:data){
            System.out.println(i);
        }
    }

    public static void shellSort(int[] data){
        int size = data.length;
        int gap = size/2;
        for(;gap>=1;gap/=2){
            for(int i=gap;i<size;i++){
                int min=i;
                int temp = data[i];
                for(int j=i;j>=gap;j-=gap){
                    if(data[j-gap]>temp){
                        data[j]=data[j-gap];
                        min = j-gap;
                    }
                }
                data[min]=temp;
            }
        }
    }
}
