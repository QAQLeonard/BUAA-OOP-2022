# Lab 09 Assignment

> 班级：212112
>
> 学号：21373339
>
> 姓名：周星达

### Question01

```
检查型异常是编译器要求必须要处置的异常，如果不处理则无法通过编译。非检查型异常则是编译器不要求强制处置的异常，如NullPointerException，IndexOutOfBoundsException，VirtualMachineError等
```

## Question2

```
程序员可以通过throw关键字是主动抛出某种特定类型的异常；
而throws关键字则是用于声明可能抛出的异常，并把它交给上层调用它的程序处理
```

## Question3

```
运行时异常：NullPointerException ArrayIndexOutOfBoundsException 
非运行时异常：IOException ClassNotFoundException
```

## Question4

```
RuntimeExcception包含在Exception里，故程序永远不会进行到第二个catch括号中的内容
```

```java
import java.io.IOException;

public class p04 {
    public static void start() throws IOException,RuntimeException
{
        throw new RuntimeException("Unable to Start");
    }

    public static void main(String[] args){
        try{
            start();
        }catch (RuntimeException re){
            re.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
```

## Question5

```
1.子类抛出异常的范围大于父类抛出异常的范围。
2.子类open函数没有处理异常。
```

```java
import java.io.IOException;
import java.io.FileInputStream;
public class SuperClass {
    public void start() throws IOException{
        throw new IOException("Unable to start");
    }
}

//SubClass.java


public class SubClass extends SuperClass {
    public void start() throws IOException{
        throw new IOException("Unable to open file");
    }
    public void open(String fileName)throws IOException{
        FileInputStream fis=new FileInputStream(fileName);
    }
}
```

## Question6

```
methodA抛出一个异常！
执行methodA的finally!
methodB执行！
执行methodB的finally!
```

## Question7

```
-----------------------
error
i in finally block:2
1
-----------------------
error
i in finally:okfinally
ok
-----------------------
error
i in finally:okfinally
okfinally
-----------------------
```

```
get1()和get2()中，先在try中抛出异常，被catch捕捉并执行catch中的代码。执行到return语句时，i的值并未被改变，然后在返回之前执行finally中的语句，打印出相关语句后返回，最后打印出i的原始值。
get3()中的i为可变字符串序列，在运行完finally后被返回的i的内容被改变，故打印出改变后的i值
```
