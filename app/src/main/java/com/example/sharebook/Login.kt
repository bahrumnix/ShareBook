package com.example.sharebook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class Login : Fragment(), View.OnClickListener {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val button : Button = view.findViewById(R.id.signuplink)
        button.setOnClickListener (this)
        return view
    }

    override fun onClick(v: View?) {
        val mFragmentManager = fragmentManager
        val signUpFragment = regist()
        mFragmentManager?.beginTransaction()?.apply {
            replace(R.id.MainFragment, signUpFragment, regist::class.java.simpleName)
            addToBackStack(null)
            commit()
        }
    }

}
