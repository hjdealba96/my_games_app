package com.co.gamesapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "game")
data class Game(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var objectId: String = "",
    var name: String? = null,
    @ColumnInfo(name = "created_at")
    var createdAt: String? = null,
    @ColumnInfo(name = "updated_at")
    var updatedAt: String? = null,
    var price: String? = null,
    @ColumnInfo(name = "image_url")
    @SerializedName("imageURL")
    var imageUrl: String? = null,
    var popular: Boolean? = false,
    var rating: String? = null,
    var downloads: String? = null,
    var description: String? = null,
    @SerializedName("SKU")
    var sku: String? = null,
    var universe: String? = null,
    var kind: String? = null
)