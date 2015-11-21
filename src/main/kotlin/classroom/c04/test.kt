package classroom.c04.kotlin

data class Pair<A: Comparable<A>, B: Comparable<B>>(
        public val first: A, public val second: B
): Comparable<Pair<A, B>> {
    override public fun compareTo(other: Pair<A, B>): Int {
        val firstCompare = first.compareTo(other.first);
        if (firstCompare != 0) { return firstCompare; }
        return second.compareTo(other.second);
    }
}
