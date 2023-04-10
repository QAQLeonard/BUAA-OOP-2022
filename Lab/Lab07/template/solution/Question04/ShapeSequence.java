//package Question04;
//
//import Question03.*;
//
//import java.util.ArrayList;
//
//public class ShapeSequence
//{
//    private ArrayList<Shape> shapes;
//    int MaxSize;
//
//    public SequenceIterator iterator()
//    {
//        return new SequenceIterator();
//    }
//
//    private class SequenceIterator
//    {
//        private int index;
//
//        public SequenceIterator()
//        {
//            index = 0;
//        }
//
//        public boolean isEnd()
//        {
//            return index >= shapes.size();
//        }
//
//        public Shape current()
//        {
//            if (!isEnd()) return shapes.get(index);
//            else return null;
//        }
//
//        public void moveNext()
//        {
//            if (!isEnd()) index++;
//        }
//
//        public boolean equals(Object o)
//        {
//            if (o instanceof SequenceIterator other)
//            {
//                return index == other.index;
//            }
//            return false;
//        }
//    }
//
//    public ShapeSequence(int size)
//    {
//        if (size <= 0) size = 0;
//        shapes = new ArrayList<>();
//        MaxSize = size;
//    }
//
//    public void add(Shape shape)
//    {
//        if(shapes.size() < MaxSize)
//            shapes.add(shape);
//    }
//
//    public String toString()
//    {
//        StringBuilder str = new StringBuilder("[");
//        for (int i = 0; i < shapes.size(); i++)
//        {
//            str.append(i == 0 ? "" : ", ").append(shapes.get(i).shapeType);
//        }
//        str.append("]");
//        return str.toString();
//    }
//
//    public static void main(String[] args)
//    {
//        ShapeSequence ss = new ShapeSequence(4);
//        Shape tmp = Rectangle.factory.makeShape(2, 3);
//        ss.add(tmp);
//        tmp = Rhombus.factory.makeShape(2, 3);
//        ss.add(tmp);
////        tmp = Ellipse.factory.makeShape(2, 3);
//        ss.add(tmp);
//        System.out.println(ss);
//        SequenceIterator Iterator = ss.iterator();
//        while (!Iterator.isEnd())
//        {
//            System.out.println(Iterator.current().shapeType);
//            Iterator.moveNext();
//        }
//        SequenceIterator Iterator2 = ss.iterator();
//        System.out.println(Iterator.equals(Iterator2));
//
//
//    }
//}
