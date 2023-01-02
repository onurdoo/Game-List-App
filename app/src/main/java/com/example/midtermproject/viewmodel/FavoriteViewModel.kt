package com.example.midtermproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.midtermproject.R
import com.example.midtermproject.model.Game

/* ViewModel for the FavoriteFragment Class */
 class FavoriteViewModel: ViewModel() {
    companion object FavList{
        val favorites = ArrayList<Game>()

        fun refreshData(game: Game) {
            favorites.add(game)
        }
        fun deleteItem(game: Game) {
            favorites.remove(game)
        }
    }

    // All the Game data holds in a list




    /* Prepare the data for the view class
    * Created bitmaps  for the game images.
    * All data created manually with API Calls these are will be restored and rearranged
    * */

}