package com.link.linkpreview.adapter

import com.squareup.picasso.Picasso
import io.github.ponnamkarthik.richlinkpreview.MetaData
import io.github.ponnamkarthik.richlinkpreview.ResponseListener
import io.github.ponnamkarthik.richlinkpreview.RichPreview

/**
 * @author Hitesh
 * @version 1.0
 * @since 07-08-2019
 */
class LinkPreviewManager {

    private val mURLCache : MutableMap<String, MetaData?> = HashMap();
    private val mURLProgress : MutableMap<String, Boolean?> = HashMap();

    var mOnLinkPreview: OnLinkPreview? = null

    fun isLoading(url:String) = mURLProgress[url]

    fun getLink(url:String): MetaData? {
        if(mURLCache.containsKey(url))
            return mURLCache[url];

        if(mURLProgress[url]==true) return null

        mURLProgress[url] = true
        RichPreview(object : ResponseListener {
            override fun onData(metaData: MetaData) {
                mURLCache[url] = metaData
                mOnLinkPreview?.onLink(metaData.imageurl);
                mURLProgress[url] = false
            }
            override fun onError(e: Exception) {
                mURLCache[url] =  null
                mURLProgress[url] = false
            }
        }).getPreview(url);

        return null
    }

}

interface OnLinkPreview{

    fun onLink(link:String?)

}