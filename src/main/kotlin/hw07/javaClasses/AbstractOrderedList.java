package hw07.javaClasses;

/* Abstract OrderedList made by Guzel Garifullina
   Estimated time  15 minutes
   real time       1 hour
*/

import java.util.Iterator;


public abstract class AbstractOrderedList<E extends Comparable<? super E> >
        implements Comparable<AbstractOrderedList<? extends E>>, Iterable< E> {
    public boolean equals(AbstractOrderedList<E> other) {
        int size = size();
        if (size != other.size()){
            return false;
        }
        if (size == 0){
            return true;
        }
        Iterator listIt = iterator();
        Iterator otherIt = other.iterator();
        while (listIt.hasNext()) {
            if (!listIt.next().equals(otherIt.next())) {
                return false;
            }
        }
        return true;
    }
    @Override
    public int hashCode() {
        int size = size();
        int sum = 0;
        for (E elem : this) {
            sum = 31 * sum + elem.hashCode();
        }
        return sum;
    }
    public int compareTo(AbstractOrderedList<? extends E> other) {
        int size = size();
        if (size > other.size()){ return 1; }
        if (size < other.size()){ return -1; }

        Iterator listIt = iterator();
        Iterator otherIt = other.iterator();
        while (listIt.hasNext()) {
            int comp = ((E)listIt.next()).compareTo((E)otherIt.next());
            if (comp != 0) { return comp;}
            }
        return 0;
    }
    public boolean isEmpty(){
        return size() == 0;
    }
    public  abstract int	size();
    public abstract void add( E e);
    public  abstract E	get(int index) throws OutOfBoundException;
    public abstract void removeAt(int index) throws OutOfBoundException;
    public abstract void clear();
}