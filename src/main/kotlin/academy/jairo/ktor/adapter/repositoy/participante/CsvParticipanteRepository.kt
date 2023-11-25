package academy.jairo.ktor.adapter.repositoy.participante

import academy.jairo.ktor.domain.participante.Participante
import java.io.BufferedReader
import java.io.FileReader

class CsvParticipanteRepository(fileName: String): ParticipanteRepository {

    private val listParticipante: MutableList<Participante> = mutableListOf()

    init {
        val bufferedReader = BufferedReader(FileReader(fileName))
        bufferedReader.useLines { lines ->
            lines.forEach { line ->
                val elements = line.split(",")
                listParticipante.add(formatFromCsv(elements[0], elements[1], elements[2]))
            }
        }
    }

    override fun findAll(): List<Participante> {
        return listParticipante
    }

    private fun formatFromCsv(name: String, email: String, scoreStr: String): Participante {
        val pontuacao = scoreStr.substring(0, scoreStr.indexOf('/')).trim().toInt()
        return Participante(toTitleCase(name), email, pontuacao)
    }

    companion object {
        fun toTitleCase(str: String): String {
            return str.split("\\s").joinToString(" ") { it.capitalize() }
        }
    }
}