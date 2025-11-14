package com.plcoding.jetpackcomposepokedex.data.remote

import com.plcoding.jetpackcomposepokedex.data.remote.responses.pokemonList
import com.plcoding.jetpackcomposepokedex.data.remote.responses.pokemons
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit")limit:Int,
        @Query("offset")offset:Int
    ):pokemonList


    @GET("pokemon/{name}")
    suspend fun getpokimonInfo(
        @Path("name") name:String
    ):pokemons
}