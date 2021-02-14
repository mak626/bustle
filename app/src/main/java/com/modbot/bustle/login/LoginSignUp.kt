package com.modbot.bustle.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.modbot.bustle.R


class LoginSignUp : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val signUpButton = view.findViewById<Button>(R.id.sign_up_button)
        val signInText = view.findViewById<TextView>(R.id.sign_in_text)

        signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginSignUp_to_loginDetailsFragment)
        }

        signInText.setOnClickListener {
            findNavController().navigate(R.id.action_loginSignUp_to_loginMainFragment)
        }
    }
}