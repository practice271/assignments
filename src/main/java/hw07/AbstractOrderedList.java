package hw07;


/**
 * Created by Antropov Igor on 31.10.2015.
 */



public abstract class AbstractOrderedList<E extends Comparable<? super E>>
            implements Comparable<AbstractOrderedList<? extends E>> {

    public boolean equals(Object other){
        if (!(other instanceof AbstractOrderedList)) { return false; }
        AbstractOrderedList otherList = (AbstractOrderedList) other;
        int size = size();
        if (otherList.size() != size) return false;
        for (int i = 0; i < size; i++ ){
            if (this.get(i) != otherList.get(i)) return false;
        }
        return true;
    }

    public int hashCode(){
        int res = 0;
        for (int i = 0; i < this.size(); i++){
            res = res * 31 + this.get(i).hashCode();
        }
        return res;
    }
    public int compareTo(AbstractOrderedList<? extends E> other){
        int size = size();
        int otherSize = other.size();
        if (size > otherSize) return 1;
            else if (size < otherSize) return -1;
        double Compare;
        for (int i = 0; i < size; i++){
            Compare = get(i).compareTo(other.get(i));
            if (Compare > 0) { return  1; }
            if (Compare < 0) { return -1; }
        }
        return 0;
    }

    public abstract int size();
    public abstract E get(int index);
    public abstract void add(E elem);
}
