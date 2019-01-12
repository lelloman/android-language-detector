package com.lelloman.languagedetector

sealed class Language {
    override fun toString(): String = javaClass.simpleName
}

object DE : Language()
object EN : Language()
object ES : Language()
object FR : Language()
object IT : Language()
object NL : Language()
object PL : Language()
object PT : Language()
object RU : Language()
object SV : Language()
object UK : Language()
object VI : Language()

val Languages = arrayOf(
    DE,
    EN,
    ES,
    FR,
    IT,
    NL,
    PL,
    PT,
    RU,
    SV,
    UK,
    VI
)