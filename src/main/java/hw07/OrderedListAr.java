package hw07;

import java.util.Arrays;

public class OrderedListAr <A extends Comparable<? super A>>
        extends OrderedList<A> {

    private A[] vals;
    private int size;
    private int trueSize;
    private boolean isAscending;

    private final static int MIN_ARRAY_SIZE = 16;
    public int getMinArraySize() {
        return MIN_ARRAY_SIZE;
    }

    private void enlargeArray() {
        A[] newAr = (A[]) new Comparable[size * 2];
        System.arraycopy(vals, 0, newAr, 0, size);
        size *= 2;
        vals = newAr;
    }

    OrderedListAr (A[] array, boolean ifAscending){
        if (array == null) {
            trueSize = 0;
            return;
        }
        trueSize = array.length;
        size = array.length * 2;
        vals = (A[]) new Comparable[size];
        isAscending = ifAscending;
        Arrays.sort(array, this::isInOrderInt);
        System.arraycopy(array, 0, vals, 0, array.length);
    }

    private boolean isInOrder(A fst, A snd) {
        return ((fst.compareTo(snd) <= 0) == isAscending);
        /*ascending == ascending
        descending != ascending
        ascending != descending
        descending == descending
         */
    }

    private int isInOrderInt(A fst, A snd) {
        if (isInOrder(fst, snd))  return -1;
        return 1;
    }

    @Override
    public int getSize() {
        return trueSize;
    }

    @Override
    public A getVal(int index) {
        if (index >= trueSize || index < 0) return null;
        return vals[index];
    }

    private void moveR(int ind) {
        if (ind == size) {
            enlargeArray();

            vals[ind + 1] = vals[ind];
            vals[ind] = null;
            return;
        }
        if (vals[size - 1] == null) {//dubious
            System.arraycopy(vals, ind, vals, ind + 1, trueSize - ind);
            vals[ind] = null;
            return;
        }
        enlargeArray();

        System.arraycopy(vals, ind, vals, ind + 1, size / 2 - ind);
        vals[ind] = null;
    }

    private void moveL(int ind) {
        if (ind <= 0) return;
        System.arraycopy(vals, ind + 1, vals, ind, trueSize - 1 - ind);
        vals[trueSize - 1] = null;
    }

    private void addValInRange(A val, int l, int r) {
        int mid = (r - l) / 2 + l;
        if (r - l <= 1) {
            if (isInOrder(val, vals[mid])) {
                moveR(mid);//including mid
                vals[mid] = val;
                return;
            }
            moveR(mid + 1);
            vals[mid + 1] = val;
            return;
        }
        if (val.equals(vals[mid])) {
            moveR(mid);//logically, it'd be `mid + 1`, but it's possible it does not exist.
            vals[mid] = val;
            return;
        }
        if (isInOrder(val, vals[mid]))
            addValInRange(val, l, mid);
        else
            addValInRange(val, mid + 1, r);
    }

    @Override
    public void addVal(A val) {
        if (trueSize == 0) {
            size = MIN_ARRAY_SIZE;
            vals = (A[]) new Comparable[size];
            vals[0] = val;
            trueSize++;
            return;
        }
        addValInRange(val, 0, trueSize - 1);
        trueSize++;
    }

    @Override
    public void delVal(int index) {
        if (index > -1 && index < trueSize) {
            moveL(index);
            trueSize--;
        }
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        for (int i = 0; i < trueSize; i++) {
            A curVal = vals[i];
            hashCode = 31 * hashCode + (curVal == null ? 0 : curVal.hashCode());
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof OrderedList)) return false;

        OrderedList otherList = (OrderedList) other;
        boolean otherIsList = false;
        OrderedListATD.List otherATDcurVal = null;

        if (other instanceof OrderedListATD) {
            otherIsList = true;
            otherATDcurVal = ((OrderedListATD) otherList).getVals();
        }

        boolean res = true;
        if (trueSize != otherList.getSize()) return false;

        for (int i = 0; i < trueSize; i++) {
            A curThis = vals[i];
            Comparable curOther = null;
            if (otherIsList) {
                if (otherATDcurVal instanceof OrderedListATD.ListNotEmpty) {
                    OrderedListATD.ListNotEmpty atdGood = (OrderedListATD.ListNotEmpty) otherATDcurVal;
                    curOther       = atdGood.head;
                    otherATDcurVal = atdGood.tail;
                }
            }
            else {
                curOther = otherList.getVal(i);
            }
            if (curThis == null && curOther != null) return false;
            if (curThis != null && curOther == null) return false;
            if (!(curThis == null && curOther == null))
                res = res && curThis.equals(curOther);
            //if both are nulls, we shouldn't do anything.
        }
        return res;
    }

    @Override
    public int compareTo (OrderedList<? extends A> other) {
        int minSize = Math.min(trueSize, other.getSize());
        for (int i = 0; i < minSize; i++) {
            int curCompare = vals[i].compareTo(other.getVal(i));
            if (curCompare != 0) return curCompare;
        }
        if (trueSize < other.getSize()) return -1;
        if (trueSize > other.getSize()) return 1;
        return 0;
    }
}