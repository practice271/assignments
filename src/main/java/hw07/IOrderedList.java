package hw07;

public interface IOrderedList<A>
{
    void push (A newElem);
    void removeAt (int index);
    void remove (A elem);
    A getAt (int index);
    int getLength();
}
