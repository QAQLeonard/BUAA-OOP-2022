# Java 程序设计 Lab03


## 实验目的

- 理解并掌握类的概念
- 理解并掌握对象的概念
- 理解类与对象的关系
- 理解面向对象中抽象过程
- 理解面向对象中的消息
- 理解 Java 程序的基本结构并能灵活使用
- 理解并掌握 Java 类的定义（成员变量、成员方法和方法重载）
- 理解并掌握 Java 类的构造函数（默认构造函数、带参数构造函数），理解重载的构造函数并灵活使用
- 理解 Java 垃圾内存自动回收机制
- 理解并掌握 Java 类变量和类方法

## 实验题目

### Question1: 简答题

编译下面这个类：

```java
public class Sample {
    int x; // 1
    long y = x; // 2
    public void f(int n) {
        int m; // 3
        int t = n + m; // 4
    }
    public static void main(String[] args) {
        Sample t=new Sample();
        t.f(5);
        System.out.println(t.x);
    }
}
```

注释标记的哪些行会导致编译错误，为什么?

### Question2: 简答题 overload

阅读下面代码：

```java
public class Overload{
    Overload(int m){}
    Overload(double  m){}
    int Overload(int m){return 23;}
    void Overload(double m){}
}
```

对于`Overload`类，下面哪些叙述是错误的，为什么错？

- a. `Overload(int m)`与`Overload(double m)`互为重载的构造方法。
- b. `int Overload(int m)`与`void Overload(double m)`互为重载的非构造方法。
- c. `Overload`类有 2 个构造方法，尝试调用默认构造方法`Overload()`会无法通过编译。
- d. `Overload`类有 3 个构造方法。

### Question3: 编程题 overload2

- 创建一个Dog类，不编写构造方法，在`main()`中new一个Dog实例，验证编译器是否自动添加了无参构造方法。
- 在上面的Dog类的基础上，编写一个构造方法`Dog(int age)`，在其中输出狗有age岁的信息，并测试此时能否调用无参的构造方法。
- 在上面的Dog类的基础上，编写一个构造方法`Dog(int age, String name)`，输出狗的姓名为name的信息，并在该构造方法中调用另一个构造方法`this(age)`，避免代码复用。
- 在上面的Dog类的基础上，编写重载的`bark()`方法，此方法根据类型进行重载，根据被调用的版本，打印出不同类型的狗吠（barking），咆哮（howling）等信息，调用所有不同版本的方法进行测试。
- 在上面的Dog类的基础上，再编写两个重载的`bark()`方法，各自接受两个不同类型的参数，只是两个`bark()`的参数接受顺序相反，在`main()`中验证是否工作。

> 你需要在 answer.md 中说明如下内容

```java
目录名为：Question3
文件名有：(如果你放了一个项目进来，则说明你的项目结构以及入口位置)
```



### Question4: 简答题 overload3

阅读下面这段代码：

```java
public class Overload2 {
    int a,b;
    int c=-1;
    public Overload2(){a=-2;}
    public Overload2(int p1,int p2){a=p1;  b=p2;}
    public Overload2(int p1,int p2,int p3){
      this(p1,p2);
      c=p3;
    }

    @Override
    public String toString(){
      return String.format("(a,b,c)=(%d,%d,%d)",a,b,c);
    }
    public static void main(String args[]){
      Overload2 s = new Overload2();
      System.out.println(s);
      System.out.println(new Overload2(5,6));
      System.out.println((new Overload2(2,3,4)).toString());
    }
  }

```

运行`java Overload2`，程序的输出是什么？

**题外话：**

根据`void java.io.PrintStream.print()`系列函数的实现，当传入的 String 或 Object 是 null 时，最后都会导致输出字符串”null”，而不是什么都不输出。

进行字符串连接时也一样：`" " + null` 的结果是 `"null"`。

因此当涉及相关操作时需要稍微注意一下。

具体原因参见`java.io.PrintStream`、`java.lang.String`的源代码。

### Question5: 简答题 finalize

阅读下面这段代码：

```java
public class User {
    public static int userCount = 0;
    public User() {
        ++userCount;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("bye");
        --userCount;
    }
    public static void main(String[] args) {
        System.out.println(User.userCount); // 1
        User u = new User();
        System.out.println(User.userCount); // 2
        u = null;
        System.out.println(User.userCount); // 3
        System.gc();
        System.out.println(User.userCount); // 4
    }
}
```

注意代码中用注释标出的四行`System.out.println(User.userCount)`，某些行的输出结果有多种可能。

