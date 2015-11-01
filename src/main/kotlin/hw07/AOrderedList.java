package hw07;

/**
 * Created by Mikhail on 01.11.2015.
 */
public abstract class AOrderedList<A extends Comparable<? super A>>
                implements Comparable<AOrderedList<? extends A>> {
    public abstract A get(int index);
    public abstract void add(A item);
    public abstract int size();
    public abstract boolean contains(A item);

    @Override
    public int compareTo(AOrderedList<? extends A> other) {
        if (size() > other.size()) return 1;
        if (size() < other.size()) return -1;

        int i = 0;
        while (i < size()){
            int compare = get(i).compareTo(other.get(i));
            if (compare != 0) return compare;
            i++;
        }
        return 0;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof AOrderedList)) return false;
        AOrderedList otherOrderedList = (AOrderedList) other;
        if (size() != otherOrderedList.size()) return false;
        for (int i = 0; i < size() - 1; i++) {
            if (get(i) != otherOrderedList.get(i)) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int res = 0;
        for (int i = 0; i < size(); i++) {
            res = res * 23 + get(i).hashCode();
        }
        return res;
    }
}
