package hw07;

/**
 * Created by Jinx on 31.10.2015.
 */

public class Main {
    public static void main(String[] args) {
        OrderedList<Character> test = new OrderedList<Character>('z');
        test.add('c');
        test.add('b');
        test.add('s');
        test.add('a');
        for (int i = 0; i < test.size(); i++) {
            System.out.println(test.get(i));
        }
        /**   OrderedArray<Character> test = new OrderedArray<Character>(1,'z');
         System.out.println();
         test.add('c');
         test.add('b');
         test.add('s');
         test.add('a');
         OrderedArray<Character> test1 = new OrderedArray<Character>(1,'b');
         System.out.println();
         test1.add('a');
         test1.add('c');
         test1.add('z');
         test1.add('s');
         for (int i = 0; i < test.size(); i++){
         System.out.println(test.get(i).toString() + "   " + test1.get(i).toString());
         }
         System.out.println(test.compareTo(test1));
         System.out.println(test.hashCode());
         System.out.println(test1.hashCode());
         */
    }
}
