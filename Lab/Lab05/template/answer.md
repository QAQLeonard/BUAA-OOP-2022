# Lab 05 Assignment

> 班级：212112
> 
> 学号：21373339
> 
> 姓名：周星达

## Question2
```
不可以。因为是private。所有构造方法都是private的类，不可以在该类外创建对象，也不能有子类。（编译器会报错：Implicit super constructor PrivateConstructor() is not visible for default constructor. Must define an explicit constructor.）

不能。 final是类型说明符，表示关闭继承，即final类不能有子类；但final类可能可以在类外创建对象（即final类的构造方法可以不是private型）：
在同一包中时，可以在任何另外一个类中使用public类与友好类创建对象;不在同一包中时（import语句），可以在任何另外一个类中使用public类创建对象。
```

## Question3
a.
```
4
4
5
foo() of Parent
foo() of Child
foo() of Child
bar() of Parent
bar() of Parent
bar() of Child

```
b.
>不能。'foo()' in 'Child' clashes with 'foo()' in 'Parent'; attempting to assign weaker access privileges ('package-private'); was 'protected'

c.
>不能。'bar()' in 'Child' clashes with 'bar()' in 'Parent'; attempting to assign weaker access privileges ('package-private'); was 'protected'

d.
>一致

e.
>静态属性、静态方法和非静态的属性都可以被隐藏，而不能够被重写。
非静态的方法可以被重写，而不能够被隐藏。