package il.co.syntax.finalkotlinproject.ui.all_characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import il.co.syntax.finalkotlinproject.data.models.Character
import il.co.syntax.finalkotlinproject.databinding.ItemCharacterBinding

class CharactersAdapter(private val listener : CharacterItemListener) :
    RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    private val characters = ArrayList<Character>()

    inner class CharacterViewHolder(private val itemBinding: ItemCharacterBinding,
                              private val listener: CharacterItemListener)
        : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {

        private lateinit var character: Character

        init {
            itemBinding.root.setOnClickListener(this)
        }

        fun bind(item: Character) {
            this.character = item
            itemBinding.name.text = item.name
            //itemBinding.speciesAndStatus.text = "${item.species} - ${item.status}"
            Glide.with(itemBinding.root)
                .load(item.picture)
                .circleCrop()
                .into(itemBinding.image)
        }

        override fun onClick(v: View?) {
            listener.onCharacterClick(character.id)
        }
    }

    fun setCharacters(characters: List<Character>) {
        this.characters.clear()
        this.characters.addAll(characters)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CharacterViewHolder(binding,listener)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bind(characters[position])


    override fun getItemCount() = characters.size

    interface CharacterItemListener {
        fun onCharacterClick(characterId : Int)
    }
}


/* inner class CharacterViewHolder(private val itemBinding : ItemCharacterBinding) : RecyclerView.ViewHolder(itemBinding.root) {

     val nameTv = itemBinding.name
     val speciesAndStatusTv = itemBinding.speciesAndStatus
     val characterImageView = itemBinding.image

     init {
         itemBinding.root.setOnClickListener {
             listener.onCharacterClick(characters[adapterPosition].id)
         }
     }
 }

  val character = characters[position]
        holder.nameTv.text = character.name
        holder.speciesAndStatusTv.text = "${character.species} ${character.status}"
        Glide.with(holder.itemView.context).load(character.picture).circleCrop().into(holder.characterImageView)
*/