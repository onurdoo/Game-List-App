package com.example.midtermproject.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.midtermproject.model.Game
import com.example.midtermproject.service.DetailedAPIService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

/* ViewModel for the DescFragment Class */
class DetailedViewModel : ViewModel() {
    val detGameLiveData = MutableLiveData<Game?>()
    val gameErrMsg = MutableLiveData<Boolean>()
    val gameDownloading = MutableLiveData<Boolean>()

    private val detGameApiService = DetailedAPIService()
    private val disposable = CompositeDisposable()

    /* Prepare the data for the view class
    * Created bitmaps  for the game image.
    * All data created manually with API Calls theese are will be restored and rearranged
    * */


    fun getData(query: String) {

        getDetDataFromInternet(query)


    }

    fun getDetDataFromInternet(query: String) {


        gameDownloading.value = true
        disposable.add(
            detGameApiService.getDetData(query).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Game>() {
                    override fun onSuccess(t: Game) {

                        detGameLiveData.value = t

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