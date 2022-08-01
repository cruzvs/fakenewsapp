package com.cruz.fakenews.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class News (
    @SerializedName("titulo")
    val title:String,
    @SerializedName("imagem")
    val image:String,
    @SerializedName("subtitulo")
    val subtitle:String,
    @SerializedName("noticia")
    val news:String,
    ):Parcelable