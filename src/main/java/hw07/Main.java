package hw07;

/**
 * Created by Antropov Igor on 31.10.2015.
 */

public class Main {
    public static void main(String[] args) {

        OrderedList<Character> test = new OrderedList<Character>('z');
        test.add('c');
        test.add('b');
        test.add('s');
        test.add('a');
        /* for (int i = 0; i < test.size(); i++) {
            System.out.println(test.get(i));
        } */
        OrderedList<Character> test1 = new OrderedList<Character>('z');
        System.out.println();
        test1.add('c');
        test1.add('b');
        test1.add('s');
        test1.add('a');
        System.out.println(test1.compareTo(test));
    }
}
