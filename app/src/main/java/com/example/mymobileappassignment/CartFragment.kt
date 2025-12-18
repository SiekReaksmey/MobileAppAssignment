package com.example.mymobileappassignment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CartFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    private var rootView: View? = null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rootView = view
        refreshCart()
    }
    
    @SuppressLint("SetTextI18n")
    private fun refreshCart() {
        val view = rootView ?: return
        
        val cartContainer = view.findViewById<LinearLayout>(R.id.cartItemsContainer)
        val txtTotal = view.findViewById<TextView>(R.id.txtTotal)
        val txtEmptyCart = view.findViewById<TextView>(R.id.txtEmptyCart)
        
        cartContainer?.removeAllViews()
        
        val cartItems = CartManager.getItems()
        
        if (cartItems.isEmpty()) {
            txtEmptyCart?.visibility = View.VISIBLE
            txtTotal?.text = " $0.00"
        } else {
            txtEmptyCart?.visibility = View.GONE
            
            val inflater = LayoutInflater.from(requireContext())
            var total = 0.0
            
            for (item in cartItems) {
                val itemView = inflater.inflate(R.layout.item_cart, cartContainer, false)
                
                val imgItem = itemView.findViewById<ImageView>(R.id.imgItem)
                val txtItemName = itemView.findViewById<TextView>(R.id.txtItemName)
                val txtItemPrice = itemView.findViewById<TextView>(R.id.txtItemPrice)
                val txtItemTotal = itemView.findViewById<TextView>(R.id.txtItemTotal)
                val btnDelete = itemView.findViewById<ImageView>(R.id.btnDeleteItem)
                
                imgItem.setImageResource(item.imageResId)
                txtItemName.text = item.name
                txtItemPrice.text = String.format("%.1f$ x%d", item.price, item.quantity)
                
                val itemTotal = item.getTotal()
                txtItemTotal.text = String.format("$%.2f", itemTotal)
                total += itemTotal
                
                // Delete button click handler - stops event propagation
                btnDelete?.setOnClickListener { v ->
                    v?.isClickable = true
                    CartManager.removeItem(item.id)
                    Toast.makeText(
                        requireContext(),
                        "${item.name} removed from cart",
                        Toast.LENGTH_SHORT
                    ).show()
                    refreshCart() // Refresh the cart after deletion
                }
                
                // Make item clickable to edit (delete button has its own handler)
                itemView.setOnClickListener {
                    val mainActivity = requireActivity() as? MainActivity
                    mainActivity?.navigateToProductDetailForEdit(
                        item.name,
                        item.price.toString(),
                        item.imageResId,
                        item.quantity
                    )
                }
                
                cartContainer?.addView(itemView)
            }
            
            txtTotal?.text = String.format(" $%.2f", total)
        }
    }
    
    override fun onResume() {
        super.onResume()
        // Refresh cart when fragment becomes visible
        refreshCart()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CartFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CartFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}