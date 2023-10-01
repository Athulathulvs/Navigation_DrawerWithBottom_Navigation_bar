package com.example.myapplication.nav_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.bottom_fragments.CartFragment
import com.example.myapplication.bottom_fragments.CategoryFragment
import com.example.myapplication.bottom_fragments.HistoryFragment
import com.example.myapplication.bottom_fragments.NotificationFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.btn_catagory -> replaceFragment(CategoryFragment())
                R.id.btn_history ->  replaceFragment(HistoryFragment())
                R.id.btn_cart ->    replaceFragment(CartFragment())
                R.id.btn_notification ->  replaceFragment(NotificationFragment())
            }
          true
        }
        replaceFragment(CategoryFragment())
        bottomNavigationView.selectedItemId =R.id.btn_catagory

        val  addFab =view.findViewById<FloatingActionButton>(R.id.add_fab_btn)
        addFab.setOnClickListener {
            Toast.makeText(context, "Add Clicked", Toast.LENGTH_SHORT).show()
        }
        return view
    }
    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.bottomNavigation,fragment)
            .commit()
    }
}