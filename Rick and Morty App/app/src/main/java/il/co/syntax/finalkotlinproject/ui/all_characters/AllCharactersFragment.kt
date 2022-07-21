package il.co.syntax.finalkotlinproject.ui.all_characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import il.co.syntax.finalkotlinproject.R
import il.co.syntax.finalkotlinproject.databinding.CharactersFragmentBinding
import il.co.syntax.finalkotlinproject.utils.Loading
import il.co.syntax.finalkotlinproject.utils.Success
import il.co.syntax.finalkotlinproject.utils.Error
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared

//you show your characters in your recycler view, we get these characters from the viewModel,

@AndroidEntryPoint
class AllCharactersFragment : Fragment(), CharactersAdapter.CharacterItemListener {

    private val viewModel : AllCharactersViewModel by viewModels()

    private var binding : CharactersFragmentBinding by autoCleared()

    private  lateinit var  adapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CharactersFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CharactersAdapter(this)
        binding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersRv.adapter = adapter

        //adaptpr connects and formats the recyclerview

        viewModel.characters.observe(viewLifecycleOwner) {
            when(it.status) {
                is Loading -> binding.progressBar.visibility = View.VISIBLE

                is Success -> {
                    binding.progressBar.visibility = View.GONE
                    adapter.setCharacters(it.status.data!!)
                }

                is Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(),it.status.message,Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onCharacterClick(characterId: Int) {
       findNavController().navigate(R.id.action_allCharactersFragment_to_singleCharacterFragment,
           bundleOf("id" to characterId))
    }
}