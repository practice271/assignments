package hw07;

public interface Ordered<T> extends Iterable<T> {

    boolean equals(Ordered<T> x);
    int compareTo(Ordered<T> x);
    int hashCode();

}
