package patterns.Strategy

public fun firstHash(input : String) : String {
    val hasher : MD5  = MD5()
    return hasher.computeHash(input)
}

public fun badHash(input : String, type : String) : String {
    if (type == "MD5"){
        return MD5().computeHash(input)
    }
    else if (type == "SHA256") {
        return SHA256().computeHash(input)
    }
    else {
        return input // Or throw exception?
    }
}

//default method
public fun badHash(input : String) : String {
    return badHash(input, "MD5")
}
