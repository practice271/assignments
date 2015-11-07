package hw07;
import java.util.Iterator;



public abstract class OrdList <A extends Comparable<? super A>>
        implements Comparable<OrdList<? extends A>>,Iterable<A> {
    public abstract int size();
    public abstract A get(int index);
    public  abstract void print();
    public abstract void add(A elem);
    public  abstract void remove(int index);

    public int compareTo(OrdList<? extends A> other) {
        if (size() > other.size()) return 1;
        if (size() < other.size()) return -1;
        Iterator itr = iterator();
        Iterator itr_other = other.iterator();
        while (itr.hasNext()){
            int compare = ((A)itr.next()).compareTo((A)itr_other.next());
            if (compare != 0) return compare;
        }
        return 0;
    }

    public boolean equals(OrdList<A> other) {
        if (size() != other.size()) return false;
        Iterator itr = iterator();
        Iterator itr_other = other.iterator();
        while (itr.hasNext()) {
            if (!itr.next().equals(itr_other.next())) return false;
        }
        return true;
    }

    public int hashcode() {
        int hash = 0;
        for (int i = 0; i < size(); i++) {
            hash = get(i).hashCode() + hash * 31;
        }
        return hash;
    }
}



