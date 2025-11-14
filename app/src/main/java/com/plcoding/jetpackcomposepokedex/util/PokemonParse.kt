package com.plcoding.jetpackcomposepokedex.util

import androidx.compose.ui.graphics.Color
import com.plcoding.jetpackcomposepokedex.data.remote.responses.Stat
import com.plcoding.jetpackcomposepokedex.data.remote.responses.Type
import com.plcoding.jetpackcomposepokedex.ui.theme.AtkColor
import com.plcoding.jetpackcomposepokedex.ui.theme.DefColor
import com.plcoding.jetpackcomposepokedex.ui.theme.HPColor
import com.plcoding.jetpackcomposepokedex.ui.theme.SpAtkColor
import com.plcoding.jetpackcomposepokedex.ui.theme.SpDefColor
import com.plcoding.jetpackcomposepokedex.ui.theme.SpdColor
import com.plcoding.jetpackcomposepokedex.ui.theme.TypeBug
import com.plcoding.jetpackcomposepokedex.ui.theme.TypeDark
import com.plcoding.jetpackcomposepokedex.ui.theme.TypeDragon
import com.plcoding.jetpackcomposepokedex.ui.theme.TypeElectric
import com.plcoding.jetpackcomposepokedex.ui.theme.TypeFairy
import com.plcoding.jetpackcomposepokedex.ui.theme.TypeFighting
import com.plcoding.jetpackcomposepokedex.ui.theme.TypeFire
import com.plcoding.jetpackcomposepokedex.ui.theme.TypeFlying
import com.plcoding.jetpackcomposepokedex.ui.theme.TypeGhost
import com.plcoding.jetpackcomposepokedex.ui.theme.TypeGrass
import com.plcoding.jetpackcomposepokedex.ui.theme.TypeGround
import com.plcoding.jetpackcomposepokedex.ui.theme.TypeIce
import com.plcoding.jetpackcomposepokedex.ui.theme.TypeNormal
import com.plcoding.jetpackcomposepokedex.ui.theme.TypePoison
import com.plcoding.jetpackcomposepokedex.ui.theme.TypePsychic
import com.plcoding.jetpackcomposepokedex.ui.theme.TypeRock
import com.plcoding.jetpackcomposepokedex.ui.theme.TypeSteel
import com.plcoding.jetpackcomposepokedex.ui.theme.TypeWater
import java.util.Locale

fun parseTypeToColor(type: Type): Color {
    return when(type.type.name.lowercase(Locale.ROOT)) {
        "normal" -> TypeNormal
        "fire" -> TypeFire
        "water" -> TypeWater
        "electric" -> TypeElectric
        "grass" -> TypeGrass
        "ice" -> TypeIce
        "fighting" -> TypeFighting
        "poison" -> TypePoison
        "ground" -> TypeGround
        "flying" -> TypeFlying
        "psychic" -> TypePsychic
        "bug" -> TypeBug
        "rock" -> TypeRock
        "ghost" -> TypeGhost
        "dragon" -> TypeDragon
        "dark" -> TypeDark
        "steel" -> TypeSteel
        "fairy" -> TypeFairy
        else -> Color.Black
    }
}

fun parseStatToColor(stat: Stat): Color {
    return when(stat.stat.name.lowercase(Locale.ROOT)) {
        "hp" -> HPColor
        "attack" -> AtkColor
        "defense" -> DefColor
        "special-attack" -> SpAtkColor
        "special-defense" -> SpDefColor
        "speed" -> SpdColor
        else -> Color.White
    }
}

fun parseStatToAbbr(stat: Stat): String {
    return when(stat.stat.name.lowercase(Locale.ROOT)) {
        "hp" -> "HP"
        "attack" -> "Atk"
        "defense" -> "Def"
        "special-attack" -> "SpAtk"
        "special-defense" -> "SpDef"
        "speed" -> "Spd"
        else -> ""
    }
}
