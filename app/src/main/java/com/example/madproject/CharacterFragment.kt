package com.example.madproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.madlevel5task1.R
import kotlinx.android.synthetic.main.char_overview_fragment.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CharacterFragment : Fragment() {

    private val viewModel: CharacterViewModel by viewModels()
    val BASELINE = 10

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.char_overview_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState:     Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeAddNoteResult()
    }

    private fun observeAddNoteResult() {
        viewModel.character.observe(viewLifecycleOwner, Observer{ note ->
            note?.let {
                name.text = it.name
                tvLastUpdated.text = getString(R.string.last_updated, it.lastUpdated.toString())
                _class.text = it.c_class
                level.text = it.c_level.toString()
                race.text = it.c_race
                _strength.text = it.stats[0].toString()
                _dexterity.text = it.stats[1].toString()
                _constitution.text = it.stats[2].toString()
                _intelligence.text = it.stats[3].toString()
                _wisdom.text = it.stats[4].toString()
                _charisma.text = it.stats[5].toString()
                str_mod.text = calcModifier(it.stats[0])
                dex_mod.text = calcModifier(it.stats[1])
                con_mod.text = calcModifier(it.stats[2])
                int_mod.text = calcModifier(it.stats[3])
                wis_mod.text = calcModifier(it.stats[4])
                cha_mod.text = calcModifier(it.stats[5])
            }
        })
    }

    private fun calcModifier(num: Int): String {
        val mod = ((num - BASELINE)/2);
        if (mod >= 0) {
            return "+$mod";
        }
        return "$mod";
    }

}