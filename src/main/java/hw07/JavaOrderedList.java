package hw07;

public abstract class JavaOrderedList<A extends Comparable<? super A>>
        implements Comparable<IOrderedList<? extends A>>, IOrderedList<A> {

    protected int length = 0;

    abstract public void add(A newElem);

    abstract public A getByIndex(int index);

    abstract public boolean removeAt(int index);

    public int getLength() {
        return length;
    }

    public int compareTo(IOrderedList<? extends A> other) {
        int thisLength = getLength();
        int otherLength = other.getLength();
        int minLength = Math.min(thisLength, otherLength);
        for (int i = 0; i < minLength; i++) {
            int compare = getByIndex(i).compareTo(other.getByIndex(i));
            if (compare != 0) return compare;
        }
        if (thisLength > otherLength) return -1;
        if (otherLength > thisLength) return 1;
        return 0;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof IOrderedList))
            return false;
        IOrderedList otherList = (IOrderedList) other;
        if (getLength() != otherList.getLength()) return false;
        for (int i = 0; i < getLength(); i++)
            if (!getByIndex(i).equals(otherList.getByIndex(i))) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < getLength(); i++)
            hash = hash * 31 + getByIndex(i).hashCode();
        return hash;
    }

    public A[] toArray() {
        A[] newArray = (A[]) new Comparable[getLength()];
        for (int index = 0; index < getLength(); index++)
            newArray[index] = getByIndex(index);
        return newArray;
    }
}