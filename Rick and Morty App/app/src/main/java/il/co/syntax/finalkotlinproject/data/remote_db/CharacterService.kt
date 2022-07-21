package il.co.syntax.finalkotlinproject.data.remote_db

import il.co.syntax.finalkotlinproject.data.models.AllCharacters
import il.co.syntax.finalkotlinproject.data.models.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {

    @GET("character")
    suspend fun getAllCharacters() : Response<AllCharacters>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id : Int) : Response<Character>
}