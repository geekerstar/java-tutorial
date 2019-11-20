package com.geekerstar.arithmetic;

import java.util.Arrays;

/**
 * @author geekerstar
 * date: 2019/3/2 15:50
 * description:
 *
 * 快速排序、归并排序、堆排序和链表的排序
 */
public class SortSummarize {
    public static void main(String[] args) {
        int[] a = {9,8,7,6,5,1,3,0,10,-1,99,-10};
        int[] b = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,17,16};
        b = a;
        print("选择排序 ",selectSort(b));
        print("冒泡排序 ",bubbleSort(b));
        print("插入排序 ",insertSort(b));
        print("归并排序 ",mergeSort(b));
        print("希尔排序 ",shellSort(b));
        print("堆排序 ",heapSort(b));

        print("快速排序 ",quickSort(b));
    }
    /**
     * 冒泡排序算法：每次将最小的一个数”浮“上去
     * 最好情况O(n),即数组本身有序
     * 最坏情况O(n^2)
     */
    public static int[] bubbleSort(int[] a) {
        a = Arrays.copyOf(a, a.length);

        int count = 0;
        boolean terminated = false;

        for(int i=0;i<a.length-1&&!terminated;i++) {
            terminated = true;
            for(int j=a.length-2;j>=i;j--) {
                count++;
                if(a[j]>a[j+1]) {
                    swap(a,j+1,j);
                    terminated = false;
                }
            }
        }
        System.out.println("冒泡排序比较次数: "+count);
        return a;
    }


    /**
     * 选择排序：每次选出待排序中最小的一个
     */
    public static int[] selectSort(int[] a) {
        a = Arrays.copyOf(a, a.length);

        int count = 0;
        for(int i=0;i<a.length-1;i++) {
            int min = a[i];
            int minIndex = i;
            for(int j=i+1;j<a.length;j++) {
                count++;
                if(a[j]<min) {
                    minIndex = j;
                    min = a[j];
                }
            }
            swap(a,i,minIndex);
        }
        System.out.println("选择排序比较次数: "+count);
        return a;
    }

    public static int[] insertSort(int[] a) {
        a = Arrays.copyOf(a, a.length);

        int count = 0;
        for(int i=1;i<a.length;i++) {//  i之前有序,i指向待排序的元素
            if(a[i]<a[i-1]) {
                int temp = a[i];
                int j = i-1;//j指向当前元素的前一个
                for(;j>=0&&a[j]>temp;j--) {
                    count++;
                    a[j+1] = a[j];
                }
                a[j+1] = temp;
            }
        }

        System.out.println("插入排序比较次数: "+count);
        return a;
    }
    private static void swap(int[] a,int i,int j) {
        if (i==j)   return;
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    /**
     * 希尔排序
     * @param a
     * @return
     */
    public static int[] shellSort(int[] a) {
        a = Arrays.copyOf(a, a.length);
        int count = 0;

        int increment = a.length;
        do {
            increment = increment/3 + 1;
            for(int i=increment;i<a.length;i=i+increment) {
                if(a[i]<a[i-increment]) {
                    int temp = a[i];
                    int j=i-increment;
                    for(;j>=0&&a[j]>temp;j -= increment) {
                        count++;
                        a[j+increment] = a[j];
                    }
                    a[j+increment] = temp;
                }
            }
        }while(increment>1);

        System.out.println("希尔排序比较次数： "+count);
        return a;

    }
    /**
     * 堆排序
     * @param a
     * @return
     */
    public static int[] heapSort(int[] a) {
        a = Arrays.copyOf(a, a.length);
        int length = a.length;
        for(int i=length/2-1;i>=0;i--)//2*i+1<=length-1,最后一个有左孩子的节点
            heapAdjust(a,i,length);

        for(int i=length-1;i>=0;i--) {
            swap(a,0,i);
            heapAdjust(a,0,i);//
        }

        return a;
    }

    //每次将以i为root的子树满足最大堆特性;i指向待调整的节点
    private static void heapAdjust(int[] a,int i,int length) {
        int temp = a[i];

        for(int j=2*i+1;j<length;j=2*i+1){//刚开始时指向左孩子
            if(j+1<length && a[j+1]>a[j])//如果有做右孩子，且右孩子比左孩子大
                j++;//j指向左右孩子中值较大的一个

            if(temp>=a[j])
                break;
            a[i] = a[j];

            i=j;
        }
        a[i] = temp;
    }

    public static int[] mergeSort(int[] a) {
        a = Arrays.copyOf(a, a.length);
        int[] aux = new int[a.length];
        mergeSort(a,aux,0,a.length-1);
        return a;
    }
    private static void mergeSort(int[] a,int[] aux,int lo,int high) {
        if(lo>=high)    return;

        int mid = (lo+high)/2;
        mergeSort(a,aux,lo,mid);
        mergeSort(a,aux,mid+1,high);
        merge(a,aux,lo,mid,high);
    }
    private static void merge(int[] a,int aux[],int lo,int mid,int high) {
        for(int i=lo;i<=high;i++) {
            aux[i] = a[i];
        }

        int lo_h = mid+1;
        for(int i=lo;i<=high;i++) {
            if(lo>mid)
                a[i] = aux[lo_h++];
            else if(lo_h>high)
                a[i] = aux[lo++];
            else {
                if(aux[lo]<=aux[lo_h])
                    a[i] = aux[lo++];
                else
                    a[i] = aux[lo_h++];
            }
        }
    }
    /**
     * 快速排序
     * @param a
     * @return
     */

    public static int[] quickSort(int[] a) {
        a = Arrays.copyOf(a, a.length);
        quickSort(a,0,a.length-1);
        return a;
    }
    private static void quickSort(int[] a,int low,int high) {
        if(low>=high)   return;

        int partition = partition(a,low,high);
        quickSort(a,low,partition-1);
        quickSort(a,partition+1,high);
    }
    private static int partition(int[] a,int low,int high) {
        int tempt = a[low];

        while(low<high) {
            while(low<high&&a[high]>=tempt)
                high--;
//            Swap(a,low,high);
            a[low] = a[high];

            while(low<high&&a[low]<=tempt)
                low++;
//            Swap(a,low,high);
            a[high] = a[low];
        }
        a[low] = tempt;
        return low;
    }
    private static void print(String str,int[] a) {
        System.out.println(str);
        for(int i=0;i<a.length;i++)
            System.out.print(a[i]+" ");
        System.out.println("\n");
    }

    /**
     * 下面是链表的快速排序
     * @param str
     * @param a
     */
    public static ListNode quickSort(ListNode begin, ListNode end) {
        //判断为空，判断是不是只有一个节点
        if (begin == null || end == null || begin == end)
            return begin;
        //从第一个节点和第一个节点的后面一个几点
        //begin指向的是当前遍历到的最后一个<= nMidValue的节点
        ListNode first = begin;
        ListNode second = begin.next;

        int nMidValue = begin.val;
        //结束条件，second到最后了
        while (second != end.next && second != null) {//结束条件
            //一直往后寻找<=nMidValue的节点，然后与fir的后继节点交换
            if (second.val < nMidValue) {
                first = first.next;
                //判断一下，避免后面的数比第一个数小，不用换的局面
                if (first != second) {
                    int temp = first.val;
                    first.val = second.val;
                    second.val = temp;
                }
            }
            second = second.next;
        }
        //判断，有些情况是不用换的，提升性能
        if (begin != first) {
            int temp = begin.val;
            begin.val = first.val;
            first.val = temp;
        }
        //前部分递归
        quickSort(begin, first);
        //后部分递归
        quickSort(first.next, end);
        return begin;
    }

    /**
     * 链表的归并排序
     */
    public ListNode sortList1(ListNode head) {
        if(head==null||head.next==null) return head;
        quickSort(null,null);
        return mergeSort(head);
    }
    private ListNode mergeSort(ListNode head){
        if(head==null || head.next==null)    return head;

        ListNode mid = getMid(head);
        ListNode second = mid.next;
        mid.next = null;

        ListNode left = mergeSort(head);
        ListNode right = mergeSort(second);
        return merge(right,left);
    }

    private ListNode merge(ListNode l1,ListNode l2){
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while(l1!=null&&l2!=null){
            if(l1.val<=l2.val){
                cur.next = l1;
                l1 = l1.next;
            }else{
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if(l1!=null)
            cur.next = l1;
        else
            cur.next = l2;

        return dummy.next;

    }

    private ListNode getMid(ListNode head){
        if(head==null ||head.next==null)    return head;
        ListNode slow = head;
        ListNode faster = head.next;
        while(faster!=null&&faster.next!=null){
            slow = slow.next;
            faster = faster.next.next;
        }
        return slow;
    }

    private class ListNode {
        int val;
        ListNode next = null;
        ListNode(int val) {
            this.val = val;
        }
    }

}
