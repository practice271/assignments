/**
 * Created by Rinat on 08/12/15.
 */
class Dad(kitchen: Kitchen) {
    var kitchen = kitchen
    fun getBeer():Boolean
    {
        if (!kitchen.tryToGetBeer())
        {
            println("Daddy: Who the hell drank all my beer?");
            return false;
        }

        println("Daddy: Yeeah! My beer!");
        kitchen.oneBeerHasGone();
        return true;
    }
    fun argue_back()
    {
        println("Daddy: it's my last beer, for shure!")
    }

}

class Mum (kitchen: Kitchen){
    var kitchen = kitchen

    fun argue()
    {
            println("Mammy: You are f*king alconaut!");
            kitchen.disputeStarted();
    }
}

class BeerStorage(var beerCount: Int = 0)
{
    fun takeOneBeerAway() : Boolean
    {
        if (beerCount == 0) return false;
        this.beerCount--;
        return true;
    }
}

class Kitchen(){

    var refrigerator = BeerStorage(2)
    var minibar = BeerStorage(3)
    var mum = Mum(this)
    var dad = Dad(this)

    fun tryToGetBeer() : Boolean
    {
        if (this.refrigerator.takeOneBeerAway()) return true;
        if (this.minibar.takeOneBeerAway()) return true;

        return false
    }
    fun oneBeerHasGone()
    {
        this.mum.argue();
    }

    fun disputeStarted()
    {
        this.dad.argue_back();
    }
}

fun main(args: Array<String>) {
    var round_counter = 0;
    var kitchen = Kitchen()
    while (kitchen.dad.getBeer())
    {
        round_counter++
        println(" ${round_counter} round passed");
    }
}

