package Homework

fun Array<Int>.HeapSort ()
{
    var i = this.count() / 2

    while (i >= 0)
    {
        Shift(i, this.count())
        i--
    }

    i = this.count() - 1

    while (i > 0)
    {
        Swap(0, i)
        Shift(0, i)
        i--
    }
}

fun Array<Int>.Swap (fir : Int, sec : Int)
{
    val temp = this[fir]
    this[fir] = this[sec]
    this[sec] = temp
}

fun Array<Int>.Shift (beg : Int, end : Int)
{
    var check = false
    var maxInt : Int
    var tBeg = beg

    while ((tBeg * 2 + 1 < end) && (!check))
    {
        if ((tBeg * 2 + 1 == end - 1) || (this[tBeg * 2 + 1] > this[tBeg * 2 + 2]))
            maxInt = tBeg * 2 + 1
        else
            maxInt = tBeg * 2 + 2

        if (this[tBeg] < this[maxInt])
        {
            this.Swap(tBeg, maxInt)
            tBeg = maxInt
        }
        else
        {
            check = true
        }
    }
}