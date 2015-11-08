package homework.hw07;

/**
 * Created by sharg on 11/1/2015.
 */
public class main {
    public static void main(String[] args){
        ArrayOrderList<Integer> x = new ArrayOrderList();
        AtdOrderList<Integer> x2 = new AtdOrderList<>();
        x2.add(3);
        x2.add(5);
        x2.add(4);
        x2.print();
        x2.remove(4);
        x2.print();
    }
}
