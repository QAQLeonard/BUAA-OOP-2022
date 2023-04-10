# Lab 04 Assignment

> 班级：212112
> 
> 学号：21373339
> 
> 姓名：周星达

## Question1
```
initialize A1
initialize A2
initialize A3
initialize A4
initialize A5
initialize A6
copy from A6
initialize B1
initialize A8
main begins
initialize A9
initialize A6
copy from A6
initialize B2
initialize A8
main ends
```

## Question2
```
能。在输出main begins前就已经输出了其他的。
能。输出时A6先于A7输出。
```

## Question3
```
在调用方法的时候，首先会加载方法所在的这个类，会将这个类中所有静态域中的内容进行初始化(静态域包含静态基本类型变量和静态引用类型变量)
1.静态成员>非静态成员，并且按顺序初始化。
2.构造方法外的成员变量>构造方法内的成员变量
3.不能在方法中定义和初始化静态数据成员。
4.无论创建多少个对象，静态域中的变量只会被初始化一次。
```

## Question4
```java
class A {
    static int value;
    static A a1 = new A(1);
    public A(int i) {
        System.out.println("initialize A"+i);
        value = i;
    }

    public A(A a) {
        System.out.println("copy from A"+a.value);
        value = a.value;
    }
    static A a2 = new A(2);
}

class B {
    A a8;
    // A a7 = new A(a6);
    A a6 = new A(6);
    static A a3 = new A(3);
    static A a4;
    static {
        a4 = new A(4);
    }
    static A a5 = new A(5);

    public B(int i) {
        System.out.println("initialize B"+i);
        a8 = new A(8);
    }
    A a7 = new A(a6);
}

public class Initialization {
    static B b1 = new B(1);
    static B b2;
    public static void main(String[] args) {
        System.out.println("main begins");

        A.value=10;
        A a9 = new A(9);
        System.out.println(A.value);
        // b2 = new B(2);
        System.out.println("main ends");
    }
}
```

## Question5
```
不可以。因为是private。
```

## Question6
```
因为外部类无法调用构造方法。
只有开始时内部构造的uniqueInstance这一个实例
```

## Question7
```java
Singleton.getInstance().foo();
```