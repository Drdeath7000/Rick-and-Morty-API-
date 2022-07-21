package il.co.syntax.finalkotlinproject.data.loca_db

import androidx.lifecycle.LiveData
import androidx.room.*
import il.co.syntax.finalkotlinproject.data.models.Character

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters ORDER BY title ASC")
    fun getAllCharacters() : LiveData<List<Character>>

    @Query("SELECT * FROM characters WHERE id = :id")
    fun getCharacter(id : Int) : LiveData<Character>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: Character)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters : List<Character>)

    @Update
    suspend fun update(vararg character: Character)

    @Query("SELECT * FROM characters WHERE `favorite` = 'Alive' ORDER BY title ASC")
    fun getFavoriteCharacters(): LiveData<List<Character>>
}