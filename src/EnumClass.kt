fun main(args: Array<String>) {
//    println(Direction.NORTH)
//    println(Direction.SOUTH)
//    println(Direction.EAST)
//    println(Direction.WEST)

//    for (direction in Direction.values()) {
//        println(direction)
//    }

//    println(Direction.NORTH.direction)
//    println(Direction.NORTH.distane)
//    println(Direction.NORTH.name)
//
//    Direction.EAST.printData()

    val direction = Direction.EAST

    when(direction) {
        Direction.EAST -> println("The direction is EAST")
        Direction.WEST -> println("The direction is WEST")
        Direction.NORTH -> println("The direction is NORTH")
        Direction.SOUTH -> println("The direction is SOUTH")
    }

}

enum class Direction(var direction: String, var distane: Int) {
    NORTH("north", 10),
    SOUTH("south", 20),
    EAST("east", 15),
    WEST("west", 40);

    fun printData() {
        println("Direction = $direction and Distance = $distane")
    }
}