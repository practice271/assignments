package patterns.observer_example

import java.util.*


public class  BaggageInfo( private val flight : Int, private val from : String, private val carousel : Int) {

    public fun flightNumber(): Int = flight
    public fun from(): String = from
    public fun carousel(): Int = carousel
}

public class BaggageHandler() : Observable() {
    val observers = ArrayList<Observer>()
    val flights = ArrayList<BaggageInfo>()

    public fun Subscribe(observer: Observer) {

        if (!observers.contains(observer)) {
            observers.add(observer)
            for (item in flights)
                observer.update(this, item)
        }
    }


    public fun Unsubscribe(observer: Observer) {
       observers.remove(observer)
    }

    public fun BaggageStatus(flightNo: Int) {
        BaggageStatus(flightNo, "", 0)
    }

    public fun BaggageStatus(flightNo: Int, from: String, carousel: Int) {
        var info = BaggageInfo(flightNo, from, carousel)

        if (carousel > 0 && !flights.contains(info)) {

            flights.add(info)
            for (observer in observers)
                observer.update(this, info)
        } else if (carousel == 0) {

            var flightsToRemove = ArrayList<BaggageInfo>()

            for (flight in flights) {
                if (info.flightNumber() == flight.flightNumber()) {
                    flightsToRemove.add(flight)
                    for (observer in observers)
                        observer.update(this, info)
                }
            }
            for (flightToRemove in flightsToRemove)
                flights.remove(flightToRemove)

            flightsToRemove.clear()
        }
    }

    public fun LastBaggageClaimed() {
        observers.clear()
    }
}

public class  ArrivalsMonitor(private val name : String) : Observer {
    private val flightInfos = ArrayList<String>()


    public fun Subscribe(provider: BaggageHandler) {
        provider.Subscribe(this)
    }

    public fun Unsubscribe(provider: BaggageHandler) {
        provider.Unsubscribe(this)
    }

    // Update information.
    override fun update(o: Observable?, info: Any?) {
        var updated = false

        val info = info as BaggageInfo

        if (info.carousel() == 0) {
            var flightsToRemove = ArrayList<String>()
            val flightNo = info.flightNumber().toString()

            for (flightInfo in flightInfos) {
                if (flightInfo.substring(20..22).equals(flightNo)) {
                    flightsToRemove.add(flightInfo)
                    updated = true
                }
            }
            for (flightToRemove in flightsToRemove)
                flightInfos.remove(flightToRemove)

            flightsToRemove.clear()
        } else {
            var count = 20 - info.from().length
            var spaces = ""
            while (count > 0) {
                spaces += " "
                count--
            }
            val flightInfo = info.from() + spaces + info.flightNumber() + " " + info.carousel()
            if (!flightInfos.contains(flightInfo)) {
                flightInfos.add(flightInfo)
                updated = true
            }
        }
        if (updated) {
            flightInfos.toSortedSet()
            println("Arrivals information from ${name}")
            for (flightInfo in flightInfos)
                println(flightInfo)
            println()
        }
    }
}

public fun main(args : Array<String>) {
    val provider = BaggageHandler()
    val observer1 = ArrivalsMonitor("BaggageClaimMonitor1")
    val observer2 = ArrivalsMonitor("SecurityExit")
    provider.BaggageStatus(712, "Detroit", 3)
    observer1.Subscribe(provider)
    provider.BaggageStatus(712, "Kalamazoo", 3)
    provider.BaggageStatus(400, "New York-Kennedy", 1)
    provider.BaggageStatus(712, "Detroit", 3)
    observer2.Subscribe(provider)
    provider.BaggageStatus(511, "San Francisco", 2)
    provider.BaggageStatus(712)
    observer2.Unsubscribe(provider)
    provider.BaggageStatus(400)
    provider.LastBaggageClaimed()

}