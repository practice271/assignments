package hw07.javaClasses;

/* Abstract OrderedList made by Guzel Garifullina
   Estimated time  15 minutes
   real time       1 hour
*/

public abstract class AbstractOrderedList<E extends Comparable<? super E> >
        implements Comparable<AbstractOrderedList<? extends E>>{
    //@Override
    public boolean equals(AbstractOrderedList<E> other) {
        int size = size();
        if (size != other.size()){
            return false;
        }
        if (size == 0){
            return true;
        }
        for (int i = 0; i< size; i++){
            if (! get(i).equals(other.get(i)) ){
                return false;
            }
        }
        return true;
    }
    @Override
    public int hashCode() {
        int size = size();
        int sum = 0;
        for (int i = 0; i< size; i++){
            sum = 31 * sum + get(i).hashCode();
        }
        return sum;
    }
    //@Override
    public int compareTo(AbstractOrderedList<? extends E> other) {
        int size = size();
        if (size > other.size()){ return 1; }
        if (size < other.size()){ return -1; }

        for (int i = 0; i< size; i++){
            int comp = get(i).compareTo(other.get(i));
            if (comp != 0) { return comp;}
        }
        return 0;
    }
    public boolean isEmpty(){
        return size() == 0;
    }
    public  abstract int	size();
    public abstract void add( E e);
    public  abstract E	get(int index);
    public abstract void removeAt(int index);
    public abstract void clear();
}


