package hw07;

public abstract class OrderedList
        <A extends Comparable<? super A>>
        implements Comparable<OrderedList<? extends A>>{

    public abstract int size();
    public abstract A get(int index);
    public abstract void set(A value);

    public boolean equals(Object other) {
        if (!(other instanceof OrderedList)) { return false; }
        if (!(this.size() == ((OrderedList) other).size())) {
            return  false;
        }
        for (int i = 0; i < this.size(); i++) {
            if (!(this.get(i).equals(((OrderedList) other).get(i)))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int sum = 0;
        for (int i = 0; i < this.size(); i++) {
            sum = sum * 31 + this.get(i).hashCode();
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

