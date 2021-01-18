package com.example.madproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.madlevel5task1.R
import kotlinx.android.synthetic.main.add_character_fragment.*

class AddCharacterFragment : Fragment() {

    private val viewModel: CharacterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.add_character_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSave.setOnClickListener {
            saveNote()
        }

        observeNote()
    }

    private fun observeNote() {
//fill the text fields with the current text and title from the viewmodel
        viewModel.character.observe(viewLifecycleOwner, Observer {
                note  ->
            note?.let {
                char_name.editText?.setText(it.name)
                char_class.editText?.setText(it.c_class)
                char_level.editText?.setText(it.c_level.toString())
                char_race.editText?.setText(it.c_race)
                if (it.stats.size == 6) {
                    strength.setText(it.stats[0].toString())
                    dexterity.setText(it.stats[1].toString())
                    constitution.setText(it.stats[2].toString())
                    intelligence.setText(it.stats[3].toString())
                    wisdom.setText(it.stats[4].toString())
                    charisma.setText(it.stats[5].toString())
                }
            }

        })

        viewModel.error.observe(viewLifecycleOwner, Observer { message ->
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        })

        viewModel.success.observe(viewLifecycleOwner, Observer {     success ->
            //"pop" the backstack, this means we destroy this    fragment and go back to the RemindersFragment
            findNavController().popBackStack()
        })
    }

    private fun saveNote() {

        val stats = ArrayList<Int>(6);
        stats.add(strength.text.toString().toInt());
        stats.add(dexterity.text.toString().toInt());
        stats.add(constitution.text.toString().toInt());
        stats.add(intelligence.text.toString().toInt());
        stats.add(wisdom.text.toString().toInt());
        stats.add(charisma.text.toString().toInt());

        viewModel.updateCharacter(
            char_name.editText?.text.toString(),
            char_class.editText?.text.toString(),
            char_level.editText?.text.toString().toInt(),
            char_race.editText?.text.toString(),
            stats)
    }

}
