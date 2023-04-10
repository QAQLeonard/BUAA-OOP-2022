# Lab 01 Assignment

> 班级：212112
> 
> 学号：21373339
> 
> 姓名：周星达

Question02-Question06,Question09,Question10，Question11均为截图文件。

## Question1 

`java` 的使用方法为：

    java [options] <主类> [args...]
            （执行类）
    或  java [options] -jar <jar 文件> [args...]
            （执行 jar 文件）
    或  java [options] -m <模块>[/<主类>] [args...]
        java [options] --module <模块>[/<主类>] [args...]
            （执行模块中的主类）
    或  java [options] <源文件> [args]
            （执行单个源文件程序）

    将主类、源文件、-jar <jar 文件>、-m 或
    --module <模块>/<主类> 后的参数作为参数
    传递到主类。

其参数的含义是：

[options]：为操作选项，如--help为将帮助信息输出到输出流
[args]：JAVA类中main函数的参数String [] args指的是运行时你给main函数传递的参数。可以在javac编译后通过在cmd中输入字符串进行参数传递

`javac` 的使用方法为：
    javac [options] [source files]

其参数的使用含义是：
[options]：为操作选项
[source files]：为待编译文件名

### Question07:

编写一段代码验证 Java 中类的基本数据类型字段（成员变量）的默认初始化值，请按照对应类型的字面量填写。

| 基本类型  | 默认值 | 基本类型    | 默认值 |
| :-------- | :----: | :---------- | :----: |
| **byte**  |    0   | **boolean** | false  |
| **short** |    0   | **char**    |        |
| **int**   |    0   | **float**   |  0.0   |
| **long**  |    0   | **double**  |  0.0   |

### Question08:

运行 java ShortCircuit，程序的输出是什么？
1
2
1
2
a is false
b is true

请解释 Java 执行串联逻辑运算时的流程，可以用文字、流程图、伪代码描述。
条件1&&条件2：当条件1为fals时后面的语句条件2不会被执行，只有当条件1为true时后面的语句才会被执行

思考如何利用短路这个机制来优化程序。
将需要优先判断的放在前面。如
```java
public class FindMinMax
{
    public static void main(String []args) 
    {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        int i=0;
        while(i>5 && i<10 && arr[i]<20)
        {
            System.out.print(arr[i]+" ");
        } 
    }
}
```
可以节省时间。

### Question11:

看程序输出结果,并提交结果内容或截图

```java
public class GlobalVar {
    int a = 10; // 全局变量,下同
    double b = 20;

    public static void main(String[] args) {
        GlobalVar globalVar = new GlobalVar();
        System.out.println("全局变量  a = " + globalVar.a);
        // System.out.println("全局变量  a = " + a); 错误写法
        globalVar.print();
        System.out.println("全局变量变化后  a = " + globalVar.a);
    }

    public void print() {
        System.out.println("在print()中, 全局变量　a = " + a + ", b = " + b);
        a = 30;
        System.out.println("在print()中, 全局变量　a = " + a + ", b = " + b);
    }
}
```

思考程序中的错误写法，简要描述你的看法。
a不是静态成员。无法访问a。
同时这里的a是全局变量的a，而不是globalVar的成员。