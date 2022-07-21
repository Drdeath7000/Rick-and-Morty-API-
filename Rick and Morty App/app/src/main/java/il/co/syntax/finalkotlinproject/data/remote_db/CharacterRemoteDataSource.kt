package il.co.syntax.finalkotlinproject.data.remote_db

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRemoteDataSource @Inject constructor(
    private val characterService: CharacterService) : BaseDataSource() {

    suspend fun getCharacters() = getResult { characterService.getAllCharacters() }
    suspend fun getCharacter(id : Int) = getResult { characterService.getCharacter(id) }
}