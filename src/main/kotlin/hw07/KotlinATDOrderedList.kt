package hw07

data class KotlinATDOrderedList<A : Comparable<A>>(val arr : Array<A>) : IKotlinOrderedList<A> {

    private data class Cons<A : Comparable<A>>(public var value: A, public var next: Cons<A>?)

    public var leng = 0
        private set

    private var list: Cons<A>? = null

    init {
        leng = arr.size
        if (leng != 0) {
            list = Cons<A>(arr[0], null)
            var c = list
            for (i in 1..arr.size - 1) {
                c?.next = Cons<A>(arr[i], null)
                c = c?.next
            }
        }
    }

    override public fun getLength() = leng

    override public fun add(newElem: A) {
        leng++
        if (list == null) {
            list = Cons<A>(newElem, null);
            return;
        }
        var c: Cons<A>? = list
        while (c!!.next != null && (c.value.compareTo(newElem) < 0))
            c = c.next;
        val min: A
        val max: A
        if (c.value.compareTo(newElem) < 0) {
            min = c.value;
            max = newElem;
        } else {
            min = newElem;
            max = c.value;
        }
        c.next = Cons<A>(max, c.next);
        c.value = min;
    }

    override public fun getByIndex(index: Int): A? {
        if (index < 0 || index >= length)
            return null;
        var c: Cons<A>? = list
        for (i in 0..index - 1)
            c = c!!.next;
        return c!!.value;
    }

    override public fun removeAt(index: Int): Boolean {
        if (index < 0 || index >= length)
            return false;
        var c: Cons<A>? = list
        for (i in 0..index - 1)
            c = c!!.next;
        c!!.value = c.next!!.value;
        c.next = c.next!!.next;
        leng--;
        return true;
    }

    override public fun compareTo(other: IOrderedList<out A>): Int {
        val thisLength: Int = leng
        val otherLength: Int = other.getLength()
        val minLength: Int = Math.min(thisLength, otherLength)
        for (i in 0..minLength - 1) {
            val compare = (getByIndex(i) as A).compareTo(other.getByIndex(i));
            if (compare != 0) return compare;
        }
        if (thisLength > otherLength) return -1;
        if (otherLength > thisLength) return 1;
        return 0;
    }

    override public fun equals(other: Any?): Boolean {
        if (other !is IOrderedList<*>)
            return false;
        val otherList = other as IOrderedList<A>
        if (leng != otherList.getLength())
            return false;
        for (i in 0..leng - 1)
            if (!(getByIndex(i) as A).equals(otherList.getByIndex(i))) return false;
        return true;
    }

    override public fun hashCode(): Int {
        var hash: Int = 0
        for (i in 0..leng - 1)
            hash = hash * 31 + (getByIndex(i) as A).hashCode();
        return hash;
    }

    override public fun toArray(): Array<A> {
        var newArray = Array<Comparable<A>?>(leng, { null })
        for (index in 0..length - 1)
            newArray[index] = getByIndex(index) as A
        return newArray as Array<A>
    }
}