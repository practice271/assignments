package hw07;

import java.util.*;

public abstract class OrderedList
        <A extends Comparable<? super A>>
        implements Comparable<OrderedList<? extends A>>, Iterable<A> {

    public abstract int size();
    public abstract A get(int index);
    public abstract void set(A value);



    public boolean equals(Iterable<A> other) {
        if (!(other instanceof OrderedList)) { return false; }
        if (!(this.size() == ((OrderedList) other).size())) {
            return  false;
        }

        Iterator thisIter = this.iterator();
        Iterator otherIter = other.iterator();
        while (thisIter.hasNext()){
            if (!otherIter.next().equals(thisIter.next())){
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        Iterator thisIter = this.iterator();
        int sum = 0;
        while(thisIter.hasNext()) {
            sum = sum * 31 + thisIter.next().hashCode();
        }
        return sum;
    }

    public int compareTo(OrderedList<? extends A> other) {
        if (this.size() != ((OrderedList) other).size()){
            if (this.size() < ((OrderedList) other).size()) { return -1;}
            else return 1;
        }
        for (int i = 0; i < this.size(); i++) {
            if ((this.get(i)).compareTo(other.get(i)) != 0){
                return (this.get(i)).compareTo(other.get(i));
            }
        }
        return 0;
    }
}