package com.example.midtermproject.viewmodel

import androidx.lifecycle.ViewModel
import com.example.midtermproject.model.Game

/* ViewModel for the FavoriteFragment Class */
class FavoriteViewModel : ViewModel() {
    companion object FavList {
        val favorites = ArrayList<Game>()

        fun refreshData(game: Game) {
            favorites.add(game)
        }

        fun deleteItem(game: Game) {
            favorites.remove(game)
        }
    }

    // All the Game data holds in a list

}