package patterns.Facade

class Drive {
    fun turnLeft() = println("Turn left")
    fun turnRight() = println("Turn right")
}

class Notification  {
    fun stop() = println("Your food is very hot");
    fun start() = println("Cooking now")
}

class Power
{
    var power: Int = 0
        set(value) {
            field = value
            println("Power set to " + power.toString() + "W")
        }
}

class Microwave  {
    val drive = Drive();
    val power = Power();
    val notification = Notification();

    fun Defrost()  {
        notification.start();
        power.power = 1000;
        drive.turnRight();
        drive.turnRight();
        power.power = 500;
        drive.turnLeft();
        drive.turnLeft();
        power.power = 200;
        drive.turnRight();
        drive.turnRight();
        power.power = 0;
        notification.stop();
    }

    fun Heating() {
        notification.start();
        power.power = 350;
        drive.turnRight();
        drive.turnRight();
        drive.turnRight();
        drive.turnRight();
        drive.turnRight();
        drive.turnLeft();
        drive.turnLeft();
        drive.turnRight();
        drive.turnRight();
        drive.turnLeft();
        drive.turnLeft();
        drive.turnLeft();
        drive.turnLeft();
        power.power = 0;
        notification.stop();
    }
}

fun main(args: Array<String>) {
    val m = Microwave()
    m.Heating()
}