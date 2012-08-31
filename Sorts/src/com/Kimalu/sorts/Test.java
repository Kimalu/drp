package com.Kimalu.sorts;

public class Test {

	public static void main(String[] args) {
		Test t =new Test();
		int[] array={653,123,7,783,412,44,678,12,5,999};
	//	System.out.println();
	//	t.selectSort(array);
	//	t.bubbleSort(array);
	//	t.insertSort(array);
		t.quickSort2(array, 0, array.length-1);
		t.printArray(array);
	}
	public void selectSort(int[] array){
		for(int i=0;i<array.length;i++){
			int smallIndex=i;
			for(int j=i+1;j<array.length;j++){
				if(array[smallIndex]>array[j]){
					smallIndex=j;
				}
			}
			if(i!=smallIndex){
				int temp=array[smallIndex];
				array[smallIndex]=array[i];
				array[i]=temp;
			}
			this.printArray(array);
			System.out.println();
		}
	}
	
	public void bubbleSort(int[] array){
		for(int i=0;i<array.length;i++){
			for(int j=0;j<array.length-i-1;j++){
				if(array[j]>array[j+1]){
					int temp=array[j+1];
					array[j+1]=array[j];
					array[j]=temp;
				}
			}
			this.printArray(array);
			System.out.println();
		}
	}
	
	public void insertSort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			int value = array[i];
			int position = i;
			// shift larger values to the right
			while (position > 0 && array[position - 1] > value) {
				array[position] = array[position - 1];
				position--;
			}
			array[position] = value;
			this.printArray(array);
			System.out.println();
		}
	}
	
	public void quickSort2(int[] a,int left,int right){
		int i, j;  
        int Pivot;  
        int Temp;  

        i = left;  
        j = right;  

        Pivot = a[left];  
        if (i < j)  
        {  
            do  
            {  
                while (a[i] < Pivot && i < right)  
                {  
                    i++;  
                }  
                while (a[j] > Pivot && j > left)  
                {  
                    j--;  
                }  
                if (i < j) //exchange a[i] and a[j]  
                {  
                    Temp = a[i];  
                    a[i] = a[j];  
                    a[j] = Temp;  

                }  
            } while (i < j);  
                /*if (i > j)  
                {  
                    Temp = a[left]; // exchange a[Left] and a[j]  
                    a[left] = a[j];  
                    a[j] = Temp;  
                }  */
                quickSort2(a,left, j - 1); // left  
                quickSort2(a,j + 1, right); // right  

        }  
    }  
	
	public void printArray(int[] array){
		for(int i=0;i<array.length;i++){  
            System.out.print(array[i]+"==>");  
        }  
	}

}
