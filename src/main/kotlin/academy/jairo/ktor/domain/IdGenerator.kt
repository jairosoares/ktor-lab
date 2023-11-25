package academy.jairo.ktor.domain

import org.bson.types.ObjectId

object IdGenerator {
    fun generate(): ObjectId {
        return ObjectId()
    }
}