package hw07;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class JavaATDOrderedList<A extends Comparable<A>> extends JavaOrderedList<A> {

    private class Cons<A extends Comparable<A>> {
        public A value;
        public Cons<A> next;

        Cons(A val, Cons nextCons) {
            value = val;
            next = nextCons;
        }
    }

    private Cons<A> list = null;

    private class ATDIterator<A extends Comparable<A>> implements Iterator<A> {
        private Cons<A> cons;

        public ATDIterator(Cons<A> list) {
            cons = list;
        }

        public boolean hasNext() {
            return (cons != null);
        }

        public A next() {
            if (cons == null) throw new NoSuchElementException();
            A val = cons.value;
            cons = cons.next;
            return val;
        }

        public void remove() {
        }
    }

    public Iterator<A> iterator() {
        return new ATDIterator<A>(list);
    }

    @Override
    public void add(A newElem) {
        length++;
        if (list == null) {
            list = new Cons<A>(newElem, null);
            return;
        }
        Cons<A> c = list;
        while (c.next != null && (c.value.compareTo(newElem) < 0))
            c = c.next;
        A min;
        A max;
        if (c.value.compareTo(newElem) < 0) {
            min = c.value;
            max = newElem;
        } else {
            min = newElem;
            max = c.value;
        }
        c.next = new Cons<A>(max, c.next);
        c.value = min;
    }

    @Override
    public A getByIndex(int index) {
        if (index < 0 || index >= length)
            return null;
        Cons<A> c = list;
        for (int i = 0; i < index; i++)
            c = c.next;
        return c.value;
    }

    @Override
    public boolean removeAt(int index) {
        if (index < 0 || index >= length)
            return false;
        Cons c = list;
        for (int i = 0; i < index; i++)
            c = c.next;
        c.value = c.next.value;
        c.next = c.next.next;
        length--;
        return true;
    }
}