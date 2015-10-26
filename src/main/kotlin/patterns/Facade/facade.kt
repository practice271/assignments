package patterns.Facade

/*
    Example of Facade pattern
    Sergeev Evgeny 17.10.2015
 */

/* PC Subsystem */

public class PowerSupplyUnit {
    public fun powerOn() = println("Power unit : Power on")
    public fun powerOff() = println("Power unit :Power off")
    public fun testVoltage() = println("Power unit : Test voltage")
}

public class CPU {
    public var registers = Array<Byte>(32, { 0 })
    public fun reset() = println("CPU        : Reset")
    public fun jump(position: Byte) = println("CPU        : Jump to $position")
    public fun execute() = println("CPU        : Execute")
}

public class DataBus() {
    private val capacity = 16
    private var data: Array<Byte> = Array<Byte>(capacity, { 0 })

    public fun read(): Array<Byte> {
        println("Data bus   : Get data")
        return data
    }

    public fun write(value: Array<Byte>) {
        println("Data bus   : Set data")
        for (i in 0..Math.min(value.size - 1, capacity - 1))
            data[i] = value[i]

    }
}

public class RAM {
    private var memory = Array<Byte>(640 * 1024, { 0 })         //ought to be enough for anybody
    public fun load(position: Byte, data: Array<Byte>) {
        println("RAM        : Load data at $position")
        for (i in 0..data.size - 1)
            memory[position + i] = data[i]
    }

    public fun upload(position: Byte, length: Byte): Array<Byte> {
        println("RAM        : Upload data from $position")
        var data = Array<Byte>(length.toInt(), { 0 })
        for (i in 0..data.size - 1)
            data[i] = memory[position + i]
        return data
    }
}

public class HardDrive {

    private var memory = Array<Byte>(3 * 1024 * 1024, { 0 })

    public fun read(position: Byte, size : Byte) : Array<Byte> {
        println("Hard drive : Upload data from $position")
        var data = Array<Byte>(size.toInt(), { 0 })
        for (i in 0..size - 1)
            data[i] = memory[position + i]
        return data
    }
}

/* Facade */

public class ComputerFacade {
    private val BOOT_ADDRESS : Byte = 0
    private val BOOT_SECTOR : Byte = 0
    private val SECTOR_SIZE : Byte = 16

    private val psu = PowerSupplyUnit()
    private val cpu = CPU()
    private val bus = DataBus()
    private val ram = RAM()
    private val hd = HardDrive()

    public fun start() {
        psu.testVoltage()
        psu.powerOn()
        cpu.reset()
        bus.write(hd.read(BOOT_SECTOR, SECTOR_SIZE))
        ram.load(BOOT_ADDRESS, bus.read())
        cpu.jump(BOOT_ADDRESS)
        cpu.execute()
    }

    public fun read(position : Byte, length : Byte) : Array<Byte> {
        bus.write(ram.upload(position, length))
        return bus.read()
    }

    public fun write(position : Byte, data : Array<Byte>) {
        bus.write(data)
        ram.load(position, bus.read())
    }
}

/*
fun main(args: Array<String>) {
    val pc = ComputerFacade()
    println("Computer starting : ")
    pc.start()
    println()

    val position : Byte = 8
    println("Write data in RAM at $position")
    pc.write(8, arrayOf<Byte>(1, 1, 1, 1))
    println()

    println("Read data from RAM at $position")
    val data = pc.read(8, 16)
    for (i in 0..data.size() - 1) print(data[i])
    println()
}
*/