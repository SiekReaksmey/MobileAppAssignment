package com.example.mymobileappassignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
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
 * Use the [HotCafee.newInstance] factory method to
 * create an instance of this fragment.
 */
class HotCafee : Fragment() {
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
        return inflater.inflate(R.layout.coldmenucaffee, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up click listeners for all hot coffee cards
        val btnCappuccino = view.findViewById<LinearLayout>(R.id.btnHotCafee1Cappuccino)
        val btnEspresso = view.findViewById<LinearLayout>(R.id.btnHotCafee2Espresso)
        val btnAmericano = view.findViewById<LinearLayout>(R.id.btnHotCafee3Americano)
        val btnMacchiato = view.findViewById<LinearLayout>(R.id.btnHotCafee4Macchiato)

        // Prevent favorite buttons from triggering card clicks
        val loveHot1 = view.findViewById<ImageView>(R.id.loveHot1)
        val loveHot2 = view.findViewById<ImageView>(R.id.loveHot2)
        val loveHot3 = view.findViewById<ImageView>(R.id.loveHot3)
        val loveHot4 = view.findViewById<ImageView>(R.id.loveHot4)

        loveHot1?.setOnClickListener { it?.isClickable = true }
        loveHot2?.setOnClickListener { it?.isClickable = true }
        loveHot3?.setOnClickListener { it?.isClickable = true }
        loveHot4?.setOnClickListener { it?.isClickable = true }

        btnCappuccino?.setOnClickListener {
            openProductDetail("Cappuccino", "1.5", R.drawable.img_1)
        }

        btnEspresso?.setOnClickListener {
            openProductDetail("Espresso", "2.5", R.drawable.img_2)
        }

        btnAmericano?.setOnClickListener {
            openProductDetail("Americano", "3.5", R.drawable.img_3)
        }

        btnMacchiato?.setOnClickListener {
            openProductDetail("Macchiato", "3.5", R.drawable.img_4)
        }
    }

    private fun openProductDetail(name: String, price: String, imageRes: Int) {
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
         * @return A new instance of fragment HotCafee.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HotCafee().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}