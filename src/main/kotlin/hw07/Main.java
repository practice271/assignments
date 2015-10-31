package hw07;


public class Main {
    public static void main(String[] args) {
        ArrayOrdList<String> arr = new ArrayOrdList<>(1, "zebra");
        System.out.println("Size = " + (arr.size()));
        arr.add("monkey");
        arr.add("panda");
        arr.add("mockingbird");
        arr.print();
        System.out.println("Size = " + (arr.size()));
        System.out.println("2 is " + (arr.get(2)));
        arr.remove(2);
        arr.print();
        System.out.println("hashcode arr:  " + arr.hashcode());
    }
}
