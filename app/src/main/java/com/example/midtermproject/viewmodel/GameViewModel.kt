package com.example.midtermproject.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.midtermproject.model.Game
import com.example.midtermproject.service.GamesAPIService
import com.example.midtermproject.service.Responses
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import kotlin.math.log


/* ViewModel for the GameFragment Class */
class GameViewModel: ViewModel() {

    // All the Game data holds in a list
    val games = MutableLiveData<List<Game>?>()

    val gameErrMsg = MutableLiveData<Boolean>()
    val gameDownloading = MutableLiveData<Boolean>()

    private val gameApiService = GamesAPIService()
    private val disposable = CompositeDisposable()



    /* Prepare the data for the view class
    * Created bitmaps  for the game images.
    * All data created manually with API Calls theese are will be restored and rearranged
    * */
    fun refreshhData(){
        getDataFromInternet()

    }

    fun getDataFromInternet(){
        gameDownloading.value = true
        disposable.add(
            gameApiService.getData().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Responses>(){
                    override fun onSuccess(t: Responses) {

                        games.value = t.results

                        Log.i("ALDI", "asdasdasd.value.toString()")
                        gameDownloading.value = false
                        gameErrMsg.value = false
                    }

                    override fun onError(e: Throwable) {
                        gameDownloading.value = false
                        gameErrMsg.value = true
                        Log.i("HATA!!", gameErrMsg.value.toString())
                    }

                })
        )

    }
}