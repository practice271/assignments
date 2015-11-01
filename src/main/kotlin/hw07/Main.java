package hw07;

import java.util.HashMap;
import hw07.OrdList;

public class Main {
    public static void main(String[] args) {
        OrdList<Integer> list = new OrdList<Integer>();
        list.set(1);
        list.set(3);
        list.set(2);
        list.set(88);
        list.set(21);
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.get(3));
        System.out.println(list.get(4));

        OrdArray<Integer> array = new OrdArray<Integer>();
        array.set(1);
        array.set(9);
        array.set(13);
        array.set(2);
        System.out.println(array.get(0));
        System.out.println(array.get(1));
        System.out.println(array.get(2));
        System.out.println(array.get(3));
    }
}

