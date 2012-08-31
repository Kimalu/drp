package com.Kimalu.sorts;

public class HeapSort {
	public static void heapSort(int pData[], int begin, int len) {

		// 建立大顶堆
		buildHeap(pData, begin, len);

		System.out.print("建堆后：");
		for (int i = 0; i < pData.length; i++)
			System.out.print(pData[i] + " ");
		for (int i = 0; i < len; i++) {
			// 交换堆顶和最后一个元素
			swap(pData, begin, len - 1 - i);
			// 重新调整为大顶堆 0表示总是从堆顶开始调整
			shift_down(pData, begin, 0, len - 1 - i);
		}
	}

	/**
	 * 建立大顶堆
	 * 
	 * @param pData
	 * @param begin
	 *            起始位置
	 * @param len
	 *            数组长度
	 */
	public static void buildHeap(int[] pData, int begin, int len) {
		// 最后一个有孩子的节点
		int pos = len/2 - 1;
		// 从最后一个有孩子节点开始，一直到堆顶建堆
		for (int i = pos; i >= 0; i--) {
			shift_down(pData, begin, i, len);
		}
	}

	/**
	 * 调整元素的位置
	 * 
	 * @param pData
	 * @param begin
	 * @param pos
	 * @param len
	 */
	public static void shift_down(int[] pData, int begin, int pos, int len) {
		// 当前待调整的元素
		int tmp = pData[pos];
		// 该元素的左孩子
		int index = 2 * pos + 1;
		while (index < len) {

			if (index + 1 < len
					&& pData[begin + index] < pData[begin + index + 1]) {
				// 如果右孩子大于左孩子的话index+1
				index = index + 1;
			}
			if (pData[begin + pos] < pData[begin + index]) {
				// 交换孩子和双亲节点
				pData[begin + pos] = pData[begin + index];
				// 重新赋初值
				pos = index;
				index = 2 * pos + 1;
			} else {
				break;
			}
			// 把双亲值赋给孩子节点
			pData[begin + pos] = tmp;
		}
	}

	/**
	 * 交换元素位置
	 * 
	 * @param pData
	 * @param src
	 *            原位置
	 * @param des
	 *            目标位置
	 */
	public static void swap(int pData[], int src, int des) {
		int tmp = pData[des];
		pData[des] = pData[src];
		pData[src] = tmp;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 随机生成待排数组
		int[] pData = new int[10];
		for (int i = 0; i < 10; i++)
			pData[i] = (int) (Math.random() * 100);
		System.out.print("建堆前：");
		for (int i = 0; i < pData.length; i++)
			System.out.print(pData[i] + " ");
		System.out.println("");
		// 堆排序
		HeapSort.heapSort(pData, 0, pData.length);

		System.out.println("\n***********************");

		for (int i = 0; i < pData.length; i++)
			System.out.print(pData[i] + " ");
	}
}
