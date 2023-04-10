# Lab 02 Assignment

> 班级：212112
> 
> 学号：21373339
> 
> 姓名：周星达

## 提交要求（以下内容在最终提交的作业中可以删除，仅作参考）

目录格式

```shell
-- 1-学号-姓名.zip
    |-- answer.pdf
    |-- solution
        |-- Question1
            |-- 题1的代码文件(如果需要)
            |-- 题1的相关截图(如果需要)

        |-- Question2
            |-- 题2的代码文件(如果需要)
            |-- 题2的相关截图(如果需要)
        |-- ...
```

answer.md 的书写格式：

7、8、11、17、18题需要编写代码至solution文件夹，编写函数的题直接在pdf中用代码块的形式展示即可

## Question1 
```
（a）PersonTest.java

（b）2个,PersonTest.class,Person.class

（c）java Person:错误: 在类 Person 中找不到 main 方法, 请将 main 方法定义为:public static void main(String[] args)否则 JavaFX 应用程序类必须扩展javafx.application.Application

java personTest:错误: 找不到或无法加载主类 personTest 原因: java.lang.NoClassDefFoundError: PersonTest (wrong name: personTest)

java PersonTest.class:错误: 找不到或无法加载主类 PersonTest.class 原因: java.lang.ClassNotFoundException: PersonTest.class

您好，很高兴认识您nice to meet you!
```
## Question2 

```java
public class Question2 
{
    public static void main(String[] args) 
    {
        System.out.println("21373339"+"周星达");
        
    }
}
```

### Question09

```java
public static double getPi(int n)
{
    if(n<0) n=0;
    double ans=0;
    for(int i=0;i<=n;i++)
    {
        if(i%2==0)
        {
            ans+=4/(2*i+1);
        }
        else
        {
            ans-=4/(2*i+1);
        }
    }
    return ans;
}
```

### Question10

```java

    public static int[] qsort(int[] arr)
    {
        Myqsort(arr,0,arr.length-1);
        return arr;
    }

    public void Myqsort(int[] arr, int left, int right)
    {
        int i, last;
        int left=0;
	    if(left >= right) return;
	    int temp0=arr[left];
	    arr[left]=arr[(left+right)/2];
	    arr[(left+right)/2]=temp0;//move partition elem to v[0]
	    last = left;

	    for(i=left+1; i<=right; i++)  //partition
	    {
	    	if(arr[i]<arr[left])
	    	{
	    		last++;
	    		int temp1=arr[last];
	    		arr[last]=arr[i];
	    		arr[i]=temp1;
	    	}
	    }
	
	    int temp2=arr[last];
	    arr[last]=arr[left];
	    arr[left]=temp2; //restore partition elem

	    Myqsort(arr,left,last);
	    Myqsort(arr,last+1,right);
    }

```

### Question12 
```
A n   A f r i c a n   S w a l l o w 
```

### Question13 
```
p1的x,y坐标:1111,2222
p2的x,y坐标:-100,-200
p1的x,y坐标:0,0
p2的x,y坐标:0,0
```

### Question14
```
数组a的元素个数=4
数组b的元素个数=3
数组a的引用=[I@4dd8dc3
数组b的引用=[I@6d03e736
数组a的元素个数=3
数组b的元素个数=3
a[0]=100,a[1]=200,a[2]=300
b[0]=100,b[1]=200,b[2]=300
```

### Question15
```
1
2
-1
-2
-3
-4
9
7
6

```

### Question15
```java
public static String strscat(String... args)
    {
        int len= args.length;
        String ans="";
        for(int i=0;i<len;i++)
        {
            ans+=args[i];
        }
        return ans;
    }
```

```
实现机制：通过传入相应变量的数组作为参数
调用strscat(new String[]{"a", "b"})能通过编译
不能。
不能。
```