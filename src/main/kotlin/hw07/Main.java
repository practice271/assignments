package hw07;


public class Main {
    public static void main(String[] args) {
        /**ArrayOrdList<String> arr = new ArrayOrdList<String>(1, "zebra");
        System.out.println("Size = " + (arr.size()));
        arr.add("monkey");
        arr.add("panda");
        arr.add("mockingbird");
        arr.print();
        System.out.println("Size = " + (arr.size()));
        System.out.println("2 is " + (arr.get(2)));
        arr.remove(2);
        arr.print();
        System.out.println("hashcode arr:  " + arr.hashcode());*/

        ListOrdList<String> list = new ListOrdList("fish", null);
        System.out.println("Size = " + (list.size()));
        list.print();
        list.add(("panda"));
        list.print();
        System.out.println("1 is " + (list.get(1)));
        System.out.println("hashcode list:  " + list.hashcode());
        ListOrdList<String> list1 = new ListOrdList("dog", null);
        System.out.println("equals " + (list.equals(list1)));


    }
}
