fun main(args: Array<String>) {
    val user1 = User("Adam", "Doe", 23)
    val user2 by lazy {
        User("User1", "lastName", 0)
    }

    println(user2.firstName)
}

