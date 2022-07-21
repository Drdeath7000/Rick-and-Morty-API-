package il.co.syntax.finalkotlinproject.ui.single_character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import il.co.syntax.finalkotlinproject.R
import il.co.syntax.finalkotlinproject.data.models.Character
import il.co.syntax.finalkotlinproject.databinding.CharacterDetailFragmentBinding
import il.co.syntax.finalkotlinproject.utils.Loading
import il.co.syntax.finalkotlinproject.utils.Success
import il.co.syntax.finalkotlinproject.utils.Error
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared
import java.sql.RowId

@AndroidEntryPoint
class SingleCharacterFragment : Fragment() {

    private val viewModel : SingleCharacterViewModel by viewModels()

    private var binding : CharacterDetailFragmentBinding by autoCleared()

    private lateinit var character : Character


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CharacterDetailFragmentBinding.inflate(inflater,container,false, )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.character.observe(viewLifecycleOwner) {

            when(it.status) {
                is Success -> {
                    binding.progressBar.visibility = View.GONE
                    updateCharacter(it.status.data!!)
                    character = it.status.data!!
                    binding.characterCl.visibility = View.VISIBLE
                }
                is Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.characterCl.visibility = View.GONE
                }
                is Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(),it.status.message,Toast.LENGTH_LONG).show()
                }
            }
        }

        arguments?.getInt("id")?.let {
            viewModel.setId(it)
        }


        binding.favorite.setOnClickListener {
            viewModel.character.observe(viewLifecycleOwner) {

            }
            arguments?.getInt("id")?.let {
                viewModel.setId(it)
            }
            updateFavorite(character)
            viewModel.update(character)
            findNavController().navigate(R.id.action_singleCharacterFragment_to_favorites)
        }
    }


//    fun onClickFav(character: Character){
//        viewModel.characters.observe(viewLifecycleOwner) {            viewModel.setId(character.id)
//            character.favorite = character.favorite != true
//            viewModel.update(character)}

    private fun updateFavorite(character: Character) {

        if (character.favorite == "Alive" || character.favorite == "unknown"
        ) {
            character.favorite = "Dead"
        }
        else{
            character.favorite = "Alive"
            viewModel.update(character)
        }
    }

    private fun updateCharacter(character:Character) {
        binding.name.text = character.name
        binding.gender.text = character.gender
        binding.species.text = character.species

        //binding.status.text = character.status
        Glide.with(requireContext()).load(character.picture).circleCrop().into(binding.image)
    }
}