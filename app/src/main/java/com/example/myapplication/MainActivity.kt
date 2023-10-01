package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
//import android.widget.Toolbar
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.nav_fragments.HomeFragment
import com.example.myapplication.nav_fragments.ProfileFragment
import com.example.myapplication.nav_fragments.SettingsFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    private lateinit var fragmentManager:FragmentManager
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
      val toolbar =findViewById<Toolbar>(R.id.toolBar)
       setSupportActionBar(toolbar)
       val navigationView =findViewById<NavigationView>(R.id.navigationView)
       binding.navigationView.setNavigationItemSelectedListener(this)

        val toogle =ActionBarDrawerToggle(this,binding.drawerLayout,toolbar,R.string.nav_open,R.string.nav_close)
        binding.drawerLayout.addDrawerListener(toogle)
        toogle.syncState()
        replaceFragment(HomeFragment())
        navigationView.setNavigationItemSelectedListener(this)

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.navFragment,fragment)
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
      when(item.itemId){
          R.id.nav_home -> replaceFragment(HomeFragment())
          R.id.nav_profile -> replaceFragment(ProfileFragment())
          R.id.nav_setting -> replaceFragment(SettingsFragment())
          R.id.nav_share -> Toast.makeText(this, "Share is clicked", Toast.LENGTH_SHORT).show()
          R.id.nav_logout -> Toast.makeText(this, "logout is clicked", Toast.LENGTH_SHORT).show()
      }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.getOnBackPressedDispatcher().onBackPressed()
        }
    }
    private fun openFragment(fragment: Fragment){
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.navFragment,fragment)
        fragmentTransaction.commit()
    }
}