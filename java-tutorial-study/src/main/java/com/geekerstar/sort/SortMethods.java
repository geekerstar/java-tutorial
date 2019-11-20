package com.geekerstar.sort;

/**
 * @author geekerstar
 * date: 2019/3/2 11:06
 * description:
 */
public class SortMethods {

    public static void main(String[] args) {
        int a[] = {8,3,2,5,9,3,6};
        Print(a);
        //bubbleSort2(a);
//        selectSort(a);
//        insertSort(a);
//        insertSort2(a);
//        shellSort(a);
//        quickSort(a);
//        mergeSort(a);
        Print(a);
    }

    /**
     * 冒泡排序 时间复杂度O(n^2) 空间复杂度O(1)
     *
     * 算法描述：
     * 1）设待排序序列中的记录的数为 n
     * 2）一般地，第 i 趟起泡排序从1 到 n-i+1
     * 3）依次比较相邻两个记录的关键字，如果发生逆序，则交换之。
     * 4）其结果是这 n-i+1 个记录中，关键字最大的记录被交换到第 n-i+1 的位置上，最多作 n-1 趟。
     *
     * @param a
     */
    private static void bubbleSort(int[] a){
        for(int i = 0; i < a.length-1; i++){
            for(int j = 0; j< a.length - i - 1; j++){
                if (a[j] > a[i]){
                    swap(a,j,j+1);
                }
            }
        }
    }

