package hw07;

public class MyList <A extends Comparable<A>> implements IOrderedList<A>
{
    private class CList
    {
        public A head;
        public CList tail;

        public CList (A head, CList tail)
        {
            this.head = head;
            this.tail = tail;
        }
    }

    private CList list = null;
    private int lengthList = 0;

    private CList add (CList cList, A elem)
    {
        if (!(cList == null) && (elem.compareTo(cList.head) < 0)) return new CList(cList.head, add(cList.tail, elem));

        return new CList(elem, cList);
    }

    @Override
    public void push (A newElem)
    {
        lengthList++;
        list = add(list, newElem);
    }

    private CList deleteAt (CList cList, int idx)
    {
        if (idx == 0)
            if (cList == null) return null;
            else return cList.tail;
        return new CList(cList.head, deleteAt(cList.tail, idx - 1));
    }

    private CList delete (CList cList, A elem)
    {
        if (cList == null) return null;
        else if (cList.head.compareTo(elem) == 0)
        {
            lengthList--;
            return cList.tail;
        }

        return new CList(cList.head, delete(cList.tail, elem));
    }

    @Override
    public void removeAt (int index)
    {
        if (index < lengthList)
            list = deleteAt(list, index);
    }

    @Override
    public void remove (A elem)
    {
        list = delete(list, elem);
    }

    private A getElem (CList cList, int idx)
    {
        if (idx == 0)
            if (cList == null) return null;
            else return cList.head;
        return getElem(cList.tail, idx - 1);
    }

    @Override
    public A getAt (int index)
    {
        if (index >= lengthList) return null;
        return getElem(list, index );
    }

    @Override
    public int getLength()
    {
        return lengthList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        CList cList = list;

        while (cList != null)
        {
            hash += cList.head.hashCode() * 71;
            cList = cList.tail;
        }

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof IOrderedList && ((IOrderedList) obj).hashCode() == hashCode() && ((IOrderedList) obj).getLength() == getLength();
    }
}