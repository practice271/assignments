package homeworks.hw07;

import java.util.Iterator;

public class JavaOrderedListATD<T extends Comparable<? super T>> extends JavaOrderedList<T>  {
    private int size = 0;
    public T first;
    public JavaOrderedListATD tail;

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        if (index == 0) {
            return first;
        }
        return getPosition(tail, 1, index);
    }

    private T getPosition(JavaOrderedListATD l, int i, int index) {
        if (i < index) {
            return getPosition(l.tail, i + 1, index);
        }
        return (T)l.first;
    }

    @Override
    public void add(T object) {
        if (size == 0) {
            first = object;
            size++;
            return;
        }
        if (object.compareTo(first) < 0) {
            JavaOrderedListATD<T> temp = new JavaOrderedListATD<T>();
            temp.first = first;
            temp.tail = tail;
            first = object;
            tail = temp;
            size++;
            return;
        }
        tail = addPosition(tail, object);
        size++;
    }

    private JavaOrderedListATD<T> addPosition(JavaOrderedListATD l, T obj) {
        JavaOrderedListATD<T> res = new JavaOrderedListATD<T>();
        if (l == null) {
            res.first = obj;
            return res;
        }
        if (l.first.compareTo(obj) >= 0) {
            res.first = obj;
            res.tail = l;
            return res;
        }
        res.first = (T)l.first;
        res.tail  = addPosition(l.tail, obj);
        return res;
    }

    @Override
    public void removeAt(int index) {
        if ((index < 0 || index >= size)) {
            return;
        }
        if (index == 0) {
            first = (T) tail.first;
            tail = tail.tail;
            size--;
            return;
        }
        tail = removePosition(tail, 1, index);
        size--;
    }

    private JavaOrderedListATD removePosition(JavaOrderedListATD l, int i, int index) {
        if (i < index) {
            JavaOrderedListATD<T> res = new JavaOrderedListATD<T>();
            res.first = (T)l.first;
            res.tail = removePosition(l.tail, i + 1, index);
            return res;
        }
        if (i == size - 1) {
            return null;
        }
        return l.tail;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private JavaOrderedListATD<T> list;

            public boolean hasNext() {
                return (list != null);
            }

            public T next() {
                if (hasNext()) {
                    T temp = list.first;
                    list = list.tail;
                    return temp;
                }
                return null;
            }

            public void remove() {}
        };
    }

}