    private static void swap(int[] a,int i,int j){
        int temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void Print(int[] a){
        for(int num : a ){
            System.out.print(num + "\t");

        }
        System.out.println();

    }


    /**
     * 优化后的冒泡排序
     *
     * @param a
     */
    private static void bubbleSort2(int[] a){
        for(int i =0;i < a.length -1; i++){
            boolean flag = true;
            for(int j = 0; j < a.length - i -1; j++){
                if(a[j] > a[j+1]){
                    swap(a,j,j+1);
                    flag = false;
                }
            }
            if(flag){
                break;
            }
        }
    }

    /**
     * 选择排序 时间复杂度O(n^2) 空间复杂度O(1)
     *
     * 算法描述
     * 1）首先通过n-1次比较，从n个数中找出最小的， 将它与第一个数交换——第一趟选择排序，结果最小的数被安置在第一个元素位置上。
     * 2）再通过n-2次比较，从剩余的n-1个数中找出关键字次小的记录，将它与第二个数交换——第二趟选择排序。
     * 3)重复上述过程，共经过n-1趟排序后，排序结束。
     *
     * @param a
     */
    private static void selectSort(int[] a){
        for(int i = 0; i <a.length - 1; i++) {
            int k = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[k]) {
                    k = j;
                    swap(a,j,i);
                }
            }
            if (k != i) {
                swap(a, i, k);
            }
        }
    }

    /**
     * 插入排序 稳定
     *
     * 算法描述：
     * 1）数据存放在数组R[0….n-1]中，排序过程的某一中间时刻，R被划分成两个子区间R[0…i-1]和R[i….n-1]，其中：前一个子区间是已排好序的有序区；后一个子区间则是当前未排序的部分。
     * 2）将当前无序区的第1个记录R[i]插入到有序区R[0….i-1]中适当的位置，使R[0…i]变为新的有序区
     * 3）当插入第i(i≥1)个对象时, 前面的r[0], r[1], …, r[i-1]已经排好序。
     *
     * @param a
     */
    private static void insertSort(int[] a){
        for(int i = 0; i < a.length - 1; i++){
            int temp = a[i + 1];
            int j = i;
            while (a[j] > temp){
                a[j + 1] = a[j];
                j--;
                if (j < 0){
                    break;
                }
            }
            a[j + 1] = temp;
        }
    }

    /**
     * 二分插入排序（直接插入排序的优化）
     *
     * 算法描述：
     * 在直接插入排序的基础上，利用二分(折半)查找算法决策出当前元素所要插入的位置。
     * 二分查找：
     * 找到中间元素，如果中间元素比当前元素大，则当前元素要插入到中间元素的左侧；否则，中间元素比当前元素小，则当前元素要插入到中间元素的右侧。
     * 找到当前元素的插入位置 i 之后，把 i 和 high 之间的元素从后往前依次后移一个位置，然后再把当前元素放入位置 i。
     *
     * @param a
     */
    private static void insertSort2(int[] a){
        for(int i = 0; i < a.length - 1; i++){
            int temp = a[i + 1];
            int low = 0;
            int high = i;
            int mid;
            while (low <= high){
                mid = (low + high)/2;
                if (a[mid] > temp){
                    high = mid -1;
                } else {
                    low = mid +1;
                }
            }
            for (int j = i; j >= high + 1; j--){
                a[j + 1] = a[j];
            }
            a[high + 1] = temp;
        }
    }

    /**
     * 希尔排序（缩小增量排序）
     *
     * 算法描述：
     * 1）先取定一个小于 n 的整数 gap1 作为第一个增量，把整个序列分成 gap1 组。所有距离为 gap1 的倍数的元素放在同一组中，在各组内分别进行排序（分组内采用直接插入排序或其它基本方式的排序）。
     * 2）然后取第二个增量gap2<gap1，重复上述的分组和排序。
     * 3）依此类推，直至增量gap＝1，即所有元素放在同一组中进行排序为止。
     *
     * 简单来说：
     * 就好像上体育课，大家乱糟糟的站在一起，老师让大家排成一排然后按照1~4 （gap1）报数，让数到同一个数字的同学按大小个排序，排完之后在按1~3报数，然后重复上面的排序规则~嗯~这样子解释是不是就好理解多了呢~
     *
     * 算法分析：
     * 开始时 gap 的值较大, 子序列中的元素较少, 排序速度较快。
     * 随着排序进展, gap 值逐渐变小, 子序列中元素个数逐渐变多,由于前面大多数元素已基本有序, 所以排序速度仍然很快。
     * 分组后n值减小，n²更小，而T(n)=O(n²),所以T(n)从总体上看是减小了。
     * Gap的取法有多种。 shell 提出取 gap = n/2 ，gap = gap/2 ，…，直到gap = 1。gap若是奇,则gap=gap+1
     *
     * @param a
     */
    private static void shellSort(int[] a){
        //进行分组，初始的gap=n/2，然后递减直到n=1;
        for (int gap = (a.length + 1)/2; gap > 0; ){
            //分组管理
            for(int i = 0; i < a.length - gap; i++){
                //组内排序
                for (int j = i; j < a.length - gap; j+=gap){
                    if (a[j] > a[j + gap]){
                        swap(a,j,j+gap);
                    }
                }
            }
            if (gap > 1){
                gap = (gap + 1)/2;
            } else if (gap == 1){
                break;
            }
        }
    }

    /**
     * 快速排序
     *
     * 算法分析：
     * 快速排序是一个递归过程，快速排序的趟数取决于递归树的高度。
     * 如果每次划分对一个记录定位后, 该记录的左侧子序列与右侧子序列的长度相同, 则下一步将是对两个长度减半的子序列进行排序, 这是最理想的情况。
     *
     * 算法评价
     * 时间复杂度：
     * 最好情况（每次总是选到中间值作枢轴）T(n)=O(nlogn)
     * 最坏情况（每次总是选到最小或最大元素作枢轴）T(n)=O(n²)
     * 空间复杂度：需栈空间以实现递归
     * 最坏情况：S(n)=O(n)
     * 一般情况：S(n)=O(logn)
     *
     *
     * @param a
     * @param p
     * @param r
     */
    public static void quickSort(int[] a, int p, int r){
        if (p < r){
            int q = partition(a,p,r);
            quickSort(a,p,q-1);
            quickSort(a,q+1,r);
        }
    }

    private static int partition(int[] a, int p, int r){
        //第一个元素的枢纽
        int i = p;
        int j = r + 1;
        int x = a[p];
        while (true){
            while (a[++i] < x && i < r);
            while (a[--j] > x);
            if (i >= j){
                break;
            }
            swap(a,i,j);
        }
        swap(a,p,j);
        return j;
    }


    /**
     * 快速排序优化
     *
     * 可以证明，快速排序算法在平均情况下的时间复杂性和最好情况下一样，也是O(nlogn)，这在基于比较的算法类中算是快速的，快速排序也因此而得名。
     * 快速排序算法的性能取决于划分的对称性。因此通过修改partition( )方法，可以设计出采用随机选择策略的快速排序算法，从而使期望划分更对称，更低概率出现最坏情况。
     *
     * 因此，可在原划分方法中加入如下代码进行优化：
     *
     * @param a
     * @param p
     * @param r
     * @return
     */
    private static int partition2(int[] a, int p, int r){
        int rand = (int)(Math.random()*(r-p));
        swap(a,p,p+rand);
        //第一个元素的枢轴
        int i=p;
        int j = r+1;
        int x = a[p];

        while(true){
            while(a[++i]<x&&i<r);
            while(a[--j]>x);
            if (i>=j) {
                break;
            }
            swap(a, i, j);

        }
        swap(a, p, j);
        return j;
    }


    /**
     * 归并排序
     *
     * 归并——将两个或两个以上的有序表组合成一个新的有序表。
     * 算法描述：
     * 1）假设初始序列含有 n 个记录，则可看成 n 个有序的子序列，每个子序列长度为1。
     * 2）两两合并，得到 n/2 个长度为2或1的有序子序列。
     * 3）再两两合并，……如此重复，直至得到一个长度为n的有序序列为止。
     *
     * 算法分析：
     * 合并排序法主要是将两个已经排好序的资料合并和进行排序。如果所读入的资料尚未排序，可以先利用其它的排序方式来处理这两笔资料，然后再将排序好的这两笔资料合并。
     * 算法评价：
     * 时间复杂度：T(n)=O(nlogn)
     * 空间复杂度：S(n)=O(n)
     *
     *
     * @param a
     * @param left
     * @param right
     */
    private static void mergeSort(int[] a, int left, int right){
        if (left<right) {
            //1）先分解
            //把整个数组分解成[left,mid][mid+1,right]——mid=(left+right)/2
            int mid = (left+right)/2;
            mergeSort(a, left, mid);
            mergeSort(a, mid+1, right);

            //2)在归并
            int[] b = new int[a.length];
            merge(a,b, left,mid, right);
            //把辅助序列b中的数据拷贝到a中
            copy(a,b,left,right);
        }
    }
    private static void merge(int[] a, int[] b, int left,int mid, int right){
        int p = left;
        int r = mid+1;
        int k = left;
        while (p<=mid&&r<=right) {
            if (a[p]<a[r]) {
                b[k]=a[p++];
            }else {
                b[k]=a[r++];
            }
            k++;
        }
        if (p>mid) {
            for(int i=r;i<=right;i++){
                b[k++]=a[i];
            }
        }else{
            for(int i=p;i<=mid;i++){
                b[k++]=a[i];
            }
        }
    }
    private static void copy(int[] a, int[] b, int left, int right){
        for(int i=left;i<=right;i++){
            a[i]=b[i];
        }
    }
}
