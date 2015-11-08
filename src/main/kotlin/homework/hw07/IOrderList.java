package homework.hw07;

/**
 * Created by sharg on 10/30/2015.
 */
public abstract class IOrderList<T extends Comparable<? super T>> implements Comparable<IOrderList<? extends T>> {

    @Override
    public int hashCode() {
        int i = size() - 1;
        int answer = 0;
        while (i > 0) {
            answer = (answer + get(i).hashCode()) * 31;
            i--;
        }
        return Math.abs(answer);
    }

    @Override
    public boolean equals(Object other) {

        if (!(other instanceof IOrderList)) return false;

        IOrderList otherList = (IOrderList) other;

        if (size() != otherList.size()) return false;

        Boolean answer = true;
        int i = 0;
        while (i < size() && answer) {
            answer = get(i).equals(otherList.get(i));
            i++;
        }
        return answer;
    }

    @Override
    public int compareTo(IOrderList<? extends T> other) {
        if (size() > other.size()) return 1;
        if (size() < other.size()) return -1;

        int i = 0;
        while (i < size()) {
            int comprassion = get(i).compareTo(other.get(i));
            if (comprassion != 0) return comprassion;
            i++;
        }

        return 0;
    }

    public abstract void add(T val);

    public  abstract void remove(T val);

    public abstract int size();

    public abstract T get(int index);
}

