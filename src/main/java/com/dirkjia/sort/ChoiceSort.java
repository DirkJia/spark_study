package com.dirkjia.sort;

public class ChoiceSort {

    public static void main(String[] args) {
        int[] data = new int[]{45,28,80,90,50,16,100,10};
        cSort(data);
        for(int i:data){
            System.out.println(i);
        }
    }

    public static void cSort(int[] data){
        for(int i=0;i<data.length;i++){
            int min=i;
            for(int j=i+1;j<data.length;j++){
                if(data[min]>data[j]){
                    min=j;
                }
            }
            if(min!=i){
                int temp = data[i];
                data[i]=data[min];
                data[min]=temp;
            }
        }
    }
}
