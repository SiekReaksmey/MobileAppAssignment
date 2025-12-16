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
 * Use the [MatchaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MatchaFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_matcha, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up click listeners for all matcha cards
        val btnLatte = view.findViewById<LinearLayout>(R.id.btnMatcha1Latte)
        val btnSesame = view.findViewById<LinearLayout>(R.id.btnMatcha2Sesame)
        val btnStrawberry = view.findViewById<LinearLayout>(R.id.btnMatcha3Strawberry)
        val btnOrange = view.findViewById<LinearLayout>(R.id.btnMatcha4Orange)

        // Prevent favorite buttons from triggering card clicks
        val loveMatcha1 = view.findViewById<ImageView>(R.id.loveMatcha1)
        val loveMatcha2 = view.findViewById<ImageView>(R.id.loveMatcha2)
        val loveMatcha3 = view.findViewById<ImageView>(R.id.loveMatcha3)
        val loveMatcha4 = view.findViewById<ImageView>(R.id.loveMatcha4)

        loveMatcha1?.setOnClickListener { it?.isClickable = true }
        loveMatcha2?.setOnClickListener { it?.isClickable = true }
        loveMatcha3?.setOnClickListener { it?.isClickable = true }
        loveMatcha4?.setOnClickListener { it?.isClickable = true }

        btnLatte?.setOnClickListener {
            openProductDetail("Matcha Latte", "1.5", R.drawable.img_10)
        }

        btnSesame?.setOnClickListener {
            openProductDetail("Sesame Foam", "2.5", R.drawable.img_11)
        }

        btnStrawberry?.setOnClickListener {
            openProductDetail("Strawberry Late", "3.5", R.drawable.img_12)
        }

        btnOrange?.setOnClickListener {
            openProductDetail("Iced Orange", "3.5", R.drawable.img_13)
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
         * @return A new instance of fragment MatchaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MatchaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}