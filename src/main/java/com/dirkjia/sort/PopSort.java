package com.dirkjia.sort;

public class PopSort {

    public static void main(String[] args) {
        int[] data = new int[]{45,28,80,90,50,16,100,10};
        popSort(data);
        for(int i:data){
            System.out.println(i);
        }
    }
    /**
     * 4 5 6 3 2 1
     * 第一次 ： 4 5 3 2 1 6
     * 第二次：从4---1， 4 3 2 1 5 6
     * @param data
     */
    public static void popSort(int[] data){
        for(int i=0;i<data.length;i++){
            for(int j=0;j<data.length-i-1;j++){
                if(data[j]>data[j+1]){
                    int temp = data[j];
                    data[j]=data[j+1];
                    data[j+1]=temp;
                }
            }
        }
    }
}
