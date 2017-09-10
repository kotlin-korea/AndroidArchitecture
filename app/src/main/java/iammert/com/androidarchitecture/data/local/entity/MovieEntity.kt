package iammert.com.androidarchitecture.data.local.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieEntity(
        @PrimaryKey
        var id: Int,
        @SerializedName("poster_path")
        var posterPath: String?,
        var adult: Boolean,
        @SerializedName("overview")
        var overview: String?,
        @SerializedName("original_title")
        var originalTitle: String?,
        var title: String?,
        @SerializedName("vote_count")
        var voteCount: Int,
        @SerializedName("vote_average")
        var voteAverage: Double,
        @SerializedName("backdrop_path")
        var backdropPath: String?,
        @SerializedName("original_language")
        var originalLanguage: String?
)