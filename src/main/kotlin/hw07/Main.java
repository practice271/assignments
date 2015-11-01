package hw07;

/**
 * Created by Mikhail on 01.11.2015.
 */
public class Main {
    public static void main(String args[]) {
        OrderedList<Integer> list = new OrderedList<Integer>();
        list.add(5);
        list.add(1);
        list.add(7);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        ArrayOrderedList<Integer> array = new ArrayOrderedList<Integer>();
        array.add(5);
        array.add(1);
        array.add(7);
        array.contains(5);
        for (int i = 0; i < array.size(); i++) {
            System.out.println(array.get(i));
        }
        if (array.contains(5)) {
            System.out.println("gj");
        }
        if (list.equals(array)) System.out.print("gj");
        System.out.println(array.hashCode());
    }
}

