package com.example.mymobileappassignment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddToCardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddToCardFragment : Fragment() {
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
        return inflater.inflate(R.layout.activity_add_tocard_view, container, false)
    }

    @SuppressLint("DefaultLocale")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get views from layout
        val imgBack = view.findViewById<ImageView>(R.id.imgBack)
        val txtName = view.findViewById<TextView>(R.id.txtName)
        val txtPrice = view.findViewById<TextView>(R.id.txtPrice)
        val txtQuantity = view.findViewById<TextView>(R.id.txtQuantity)
        val txtTotalPrice = view.findViewById<TextView>(R.id.txtTotalPrice)
        val btnMinus = view.findViewById<TextView>(R.id.btnMinus)
        val btnPlus = view.findViewById<TextView>(R.id.btnPlus)
        val btnAddToCart = view.findViewById<Button>(R.id.btnAddToCart)
        val imgCoffee = view.findViewById<ImageView>(R.id.imgCoffee)
        
        // Ensure button is enabled and clickable
        btnAddToCart?.isEnabled = true
        btnAddToCart?.isClickable = true
        
        // Back button functionality
        imgBack?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        // Use arguments if provided (param1 = name, param2 = price)
        val name = param1 ?: "Cappuccino"
        val price = param2?.toDoubleOrNull() ?: 1.5
        val imageResId = arguments?.getInt("IMAGE_RES_ID", R.drawable.img_8) ?: R.drawable.img_8
        val isEditMode = arguments?.getBoolean("IS_EDIT_MODE", false) ?: false
        val currentQuantity = arguments?.getInt("CURRENT_QUANTITY", 1) ?: 1

        txtName.text = name
        txtPrice.text = String.format("%.1f$", price)
        imgCoffee.setImageResource(imageResId)

        // Update button text based on edit mode
        if (isEditMode) {
            btnAddToCart.text = "Update Cart"
        }

        // If editing, start with current quantity; otherwise start with 1
        var quantity = if (isEditMode) currentQuantity else 1

        fun updateTotal() {
            val total = price * quantity
            txtQuantity.text = quantity.toString()
            txtTotalPrice.text = String.format("Total: %.1f$", total)
        }

        updateTotal()

        btnPlus.setOnClickListener {
            quantity++
            updateTotal()
        }

        btnMinus.setOnClickListener {
            if (quantity > 1) {
                quantity--
                updateTotal()
            }
        }

        btnAddToCart?.setOnClickListener {
            if (isEditMode) {
                // Update existing item in cart
                CartManager.updateItemQuantity(name, price, quantity)
                Toast.makeText(
                    requireContext(),
                    "Updated $quantity x $name in cart",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                // Add new item to cart
                CartManager.addItem(name, price, imageResId, quantity)
                Toast.makeText(
                    requireContext(),
                    "Added $quantity x $name to cart",
                    Toast.LENGTH_SHORT
                ).show()
            }
            
            // Navigate to cart screen
            val mainActivity = requireActivity() as? MainActivity
            mainActivity?.navigateToCart()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddToCardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddToCardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}