package com.example.midtermproject.viewmodel

import android.content.Context
import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.midtermproject.R
import com.example.midtermproject.model.Game

/* ViewModel for the DescFragment Class */
class DetailedViewModel : ViewModel() {
    val detGameLiveData = MutableLiveData<Game>()

    /* Prepare the data for the view class
    * Created bitmaps  for the game image.
    * All data created manually with API Calls theese are will be restored and rearranged
    * */
    fun getData() {
        var aa = Game("Grand Theft Auto V", "96", "Action, shooter", "Bu bir aciklama" , R.drawable.im_gta)
        detGameLiveData.value = aa


    }

}