运行`java User`，程序的输出可能是什么？

> 你需要在 answer.md 中说明如下内容

| 注释标记行 | 可能的输出内容 |
| :--------: | :------------: |
|     1      |       0        |
|     2      |       1        |
|     3      |       1        |
|     4      |      1,0       |

有什么办法可以让`finalize()`在`main()`结束前一定被调用吗？

**题外话：**

和 C++不同，在 Java 中显式重写 `finalize()` 通常是不必要的。

`java.lang.System.gc()`只会在需要的时候执行垃圾收集，你的调用只是一个“建议”。

### Question6: 编程题

写一个音乐类，并编写测试类显示音乐信息（音乐名称、音乐类型）。

写一个手机类，并编写测试类显示手机信息（手机品牌、手机型号）

> 你需要在 answer.md 中说明如下内容

```java
目录名为：Question6
文件名有：(如果你放了一个项目进来，则说明你的项目结构以及入口位置)
```

**唠叨两句：**

手机类和音乐类都需要完成 “显示信息” 这样的功能，那我们能否将这一功能封装起来实现呢？

比如我们可以让手机类和音乐类都实现一个 `IMessage` 接口， 他们就有了统一的测试方法：

```java
class Test {
    public static void main(String args[]) {
        IMessage music = new Music();
        IMessage mobile = new Mobile();
        music.print()
        mobile.print()
    }
}
```

当然，在这个题中，使用这样的实现方式未免有点 “过度封装”。 

如果之后完成个人 / 团队大作业时遇到了类似的场景，不妨在想一想能否应用上述的模式进行设计。

### Question7: 编程题

编写程序,在其中定义两个类：

- Person 类：
  - 属性有 name、age 和 sex
  - 提供你认为必要的构造方法
  - 方法 `setAge()` 设置人的合法年龄 (0~130)
  - 方法 `getAge()` 返回人的年龄
  - 方法 `work()` 输出字符串“working”
  - 方法 `showAge()` 输出 age 值
- TestPerson 类：
  - 创建 Person 类的对象，设置该对象的 name、age 和 sex 属性
  - 调用 `setAge()` 和 `getAge()` 方法，体会 Java 的封装性。
  - 创建第二个对象，执行上述操作，体会同一个类的不同对象之间的关系。

> 你需要在 answer.md 中说明如下内容

```java
目录名为：Question7
文件名有：(如果你放了一个项目进来，则说明你的项目结构以及入口位置)
```

### Question8: 编程题

编写一个 Java 命令行程序，**只从标准输入读取一行用户输入**，判断这行输入是否是一个没有前导 0 的无符号整数；如果是，则还要判断该数字是否是一个回文数。输出你的判断和执行结果。

对于“没有前导 0 的无符号整数”的定义：

- 是一个字符串 s
- s 的长度至少是 1，没有上限要求
- s 的字符集 Σ={0, 1, 2, 3, 4, 5, 6, 7, 8, 9}，其他所有字符都不应该出现在 s 中
- 当 s 的长度大于 1 时，若从其首部开始有若干个连续字符 0，那么这些字符 0 都叫做 s
  的“前导 0”
  - 比如数字串 00010020，有三个前导 0
  - 比如数字串 01，有 1 个前导 0
  - 比如数字串 102030，没有前导 0
  - 比如数字串 0，没有前导 0

本题对于回文数的定义：

- 是一个字符串 s
- s 的长度至少是 1，没有上限要求
- s 的字符集 Σ={0, 1, 2, 3, 4, 5, 6, 7, 8, 9}，其他所有字符都不应该出现在 s 中
- 将 s 中的字符逆序排列并去除前导 0 得到的数字串 r，有 s 和 r 完全相同
  - s=123 时，r=321，不相同，s 和 r 都不是回文数
  - s=12321 时，r=12321，相同，s 是回文数
  - s=12100 时，r=121，不相同，s 不是回文数，但 r 是回文数
  - s=1 时，r=1，相同，s 是回文数
  - s=0 时，r=0，相同，s 是回文数

本题将输入的一行字符视为一个完整的字符串，如果输入的是诸如“121 121”这样包含空格的串，虽然 121 是回文数，但是整个串不应该被认为是回文数。如果将字符集扩充为包含空格的其他字符集，那么“121 121”就是一个该字符集下的回文串，不过本题的字符集限定为由 0~9 这十个数字组成的字符集。

> 你需要在 answer.md 中说明如下内容

```java
目录名为：Question8
文件名有：(如果你放了一个项目进来，则说明你的项目结构以及入口位置)
```