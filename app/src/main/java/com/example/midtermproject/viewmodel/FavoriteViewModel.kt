package com.example.midtermproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.midtermproject.R
import com.example.midtermproject.model.Game

/* ViewModel for the FavoriteFragment Class */
class FavoriteViewModel: ViewModel() {

    // All the Game data holds in a list
    val favorites = MutableLiveData<List<Game>>()
    //val gameErrMsg = MutableLiveData<Boolean>()
    // val gameDownloading = MutableLiveData<Boolean>()


    /* Prepare the data for the view class
    * Created bitmaps  for the game images.
    * All data created manually with API Calls theese are will be restored and rearranged
    * */
    fun refreshData() {

        // Images created
        var bitmapWitcher = R.drawable.im_witcher
        var bitmapGta = R.drawable.im_gta
        var a = Game("Grand Theft Auto V", "96", "Action, shooter", "metacritic", bitmapGta)
        var b = Game("The Witcher 3: Wild Hunt","89","Action, puzzle","metacritic",bitmapWitcher)


        val favList = arrayListOf<Game>(
            a, b
        )
        //data list created
        favorites.value = favList
    }

}