package com.modbot.bustle.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.SupportMapFragment
import com.modbot.bustle.R

class LoginMainFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val signInButton = view.findViewById<Button>(R.id.sign_in_button)
        val signUpText = view.findViewById<TextView>(R.id.sign_up_text)

        signInButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginMainFragment_to_homeFragment)
        }

        signUpText.setOnClickListener {
            findNavController().navigate(R.id.action_loginMainFragment_to_loginSignUp)
        }

    }
}