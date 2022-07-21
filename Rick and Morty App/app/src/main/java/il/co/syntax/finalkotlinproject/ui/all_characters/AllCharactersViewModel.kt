package il.co.syntax.finalkotlinproject.ui.all_characters

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import il.co.syntax.finalkotlinproject.data.repository.CharacterRepository
import javax.inject.Inject

@HiltViewModel
class AllCharactersViewModel @Inject constructor(
    characterRepository: CharacterRepository
) : ViewModel() {

    val characters  = characterRepository.getCharacters()
    val favorites = characterRepository.getFavoriteCharacters()


}