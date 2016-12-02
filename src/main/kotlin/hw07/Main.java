package hw07;

public class Main {
    public static void main(String[] args) {
        OrderedList<String> l = new LinkedOrderedList();
        l.add("bbb");
        l.add("aa");
        l.add("b");
        l.equals(l);
        String[] z = new String[l.size()];
        l.toArray(z);
        for (String s : l) {
            System.out.println(s);
        }
    }
}
