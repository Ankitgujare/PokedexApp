package com.plcoding.jetpackcomposepokedex.PokedomDetail

import androidx.lifecycle.ViewModel
import com.plcoding.jetpackcomposepokedex.data.remote.responses.pokemons
import com.plcoding.jetpackcomposepokedex.reposatory.PoketmonReposatory
import com.plcoding.jetpackcomposepokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val reposatory: PoketmonReposatory
):ViewModel() {


    suspend fun getpokimonInfo(pokemonName:String):Resource<pokemons>{
        return reposatory.getpokemonInfo(pokemonName)

    }
}