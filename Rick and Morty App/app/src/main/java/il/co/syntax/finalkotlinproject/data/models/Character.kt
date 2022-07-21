package il.co.syntax.finalkotlinproject.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "characters")
data class Character(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "title")
    val name: String,
    @SerializedName("status")
    var favorite: String = "Dead",
    val species: String,
    val type: String,
    val gender: String,

    @SerializedName("image")
    val picture: String
) {
}