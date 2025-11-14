package com.plcoding.jetpackcomposepokedex.reposatory

import com.plcoding.jetpackcomposepokedex.data.remote.PokeApi
import com.plcoding.jetpackcomposepokedex.data.remote.responses.pokemonList
import com.plcoding.jetpackcomposepokedex.data.remote.responses.pokemons
import com.plcoding.jetpackcomposepokedex.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PoketmonReposatory @Inject constructor(
    private val api:PokeApi
) {

    suspend fun getPokemonList(limit:Int,offset:Int):Resource<pokemonList>{
        val response=try{
            api.getPokemonList(limit,offset)
        }catch (e:Exception){
           return Resource.Error("An Unknown Error Occured")
        }

        return Resource.Success(response)

    }


    suspend fun getpokemonInfo(pokemonName:String):Resource<pokemons>{
        val response=try{
            api.getpokimonInfo(pokemonName)
        }catch (e:Exception){
            return Resource.Error("An Unknown Error Occured")
        }

        return Resource.Success(response)

    }
}