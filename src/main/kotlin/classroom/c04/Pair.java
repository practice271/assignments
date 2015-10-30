package classroom.c04;

public class Pair
        <A extends Comparable<? super A>, B extends Comparable<? super B>>
        implements Comparable<Pair<? extends A, ? extends B>> {
    private A first;
    private B second;

    public Pair(A first, B second) {
        this.first  = first;
        this.second = second;
    }

    public A getFirst() { return first;  }
    public void setFirst(A newFirst) { first = newFirst;  }

    public B getSecond() { return second; }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Pair)) { return false; }
        Pair otherPair = (Pair) other;
        return first.equals(otherPair.getFirst()) &&
                second.equals(otherPair.getSecond());
    }

    @Override
    public int hashCode() {
        return first.hashCode() * 31 + second.hashCode();
    }

   // @Override
    public int compareTo(Pair<? extends A, ? extends B> other) {
        double firstCompare = first.compareTo(other.first);
        if (firstCompare > 0) { return  1; }
        if (firstCompare < 0) { return -1; }

        double secondCompare = second.compareTo(other.second);
        if (secondCompare > 0) { return  1; }
        if (secondCompare < 0) { return -1; }
        return 0;
    }
}
