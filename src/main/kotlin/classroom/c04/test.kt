package classroom.c04.kotlin

data class Pair<A: Comparable<A>, B: Comparable<B>>(
        public val first: A, public val second: B
): Comparable<Pair<out A, out B>> {
    override public fun compareTo(other: Pair<out A, out B>): Int {
        val firstCompare = first.compareTo(other.first);
        if (firstCompare > 0) { return  1; }
        if (firstCompare < 0) { return -1; }

        val secondCompare = second.compareTo(other.second);
        if (secondCompare > 0) { return  1; }
        if (secondCompare < 0) { return -1; }
        return 0;
    }
}
