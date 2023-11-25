package academy.jairo.ktor.routes

import academy.jairo.ktor.adapter.repositoy.MongoDBAtlasDatabase
import academy.jairo.ktor.adapter.repositoy.user.UserMongoRepository
import academy.jairo.ktor.application.UserMongoService
import academy.jairo.ktor.domain.user.UserMongo
import academy.jairo.ktor.domain.user.UserMongoDTO
import academy.jairo.ktor.domain.user.toDTO
import academy.jairo.ktor.domain.user.toUser
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureUserMongoRouting() {

    val database = MongoDBAtlasDatabase()
    val userRepository = UserMongoRepository(database.create())
    val userMongoService = UserMongoService(userRepository)

    routing {
        route("/error") {
            get {
                val peopleList = userMongoService.findAllWithError()
                call.respond(peopleList)
            }
        }
        route("/user/findByName/{name}") {
            get {
                val name = call.parameters["name"] ?: return@get call.respond(HttpStatusCode.BadRequest)
                val peopleList = userMongoService.findByName(name)
                call.respond(peopleList)
            }
        }
        route("/user") {
            post {
                val user = call.receive<UserMongoDTO>()
                val createdUser = userMongoService.create(user.toUser())
                call.respond(HttpStatusCode.Created, createdUser.toDTO())
            }

            get("/{id}") {
                val userId = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
                val user = userMongoService.get(userId)
                if (user != null) {
                    call.respond(user)
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }

            put("/{id}") {
                val userId = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.BadRequest)
                val newUserMongo = call.receive<UserMongo>()
                val updated = userMongoService.update(userId, newUserMongo)
                if (updated) {
                    call.respond(HttpStatusCode.OK)
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }

            delete("/{id}") {
                val userId = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
                val deleted = userMongoService.delete(userId)
                if (deleted) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }

            get {
                val peopleList = userMongoService.findAll()
                call.respond(peopleList)
            }
        }
    }

}
