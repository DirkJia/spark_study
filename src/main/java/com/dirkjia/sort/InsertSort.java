package com.dirkjia.sort;

public class InsertSort {

    public static void main(String[] args) {
        int[] data = new int[]{45,28,80,90,50,16,100,10};
        insertSort(data);
        for(int i:data){
            System.out.println(i);
        }
    }

    public static void insertSort(int[] data){

        for(int i=1;i<data.length;i++){
           int temp = data[i];
           int index = i;
           for(int j=i;j>0;j--){
               if(data[j-1]>temp){
                   data[j]=data[j-1];
                   index=j-1;
               }
           }
           data[index]=temp;
        }
    }

}
