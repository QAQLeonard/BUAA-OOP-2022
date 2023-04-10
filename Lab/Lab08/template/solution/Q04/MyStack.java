import java.util.LinkedList;

public class MyStack<T>
{
    private LinkedList<T> values = new LinkedList<T>();

    public void push(T t)
    {
        values.addFirst(t);
    }

    public T pull()
    {
        return values.removeFirst();
    }

    public T peek()
    {
        return values.getFirst();
    }

    public static void main(String[] args)
    {
        MyStack<String> stack = new MyStack<String>();
        stack.push("Hello");
        stack.push("World");
        System.out.println(stack.peek());
        System.out.println(stack.pull());
        System.out.println(stack.pull());
        MyStack<Integer> stack1 = new MyStack<Integer>();
        stack1.push(114514);
        stack1.push(1919810);
        System.out.println(stack1.peek());
        System.out.println(stack1.pull());
        System.out.println(stack1.pull());
    }
}
