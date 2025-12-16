package com.example.mymobileappassignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [coldFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class coldFragment : Fragment() {
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cold_cafee, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up click listeners for all coffee cards to open product details
        val btnCappuccino = view.findViewById<LinearLayout>(R.id.btnColdCafee1Capuucina)
        val btnEspresso = view.findViewById<LinearLayout>(R.id.btnColdCafee2Espresso)
        val btnAmericano = view.findViewById<LinearLayout>(R.id.btnColdCafee3Americano)
        val btnMacchiato = view.findViewById<LinearLayout>(R.id.btnColdCafee4Macchiato)

        // Get favorite buttons and prevent them from triggering card clicks
        val loveCapuchino = view.findViewById<ImageView>(R.id.loveCapuchino)
        val loveEspresso = view.findViewById<ImageView>(R.id.loveEspresso)
        val loveAmericano = view.findViewById<ImageView>(R.id.loveAmericano)
        val loveMacchiato = view.findViewById<ImageView>(R.id.loveMacchiato)

        // Set up favorite button click handlers (stop event propagation)
        loveCapuchino?.setOnClickListener { 
            // Handle favorite functionality here if needed
            // Stop event from bubbling to parent
        }
        loveEspresso?.setOnClickListener { }
        loveAmericano?.setOnClickListener { }
        loveMacchiato?.setOnClickListener { }

        // Card click handlers - clicking anywhere on card opens detail
        // Favorite buttons have their own click handlers and won't trigger this
        btnCappuccino?.setOnClickListener {
            openProductDetail("Cappuccino", "1.5", R.drawable.img_8)
        }

        btnEspresso?.setOnClickListener {
            openProductDetail("Espresso", "2.5", R.drawable.img_7)
        }

        btnAmericano?.setOnClickListener {
            openProductDetail("Americano", "3.5", R.drawable.img_9)
        }

        btnMacchiato?.setOnClickListener {
            openProductDetail("Macchiato", "3.5", R.drawable.img_6)
        }
    }

    private fun openProductDetail(name: String, price: String, imageRes: Int) {
        // Navigate at MainActivity level to show full-screen product detail
        // This replaces MenuFragment entirely, hiding menu/search/categories
        val mainActivity = requireActivity() as? MainActivity
        mainActivity?.navigateToProductDetail(name, price, imageRes)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment coldFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            coldFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}