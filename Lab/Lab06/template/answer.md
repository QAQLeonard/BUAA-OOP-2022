# Lab 06 Assignment

> 班级：212112
> 
> 学号：21373339
> 
> 姓名：周星达

## Question01
1) 
   >private f()
2) 
   >能通过，输出为private f()

## Question02
1) 
   >sup.field = 0, sup.getField() = 1
    sub.field = 1, sub.getField() = 1, sub.getSuperField() = 0
2) 
   >类的非静态属性能体现多态性

## Question03
1) 
   >Base staticGet()
    Derived dynamicGet()
2) 
   >类的静态属性和静态方法不能体现多态性

## Question04
1)
   >A() before draw()
    B.draw(), b = 0
    A() after draw()
    B(), b = 5
2)  
   >程序开始，创建类型为B的对象。B为A的子类，故先输出A()中的"A() before draw()"。B中覆盖了父类的draw()方法，故输出时调用的为B的draw()方法，输出"B.draw(), b = 0"。之后输出"A() after draw()"。之后会回到B的构造方法，输出"B(), b = 5"

## Question06
1)
   >1.接口中声明了方法为public,而实现接口时重写方法时没有声明方法为public属性。
   2.Test02同时实现了I0,I2两个接口，其中f()都无参数，一个无返回值，一个返回值为int，会有冲突。
2)  
   >I1不会,I2,I3会