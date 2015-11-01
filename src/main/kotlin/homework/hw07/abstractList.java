package homework.hw07;

public abstract class abstractList<T> {
    protected abstract void add(T elem);
    protected abstract T get(int index);
    protected abstract boolean isEmpty();
    protected abstract int size();
    protected abstract void removeAt(int index);

}
