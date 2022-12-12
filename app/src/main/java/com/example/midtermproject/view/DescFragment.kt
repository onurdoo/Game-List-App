package com.example.midtermproject.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.midtermproject.R
import com.example.midtermproject.viewmodel.DetailedViewModel


/* View Class for Detailed Page
* For the present this class dont use the viewModel due to midterm project requirments
* With API requests this whole class will be updated
* */
class DescFragment : Fragment() {
    private lateinit var viewModel: DetailedViewModel //created view model field
    private var gameImId = 0 // Image Id field
    private var gameTitle = "" //Game Title field

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_desc, container, false)
    }


    /*For the fragment lifecycles this method called
    * All operations executed in here
    */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //For the fragment replace button bind with view id
        val backButton = view.findViewById<LinearLayout>(R.id.backButton)

        // viewModel initialized
        viewModel = ViewModelProvider(this)[DetailedViewModel::class.java]
        viewModel.getData()



        //Created arguments with action it recieved image and title data for the selected game
        arguments?.let {
            gameImId = DescFragmentArgs.fromBundle(it).imageID
            gameTitle = DescFragmentArgs.fromBundle(it).gameTitle
            println(gameImId) //data control

        }

        //view binds
        view.findViewById<TextView>(R.id.gameName).text = gameTitle
        val imageBitmap = BitmapFactory.decodeResource(resources, gameImId)


        //image resized for the detailed page
        val bitmapResized = Bitmap.createScaledBitmap(imageBitmap, 430, 291,false)

        view.findViewById<ImageView>(R.id.detailedIm).setImageBitmap(bitmapResized)





        // with navigation framework fragment replacements are done
        backButton.setOnClickListener {
            val action = DescFragmentDirections.actionDescFragmentToGameFragment() //action created
            Navigation.findNavController(it).navigate(action)
        }

    }


    //
    fun observeLiveData() {

        viewModel.detGameLiveData.observe(viewLifecycleOwner, Observer { game ->
            game?.let {
                var gameName = view?.findViewById<TextView>(R.id.gameName)
                var gameDesc = view?.findViewById<TextView>(R.id.gameDesc)

                gameName?.text = it.name
                gameDesc?.text = it.description

            }

        })
    }

    /*
    fun scaleImage(image: Drawable?, scaleFactor: Float): Drawable? {
        var image = image
        if (image == null || image !is BitmapDrawable) {
            return image
        }
        val b = image.bitmap
        val sizeX = Math.round(image.getIntrinsicWidth() * scaleFactor)
        val sizeY = Math.round(image.getIntrinsicHeight() * scaleFactor)
        val bitmapResized = Bitmap.createScaledBitmap(b, sizeX, sizeY, false)
        image = BitmapDrawable(resources, bitmapResized)
        return image
    }*/

}