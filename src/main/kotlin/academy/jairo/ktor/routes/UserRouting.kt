package academy.jairo.ktor.routes

import academy.jairo.ktor.adapter.repositoy.user.UsersRepository
import academy.jairo.ktor.domain.user.relational.UserDTO
import academy.jairo.ktor.domain.user.relational.UserTB
import academy.jairo.ktor.request.Response
import academy.jairo.ktor.service.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.Database

fun Application.configureUseRouting(database: Database) {

    val userRepository = UsersRepository(database)
    val userService = UserService(userRepository)

    routing {
        route("/users") {
            // Create
            post() {
                val userDTO = call.receive<UserDTO>()
                val user = userService.create(userDTO)
                call.respond(HttpStatusCode.Created,
                    Response.Success.Builder<UserTB>()
                        .data(user)
                        .build(true)
                )
            }
            // findById
            get("/{id}") {
                val id = call.getParameterId()
                val user = userService.findById(id)
                call.respond(HttpStatusCode.OK,
                    Response.Success.Builder<UserTB>()
                        .data(user)
                        .build()
                )
            }
            // Update
            put("/{id}") {
                val id = call.parameters["id"]?.toLong() ?: throw IllegalArgumentException("Invalid ID")
                val user = call.receive<UserDTO>()
                userService.update(id, user)
                call.respond(HttpStatusCode.OK,
                    Response.Success.Builder<UserTB>()
                        .message("Updated with success")
                        .build()
                )
            }
            // Delete
            delete("/{id}") {
                val id = call.parameters["id"]?.toLong() ?: throw IllegalArgumentException("Invalid ID")
                userService.delete(id)
                call.respond(HttpStatusCode.OK,
                    Response.Success.Builder<UserTB>()
                        .message("Deleted with success")
                        .build()
                )
            }
            // findAll
            get() {
                val peopleList = userService.findAll()
                call.respond(peopleList)
            }
        }
    }
}
