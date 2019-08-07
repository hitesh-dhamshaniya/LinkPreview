package com.link.linkpreview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import io.github.ponnamkarthik.richlinkpreview.MetaData
import io.github.ponnamkarthik.richlinkpreview.ResponseListener
import io.github.ponnamkarthik.richlinkpreview.RichPreview
import io.github.ponnamkarthik.richlinkpreview.ViewListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        richLinkView.setLink("https://stackoverflow.com", object : ViewListener {

            override fun onSuccess(status: Boolean) {

            }

            override fun onError(e: Exception) {

            }
        })


        richLinkViewTel.setLink("https://www.youtube.com/channel/UC5DlHdPZqA1IzqqSqBXvo0g", object : ViewListener {

            override fun onSuccess(status: Boolean) {

            }

            override fun onError(e: Exception) {

            }
        })

        val richPreview = RichPreview(object : ResponseListener {
            override fun onData(metaData: MetaData) {
//                data = metaData.toInt()
                Picasso.get().load(metaData.imageurl).into(imgPreview)

                tvTitle.text = metaData.title

                //Implement your Layout
            }

            override fun onError(e: Exception) {
                //handle error
            }
        })

        richPreview.getPreview("https://www.youtube.com/watch?v=ZSHOYIe3O08&t=11s")


        naviageToRecyclerViewExample()
    }

    private fun naviageToRecyclerViewExample() {
        btnUseInRecyclerView.setOnClickListener {
            startActivity(Intent(MainActivity@ this, RecyclerViewExampleActivity::class.java))
        }
    }
}
