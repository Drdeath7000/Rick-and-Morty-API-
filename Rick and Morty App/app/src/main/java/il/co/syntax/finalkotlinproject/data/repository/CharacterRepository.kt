package il.co.syntax.finalkotlinproject.data.repository

import il.co.syntax.finalkotlinproject.data.loca_db.CharacterDao
import il.co.syntax.finalkotlinproject.data.models.Character
import il.co.syntax.finalkotlinproject.data.remote_db.CharacterRemoteDataSource
import il.co.syntax.finalkotlinproject.utils.performFetchingAndSaving
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepository @Inject constructor(
    private val remoteDataSource : CharacterRemoteDataSource,
    private val localDataSource : CharacterDao
){

    fun getCharacters() = performFetchingAndSaving(
        {localDataSource.getAllCharacters()},
        {remoteDataSource.getCharacters()},
        {localDataSource.insertCharacters(it.results)}
    )

    fun getCharacter(id : Int) = performFetchingAndSaving(
        {localDataSource.getCharacter(id)},
        {remoteDataSource.getCharacter(id)},
        {localDataSource.insertCharacter(it)}
    )

    suspend fun update(character: Character) {
        localDataSource.update(character)
    }

    fun getFavoriteCharacters() = localDataSource.getFavoriteCharacters()

}