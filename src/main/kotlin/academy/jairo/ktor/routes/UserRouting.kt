package academy.jairo.ktor.routes

import academy.jairo.ktor.adapter.repositoy.PostgreSQLDatabase
import academy.jairo.ktor.adapter.repositoy.user.UsersRepository
import academy.jairo.ktor.service.UserService
import academy.jairo.ktor.domain.user.relational.UserDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureUseRouting() {

    val database = PostgreSQLDatabase()
    val userRepository = UsersRepository(database.create())
    val userService = UserService(userRepository)

    routing {
        route("/users") {
            // Create
            post() {
                val user = call.receive<UserDTO>()
                val id = userService.create(user)
                call.respond(HttpStatusCode.Created, id)
            }
            // findById
            get("/{id}") {
                val id = call.getParameterId()
                val user = userService.findById(id)
                if (user != null) {
                    call.respond(HttpStatusCode.OK, user)
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }
            // Update
            put("/{id}") {
                val id = call.parameters["id"]?.toLong() ?: throw IllegalArgumentException("Invalid ID")
                val user = call.receive<UserDTO>()
                userService.update(id, user)
                call.respond(HttpStatusCode.OK)
            }
            // Delete
            delete("/{id}") {
                val id = call.parameters["id"]?.toLong() ?: throw IllegalArgumentException("Invalid ID")
                userService.delete(id)
                call.respond(HttpStatusCode.OK)
            }
            // findAll
            get() {
                val peopleList = userService.findAll()
                call.respond(peopleList)
            }
        }
    }
}
