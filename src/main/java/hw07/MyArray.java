package hw07;

import java.util.Iterator;

public class MyArray<A extends Comparable<A>>  implements IOrderedList<A>
{
    private int resizeConst = 2;
    private int lengthArray = 2;
    private A[] arr = newArray();
    private int index = 0;

    public A[] newArray ()
    {
        return (A[]) new Comparable[lengthArray];
    }

    @Override
    public int getLength()
    {
        return index;
    }

    @Override
    public void push (A newElem)
    {
        A[] newArr;
        index++;

        if (index > lengthArray)
        {
            lengthArray *= resizeConst;
        }

        newArr = newArray();

        int i;
        for (i = 0; i < index - 1; i++)
            if (arr[i] == null || newElem.compareTo(arr[i]) > 0) break;

        if (i > 0)
            System.arraycopy(arr, 0, newArr, 0, i);

        newArr[i] = newElem;

        System.arraycopy(arr, i, newArr, i + 1, index - 1 - i);

        arr = newArr;
    }

    @Override
    public void removeAt (int index)
    {
        if (index >= this.index) return;

        this.index--;
        System.arraycopy(arr, index + 1, arr, index, lengthArray - 1 - index);
    }

    @Override
    public void remove (A elem)
    {
        for (int i = 0; i < index; i++)
            if (elem.compareTo(arr[i]) == 0)
            {
                removeAt(i);
                return;
            }
    }

    @Override
    public A getAt (int index) {
        if (index >= this.index) return null;
        return arr[index];
    }

    @Override
    public int hashCode()
    {
        int hash = 0;

        for (int i = 0; i < index; i++)
        {
            hash += arr[i].hashCode() * 71;
        }

        return hash;
    }

    @Override
    public boolean equals (Object obj)
    {
        if (!(obj instanceof IOrderedList))
            return false;
        else
        {
            IOrderedList<A> otherArr = (IOrderedList) obj;

            if (otherArr.getLength() == getLength())
            {
                Iterator<A> iter = iterator();
                for (int i = 0; i < index; i++)
                {
                    if (iter.next() != otherArr.getAt(i)) return false;
                }

                return true;
            }

            else return false;
        }
    }

    @Override
    public Iterator<A> iterator() {
        return new Iterator<A>() {

            int idx = 0;

            @Override
            public boolean hasNext() {
                return (idx < lengthArray);
            }

            @Override
            public A next() {
                A elem = arr[idx];
                idx++;
                return elem;
            }

            @Override
            public void remove() {}
        };
    }
}
