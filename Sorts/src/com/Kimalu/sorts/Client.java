package com.Kimalu.sorts;

import java.util.Random;

public class Client {
	public static int[] src=new int[10];  
    
    static{  
        for(int i=0;i<src.length;i++){  
            src[i]=new Random().nextInt(10000);  
        }  
    }  
      
    public static void main(String args[]){  
        printArray();  
        //selectionSort();  
        //injectionSort();  
        bubbleSort();  
        printArray();  
    }  
    /** 
     * 选择排序 
     */  
    public static void selectionSort(){  
        for(int i=0;i<src.length-1;i++){  
            for(int j=i+1;j<src.length;j++){  
                if(src[i]>src[j]){  
                    swap(i, j);  
                }  
            }  
        }  
        printArray();  
    }  
      
    /** 
     * 冒泡排序 
     */  
    public static void bubbleSort(){  
        //boolean flag=true;  
        for(int i=0;i<src.length-1 ;i++){  
            //flag=false;  
            for(int j=0;j<src.length-i-1 ;j++){  
                if(src[j+1]<src[j]){  
                    swap(j+1,j);  
                //  flag=true;  
                }  
            }  
        }  
          
    }  
    /** 
     * 插入排序 
     */  
    public static void injectionSort(){  
        for(int i=1;i<src.length;i++){  
            int temp=src[i];  
            int j=i-1;  
            while(temp<src[j]){  
                src[j+1]=src[j];  
                j--;  
                if(j<0){  
                    break;  
                }  
            }  
            src[j+1]=temp;  
        }  
    }  
    /** 
     * 交换两个值 
     * @param i 
     * @param j 
     */  
    private static void swap(int i, int j) {  
        int t=src[i];  
        src[i]=src[j];  
        src[j]=t;  
    }  
      
    /** 
     * 打印数组 
     */  
    private static void printArray(){  
        for(int i=0;i<src.length;i++){  
            System.out.print(src[i]+"==>");  
        }  
        System.out.println();  
        System.out.println("===========================================================");  
    }  
}
