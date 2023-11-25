package academy.jairo.ktor.adapter.repositoy.participante

import academy.jairo.ktor.domain.participante.Participante

interface ParticipanteRepository {

    fun findAll(): List<Participante>

}