/* Implementation of the Singleton pattern
by Sokolova Polina */

package patterns.singleton

public object EarthAtmosphere {
    public fun layer(h : Double) : String {
        when {
            (h <   0) -> { return "Invalid height" }
            (h <  17) -> { return "Troposphere"    }
            (h <  55) -> { return "Stratosphere"   }
            (h <  80) -> { return "Mesosphere"     }
            (h < 800) -> { return "Thermosphere"   }
            else      -> { return "Exosphere"      }
        }
    }
    public fun temperature(h : Double) : Double {
        val T = 273.15
        when {
            (h > 145) -> { return 0.0 }
            (h >  94) -> { return T + 140 * (h - 94) / 51 - 90 }
            (h >  84) -> { return T - 90 }
            (h >  54) -> { return T - 3 * h + 162 }
            (h >  47) -> { return T }
            (h >  20) -> { return T + (20 * h - 940) / 9}
            (h >  10) -> { return T - 60 }
            (h >=  0) -> { return T -8 * h + 20 }
            else      -> { return T }
        }
    }
    public fun pressure(h : Double) : Double {
        val T = temperature(h)
        if (T <= 0.0) { return 0.0 }
        val R = 8.31447  //Gas constant
        val M = 0.028964 //Molar mass of air
        val p = 101325   //Standart air pressure
        val g = 9.8      //Gravitational acceleration
        val pressure = p * Math.exp(-M * g * h * 1000 / (R * T))
        return pressure
    }
    public fun density(h : Double) : Double {
        val T = temperature(h)
        if (T <= 0.0) { return 0.0 }
        val R = 8.31447
        val M = 0.0289644
        val density = pressure(h) * M / (R * T)
        return density
    }
}

/*fun main (args : Array<String>) {
    val h = 40.0
    val layer = EarthAtmosphere.layer(h)
    val temp  = Math.round(EarthAtmosphere.temperature(h)) - 273
    val press = Math.round(EarthAtmosphere.pressure(h))
    val dens  = EarthAtmosphere.density(h)
    println("Lay: $layer")
    println("Temperature ≈ $temp°С")
    println("Pressure ≈ $press Pa")
    println("Density = $dens kg/m^3")
}*/