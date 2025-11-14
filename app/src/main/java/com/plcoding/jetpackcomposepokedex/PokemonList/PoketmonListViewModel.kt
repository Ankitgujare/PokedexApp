package com.plcoding.jetpackcomposepokedex.PokemonList

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.google.android.material.color.utilities.CorePalette
import com.plcoding.jetpackcomposepokedex.data.model.PokemonListEntry
import com.plcoding.jetpackcomposepokedex.reposatory.PoketmonReposatory
import com.plcoding.jetpackcomposepokedex.util.Constant.PAGE_SIZE
import com.plcoding.jetpackcomposepokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PoketmonListViewModel @Inject constructor(
    private val reposatory: PoketmonReposatory
):ViewModel() {


    private var curpage=0



    var pokemonList= mutableStateOf<List<PokemonListEntry>>(emptyList())
    var loaderror= mutableStateOf("")
    var isLoading= mutableStateOf(false)
    var endReached= mutableStateOf(false)

    //creating Catch PokemonList here
    private var cachedPokemonList= listOf<PokemonListEntry>()
    private var isSearchstarting=true
    var issearching = mutableStateOf(false)


    fun searchPokemonList(query:String){

        val listToSearch=if(isSearchstarting){
            pokemonList.value
        }else{
            cachedPokemonList
        }

        viewModelScope.launch(Dispatchers.Default){
            if (query.isEmpty()){
                pokemonList.value=cachedPokemonList
                issearching.value=false
                isSearchstarting=true
                return@launch

            }

            val results=listToSearch.filter {
                it.pokemonName.contains(query.trim(),ignoreCase = true) ||
                        it.number.toString()==query.trim()
            }

            if (isSearchstarting){
                cachedPokemonList=pokemonList.value
                isSearchstarting=false
            }

            pokemonList.value=results
            issearching.value=true
        }
    }


    init {
        loadPokemonPaginated()
    }

    fun loadPokemonPaginated(){
        viewModelScope.launch {
            isLoading.value=true
            val result=reposatory.getPokemonList(PAGE_SIZE,curpage+ PAGE_SIZE)
            when(result){
                is Resource.Success->{
                    endReached.value=curpage+ PAGE_SIZE>=result.data!!.count
                    val pokedexEntries=result.data.results.mapIndexed{index,entry->
                        val number=if (entry.url.endsWith("/")){
                            entry.url.dropLast(1).takeLastWhile { it.isDigit() }

                        }else{
                            entry.url.takeLastWhile { it.isDigit() }
                        }
                        val url="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"

                        PokemonListEntry(entry.name.capitalize(Locale.ROOT),url,number.toInt())
                    }
                    curpage++
                    loaderror.value=""
                    isLoading.value=false
                    pokemonList.value+=pokedexEntries
                }


                is Resource.Error->{
                    loaderror.value=result.message!!
                    isLoading.value=false

                }
                
                is Resource.Loading -> {
                    // Loading state is already handled by isLoading.value = true
                }
            }
        }
    }


    fun calDominantColor(drawable:Drawable,onfinish:(Color)->Unit){
        val bitmap=(drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888,true)
        Palette.from(bitmap).generate{palatte->
            palatte?.dominantSwatch?.rgb?.let {colorValue->
                onfinish(Color(colorValue))
            }
        }
    }
}