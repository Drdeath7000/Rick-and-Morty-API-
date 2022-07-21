package il.co.syntax.finalkotlinproject.ui.single_character

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import il.co.syntax.finalkotlinproject.data.models.Character
import il.co.syntax.finalkotlinproject.data.repository.CharacterRepository
import il.co.syntax.finalkotlinproject.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleCharacterViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
): ViewModel() {

    private val _id =  MutableLiveData<Int>()

    private val _character = _id.switchMap {
        characterRepository.getCharacter(it)
    }

    val character : LiveData<Resource<Character>> = _character

    fun setId(id : Int) {
        _id.value = id
    }

    fun update(character: Character) {
        viewModelScope.launch {
            characterRepository.update(character)
        }
    }

}