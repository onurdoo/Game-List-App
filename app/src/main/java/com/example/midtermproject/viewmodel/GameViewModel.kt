package com.example.midtermproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.midtermproject.R
import com.example.midtermproject.model.Game

/* ViewModel for the GameFragment Class */
class GameViewModel: ViewModel() {

    // All the Game data holds in a list
    val games = MutableLiveData<List<Game>>()
    //val gameErrMsg = MutableLiveData<Boolean>()
   // val gameDownloading = MutableLiveData<Boolean>()



    /* Prepare the data for the view class
    * Created bitmaps  for the game images.
    * All data created manually with API Calls theese are will be restored and rearranged
    * */
    fun refreshhData(){

        // Images created
        val bitmapPortal = R.drawable.im_portal
        val bitmapWitcher = R.drawable.im_witcher
        val bitmapGta = R.drawable.im_gta
        val bitmapL4d2 = R.drawable.im_l4

        var aa = Game("Grand Theft Auto V", "96", "Action, shooter", "metacritic", bitmapGta)
        var bb = Game("Portal 2","95","Action, puzzle","metacritic",bitmapPortal)
        var cc = Game("The Witcher 3: Wild Hunt","89","Action, puzzle","metacritic",bitmapWitcher)
        var dd = Game("Left 4 Dead 2","89","Action, puzzle","metacritic",bitmapL4d2)
        var ee = Game("Grand Theft Auto V", "96", "Action, shooter", "metacritic", bitmapGta)


        val gameList = arrayListOf(
            aa,bb,cc,dd,ee
        )

        //data list created
        games.value = gameList
        //gameErrMsg.value = false
        //gameDownloading.value = false




        // myRecyclerView.setOnClickListener { myRecyclerView.setBackgroundColor(getColor(R.color.aa))}
    }
}