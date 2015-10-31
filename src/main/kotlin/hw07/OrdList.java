package hw07;

public abstract class OrdList <A extends Comparable<? super A>>
        implements Comparable<OrdList<? extends A>> {
    public abstract int size();
    public abstract A get(int index);
    public  abstract void print();
    public abstract void add(A elem);
    public  abstract void remove(int index);

    public int compareTo(OrdList<? extends A> other) {
        if (size() > other.size()) return 1;
        if (size() < other.size()) return -1;
        for (int i = 0; i < size(); i++) {
            int compare = get(i).compareTo(other.get(i));
            if (compare != 0) return compare;
        }
        return 0;
    }

    public boolean equals(OrdList<A> other) {
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++) {
            if (!get(i).equals(other.get(i))) return false;
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



