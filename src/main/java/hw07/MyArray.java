package hw07;

public class MyArray<A extends Comparable<A>>  implements IOrderedList<A>
{
    private int lengthArray = 2;
    private A[] arr = newArray();
    private int position = 0;

    public A[] newArray ()
    {
        return (A[]) new Comparable[lengthArray];
    }

    @Override
    public int getLength()
    {
        return position;
    }

    @Override
    public void push (A newElem)
    {
        A[] newArr;
        position++;

        if (position > lengthArray)
        {
            lengthArray += 2;
        }

        newArr = newArray();

        int i;
        for (i = 0; i < position - 1; i++)
            if (arr[i] == null || newElem.compareTo(arr[i]) > 0) break;

        if (i > 0)
            System.arraycopy(arr, 0, newArr, 0, i);

        newArr[i] = newElem;

        System.arraycopy(arr, i, newArr, i + 1, position - 1 - i);

        arr = newArr;
    }

    @Override
    public void removeAt (int index)
    {
        if (index >= position) return;

        position--;
        System.arraycopy(arr, index + 1, arr, index, lengthArray - 1 - index);
    }

    @Override
    public void remove (A elem)
    {
        int i;

        for (i = 0; i < position; i++)
            if (elem.compareTo(arr[i]) == 0) break;

        if (i == position) return;
        removeAt(i);
    }

    @Override
    public A getAt (int index) {
        if (index >= position) return null;
        return arr[index];
    }

    @Override
    public int hashCode()
    {
        int hash = 0;

        for (int i = 0; i < position; i++)
        {
            hash += arr[i].hashCode() * 71;
        }

        return hash;
    }

    @Override
    public boolean equals (Object obj)
    {
        return obj instanceof IOrderedList && ((IOrderedList) obj).hashCode() == hashCode() && ((IOrderedList) obj).getLength() == getLength();
    }
}
