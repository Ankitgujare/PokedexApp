package com.plcoding.jetpackcomposepokedex.PokemonList

import android.os.Build
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.plcoding.jetpackcomposepokedex.R
import com.plcoding.jetpackcomposepokedex.data.model.PokemonListEntry
import com.plcoding.jetpackcomposepokedex.ui.theme.RobotoCondensed
import java.time.format.TextStyle

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun pokemonListScreen(
    navController: NavController,
    viewModel: PoketmonListViewModel= hiltViewModel()

){
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize(),
    ) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_international_pok_mon_logo ),
                contentDescription ="Pokemon",
            modifier= Modifier
                .fillMaxWidth()
                .align(CenterHorizontally)
            )

            //SearchBar component

            SearchBar(
                hint = "Searching",
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp, horizontal = 12.dp)
                    .align(CenterHorizontally)

            ){
              //Here we will Do viewModel.search()
                viewModel.searchPokemonList(it)

            }
            Spacer(Modifier.height(16.dp))
            pokemonList(navController)


        }
    }
}



//Reusable composable
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SearchBar(
    modifier:Modifier=Modifier,
    hint:String="",
    onsearch:(String)->Unit={}

) {
    var text by remember { mutableStateOf("") }
    var isHintDisplayed by remember { mutableStateOf(hint!="") }

    Box(modifier = modifier){
        BasicTextField(value = text,
            onValueChange ={
                text=it
                onsearch(it)
            },
            maxLines = 1,
            singleLine = true,
            modifier= Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = !it.isFocused && text.isNotEmpty()
                })


        if (isHintDisplayed){
            Text(
                text=hint,
                color=Color.LightGray,
                modifier=Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }

    }



}


@Composable
fun pokemonList(
    navController: NavController,
    viewModel: PoketmonListViewModel= hiltViewModel()
) {

    val pokemonList by remember { viewModel.pokemonList }
    val endReached by remember{viewModel.endReached}
    val loadError by remember{viewModel.loaderror}
    val isLoading by remember{viewModel.isLoading}
    val isSearching by remember{viewModel.issearching}




    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){

        val itemCount=if(pokemonList.size%2==0){
            pokemonList.size/2
        } else{
            pokemonList.size/2 + 1
        }
        items(itemCount){
            if (it>=itemCount-1 && !endReached && !isLoading && !isSearching){
                viewModel.loadPokemonPaginated()
            }
            pokedexRow(rowIndex = it, entries =pokemonList,navController )
        }
    }

    Box(

        contentAlignment = Center,
        modifier=Modifier.fillMaxSize()
    ){

        if (isLoading){
            CircularProgressIndicator(color =MaterialTheme.colors.primary)
        }
        if (loadError.isNotEmpty()){
            retrysection(error = loadError) {
                viewModel.loadPokemonPaginated()
            }
        }
    }


}

















@Composable
fun pokedexEntry(
    entry:PokemonListEntry,
    navController: NavController,
     modifier:Modifier=Modifier,
    viewModel:PoketmonListViewModel= hiltViewModel()
) {


    val defaultDominantColor = MaterialTheme.colors.surface
    var actualDominantColor by remember { mutableStateOf(defaultDominantColor) }



    Box(
        contentAlignment = Center,
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(
                Brush.verticalGradient(
                    listOf(
                        actualDominantColor,
                        defaultDominantColor
                    )
                )
            )
            .clickable {
                navController.navigate(
                    "pokemon_detail_screen/${actualDominantColor.toArgb()}/${entry.pokemonName}"
                )
            }

    ) {

        Column{
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(entry.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = entry.pokemonName,
                onSuccess = { success ->
                    viewModel.calDominantColor(success.result.drawable) {
                        actualDominantColor = it
                    }
                },
                modifier = Modifier
                    .size(120.dp)
                    .align(CenterHorizontally)
            )
            
           Text(
               text = entry.pokemonName,
               textAlign = TextAlign.Center,
               fontSize = 20.sp,
               fontFamily = RobotoCondensed,
               modifier=Modifier.fillMaxWidth()



           )

        }
    }

}


@Composable
fun pokedexRow(
    rowIndex:Int,
    entries:List<PokemonListEntry>,
    navController: NavController

) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        pokedexEntry(
            entry = entries[rowIndex*2],
            navController=navController,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(8.dp))
        
        if (entries.size>=rowIndex*2+2){
            pokedexEntry(
                entry = entries[rowIndex*2+1],
                navController=navController,
                modifier = Modifier.weight(1f)
            )
        }else{ 
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun retrysection(
    error:String,
    onretry:()->Unit
) {
    Column {
        Text(text = error,color=Color.Red, fontSize =18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onretry() },
            modifier=Modifier.align(CenterHorizontally)


        ) {
            Text(text = "Retry")
        }

    }
    
}