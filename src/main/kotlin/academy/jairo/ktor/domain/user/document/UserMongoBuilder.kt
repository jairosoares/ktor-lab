package academy.jairo.ktor.domain.user.document

import academy.jairo.ktor.domain.IdGenerator
import org.bson.types.ObjectId

class UserMongoBuilder {

    private var id: ObjectId? = null
    private var name: String = ""
    private var age: Int = 0

    fun name(name: String): UserMongoBuilder {
        this.name = name
        return this
    }

    fun age(age: Int): UserMongoBuilder {
        this.age = age
        return this;
    }

    fun build(): UserMongo {
        val generatedId = id ?: IdGenerator.generate()
        return UserMongo(generatedId, name, age)
    }

}