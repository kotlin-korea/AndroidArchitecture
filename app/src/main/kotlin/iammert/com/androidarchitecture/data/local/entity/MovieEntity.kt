package iammert.com.androidarchitecture.data.local.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
class MovieEntity {

    @PrimaryKey
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("poster_path")
    var posterPath: String? = null

    @SerializedName("adult")
    var adult: Boolean = false

    @SerializedName("overview")
    var overview: String? = null

    @SerializedName("original_title")
    var originalTitle: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("vote_count")
    var voteCount: Int = 0

    @SerializedName("vote_average")
    var voteAverage: Double = 0.toDouble()

    @SerializedName("backdrop_path")
    var backdropPath: String? = null

    @SerializedName("original_language")
    var originalLanguage: String? = null
    
}