Merge k sorted arrays 
=====================

We are given an ‘n’ number of arrays, let's say we take three arrays i.e. arr1[], arr2[] and arr3[] of integer type. The task is to merge all the given integer arrays in such a manner that the resultant array is sorted in the runtime only.

Example
-------

**Input**

    int a[]={21,22,23,24};
    int b[] ={28,31,35};

**Output**

    int resultant[]={21,22,23,24,28,31,35}.

**Explanation**. The array elements are compared before they are added and added according to their suitable position in the resultant array.

**Input**

   int a[]={1,3,5,7,9,11,13};
   int b[] ={14,16,18}
   int c[] ={19,20,21,22}

**Output**

    int resultant[]={1,3,5,7,9,11,13,14,16,18,19,20,21,22}.

**Explanation**. The array elements are compared before they are added and added according to their suitable position in the resultant array.