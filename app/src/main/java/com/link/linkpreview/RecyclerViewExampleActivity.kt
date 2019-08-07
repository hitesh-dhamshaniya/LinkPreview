package com.link.linkpreview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.link.linkpreview.adapter.LinkPreviewAdapter
import kotlinx.android.synthetic.main.activity_recycler_view_example.*

class RecyclerViewExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_example)

        initUI()
    }

    private fun initUI() {
        rvLinkPreview.setHasFixedSize(true)
        rvLinkPreview.layoutManager = LinearLayoutManager(this)
        rvLinkPreview.adapter = LinkPreviewAdapter()
    }
}
