package com.link.linkpreview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.link.linkpreview.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom.view.*

/**
 * @author Hitesh
 * @version 1.0
 * @since 07-08-2019
 */
class LinkPreviewAdapter : RecyclerView.Adapter<LinkPreviewAdapter.ViewHolder>() {

    private val mLinkPreviewManager: LinkPreviewManager = LinkPreviewManager()

    init {
        mLinkPreviewManager.mOnLinkPreview = object : OnLinkPreview {
            override fun onLink(link: String?) {
                notifyDataSetChanged()
            }
        }
    }

    val sampleUrlList = listOf<String>(
        "http://www.dadaabstories.org/",
        "https://www.julianabicycles.com/en-US",
        "https://time.com/",
        "http://www.edseyart.com/",
        "http://www.edseyart.com/",
        "http://adayinbigdata.com/",
        "http://mustafademirkent.com/",
        "http://gingerwhale.com/",
        "https://pittsburghkids.org/",
        "https://www.fork-cms.com/",
        "https://oliverrussell.com/",
        "http://www.anderssonwise.com/",
        "https://www.youtube.com/channel/UC5DlHdPZqA1IzqqSqBXvo0g",
        "https://medium.com/p/c8c6d332f137/responses/show",
        "https://web.whatsapp.com/"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHold = LayoutInflater.from(parent.context).inflate(R.layout.custom, parent, false)
        return ViewHolder(viewHold)
    }

    override fun getItemCount() = sampleUrlList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val url = getItem(position)
        val link = mLinkPreviewManager.getLink(url)
        when {
            link != null -> {
                if (link.imageurl.isNullOrEmpty()) {
                    Picasso.get().load(android.R.drawable.ic_dialog_alert).into(holder.imgPreview)
                } else {
                    Picasso.get().load(link.imageurl).into(holder.imgPreview)
                }
                holder.tvTitle.text = link.title
            }
            mLinkPreviewManager.isLoading(url) == true -> Picasso.get().load(android.R.drawable.ic_menu_myplaces).into(
                holder.imgPreview
            )
            else -> Picasso.get().load(android.R.drawable.ic_menu_agenda).into(holder.imgPreview)
        }

    }

    private fun getItem(position: Int): String = sampleUrlList[position]

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPreview = itemView.imgPreview
        val tvTitle = itemView.tvTitle

        init {

        }
    }
}