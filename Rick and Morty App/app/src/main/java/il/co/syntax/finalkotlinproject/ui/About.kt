package il.co.syntax.finalkotlinproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import il.co.syntax.finalkotlinproject.R
import il.co.syntax.finalkotlinproject.databinding.AboutFragmentBinding
import il.co.syntax.finalkotlinproject.databinding.FavoritesBinding
import il.co.syntax.finalkotlinproject.ui.all_characters.AllCharactersViewModel
import il.co.syntax.finalkotlinproject.ui.all_characters.CharactersAdapter
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared

@AndroidEntryPoint
class About : Fragment(){

    private var binding : AboutFragmentBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AboutFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
