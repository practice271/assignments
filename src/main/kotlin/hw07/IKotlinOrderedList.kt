package hw07

public interface IKotlinOrderedList<A : Comparable<in A>> : Comparable<IOrderedList<out A>>, IOrderedList<A> {

    override public fun add(newElem: A)

    override public fun getByIndex(index: Int): A?

    override public fun removeAt(index: Int): Boolean

    override public fun compareTo(other: IOrderedList<out A>): Int

    override public fun toArray(): Array<A>
}