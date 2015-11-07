package patterns.composite_example

interface FileSystem
{
    var name  : String
    fun Rename(name:String){}
    fun Add(type_ : FileSystem)
    fun Remove(name: FileSystem)

}


class File(name: String) : FileSystem
{
    override var name = name
    override  fun Rename(newName : String)
    {
        name = newName
    }
    override fun Add(name: FileSystem){}
    override fun Remove(name: FileSystem){throw Exception("Error")}
}



class Folder(name : String) : FileSystem {

    var list = Array<FileSystem>(0,{File("root")}).toLinkedList()
    override var name = name
    override  fun Rename(newName : String)
    {
        name = newName
    }
    override fun Add(name: FileSystem) {
        list.add(name)
    }
    override fun Remove(name: FileSystem) {
        list.remove(name)
    }
}


fun main(args : Array<String>)
{
    val x = Folder("Music")
    val y = File("AC/DC")
    x.Add(y)
    println(x.name)
    println("   ${x.list.first.name}")
}
