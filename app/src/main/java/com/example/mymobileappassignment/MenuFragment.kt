package com.example.mymobileappassignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import kotlin.contracts.contract

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        val btnHot = view.findViewById<Button>(R.id.btnHot)
        val btnCold = view.findViewById<Button>(R.id.btnCold)
        val love = inflater.inflate(R.layout.fragment_cold_cafee, container, false).findViewById<ImageView>(R.id.loveCapuchino)
        var btnMatcha = view.findViewById<Button>(R.id.btnMacha)
        btnMatcha.setOnClickListener {
            childFragmentManager.beginTransaction()
                .replace(R.id.fram_Menu, MatchaFragment())
                .addToBackStack(null)
                .commit()
            btnHot.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.white)
            btnHot.setTextColor(ContextCompat.getColor(requireContext(), R.color.brown))
            btnCold.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.white)
            btnCold.setTextColor(ContextCompat.getColor(requireContext(), R.color.brown))
            btnMatcha.backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.brown)
            btnMatcha.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))
        }
        btnHot.setOnClickListener {
            // Replace FrameLayout with HotCoffeeFragment
            childFragmentManager.beginTransaction()
                .replace(R.id.fram_Menu, HotCafee())
                .addToBackStack(null)
                .commit()

            // Change button color correctly
            btnHot.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.brown)
            btnHot.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            btnCold.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.white)
            btnCold.setTextColor(ContextCompat.getColor(requireContext(), R.color.brown))
            btnMatcha.backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.white)
            btnMatcha.setTextColor(ContextCompat.getColor(requireContext(),R.color.brown))
        }
        btnCold.setOnClickListener {
            childFragmentManager.beginTransaction()
                .replace(R.id.fram_Menu, coldFragment())
                .addToBackStack(null)
                .commit()
            btnHot.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.white)
            btnHot.setTextColor(ContextCompat.getColor(requireContext(), R.color.brown))
            btnCold.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.brown)
            btnCold.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            btnMatcha.backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.white)
            btnMatcha.setTextColor(ContextCompat.getColor(requireContext(),R.color.brown))
        }
        btnCold.performClick()
        return view
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MenuFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MenuFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}