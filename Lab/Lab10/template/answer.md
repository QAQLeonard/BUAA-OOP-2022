# Lab 10 Assignment

> 班级：212112
>
> 学号：21373339
>
> 姓名：周星达

### Question1 如果准备按字节读取一个文件的内容，应当使用 `FileInputStream` 流还是 `FileReader` 流，为什么？ `简答`

```
使用FileInputStream流，因为前者是以字节为单位的输出流，后者是以字符为单位的输出流
```

### Question2  `简答`

```java
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileInputStreamTest {
    public static void main(String[] args) {
        File f =new File("hello.txt");
        byte [] a="abcd".getBytes();
        try{
            FileOutputStream out=new FileOutputStream(f);
            out.write(a);
            out.close();
            FileInputStream in=new FileInputStream(f);
            byte [] tom= new byte[3];
            //Part Ⅰ
            int m = in.read(tom,0,3);
            System.out.println(m);//3
            String s=new String(tom,0,3);
            System.out.println(s);//abc
            //Part Ⅱ
            m = in.read(tom,0,3);
            System.out.println(m);//1
            s=new String(tom,0,3);
            System.out.println(s);//dbc  
        }
        catch(IOException e) {}
    }
}
```

(1) 请写出程序的输出

```
3
abc
1
dbc
```

(2) 解释 `Part Ⅰ` 和 `Part Ⅱ` 的输出为什么不同

```
byte[]tom的长度为3，第一次从hello.txt中读出三个字符abc保存在tom中，输出字节数m为3，第二次因为没有关闭输入流，故从上次结束的位置从hello.txt中读出，只读出一个字符d保存在tom的第一位，第一次读出的第二和第三位的b和c并未被清除，故读出字节数为1，tom中的内容为dbc
```
