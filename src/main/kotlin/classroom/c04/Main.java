package classroom.c04;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Pair<Integer, Integer> pair1 = new Pair<Integer, Integer>(1, 2);
        Pair<Integer, Integer> pair2 = new Pair<Integer, Integer>(1, 2);
        System.out.println("pair1 == pair2:      " + (pair1 == pair2));
        System.out.println("pair1.equals(pair2): " + pair1.equals(pair2));

        HashMap<Pair<Integer, Integer>, Integer> hashMap =
                new HashMap<Pair<Integer, Integer>, Integer>();
        hashMap.put(pair1, 5);
        hashMap.put(pair1, 6);
        System.out.println("hashMap.size(): " + hashMap.size());
        hashMap.put(pair2, 7);
        System.out.println("hashMap.size(): " + hashMap.size());

        System.out.println("pair1.hashCode(): " + pair1.hashCode());
        System.out.println("pair2.hashCode(): " + pair2.hashCode());

        //System.out.println("pair1 <  pair2:      " + (pair1 < pair2));
        System.out.println("pair1.compareTo(pair2): " + pair1.compareTo(pair2));

        Pair<A1, A1> pair3 = new Pair<A1, A1>(new A1(), new A1());
        Pair<A2, A2> pair4 = new Pair<A2, A2>(new A2(), new A2());
        int res = pair3.compareTo(pair4);

        System.out.println(pair1);
    }
}
