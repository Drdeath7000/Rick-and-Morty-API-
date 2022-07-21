package il.co.syntax.finalkotlinproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import il.co.syntax.finalkotlinproject.R
import il.co.syntax.finalkotlinproject.data.models.Character
import il.co.syntax.finalkotlinproject.databinding.FavoritesBinding
import il.co.syntax.finalkotlinproject.ui.all_characters.AllCharactersViewModel
import il.co.syntax.finalkotlinproject.ui.all_characters.CharactersAdapter
import il.co.syntax.finalkotlinproject.utils.Error
import il.co.syntax.finalkotlinproject.utils.Loading
import il.co.syntax.finalkotlinproject.utils.Success
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared


@AndroidEntryPoint
abstract class Favorites : Fragment(), CharactersAdapter.CharacterItemListener {


    private val viewModel: AllCharactersViewModel by viewModels()

    private var binding: FavoritesBinding by autoCleared()

    private lateinit var adapter: CharactersAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CharactersAdapter(this)
        binding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersRv.adapter = adapter

        viewModel.favorites.observe(viewLifecycleOwner) {
            var temp:List<Character> = ArrayList<Character>()
            temp = it
            adapter.setCharacters(temp)

        }
    }


    override fun onCharacterClick(characterId: Int) {
        findNavController().navigate(
            R.id.action_allCharactersFragment_to_singleCharacterFragment,
            bundleOf("id" to characterId)
        )
    }
}