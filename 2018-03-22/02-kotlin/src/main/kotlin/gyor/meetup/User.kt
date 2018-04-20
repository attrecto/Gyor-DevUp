package main.kotlin.gyor.meetup

data class User(val id: Int, val username: String, val email: String, val role: Role)

enum class Role {
    Admin,
    Regular
}

fun getSomeUsers() : List<User> {
    val users : ArrayList<User> = ArrayList()
    users.add(User(1, "Steve", "steve@attrecto.com", Role.Admin))
    users.add(User(2, "John", "john@attrecto.com", Role.Regular))
    users.add(User(3, "Mary", "mary@freemail.hu", Role.Regular))
    return users
}