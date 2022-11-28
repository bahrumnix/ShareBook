package com.example.sharebook

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class regist : Fragment(), View.OnClickListener {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_regist, container, false)
        val button : Button = view.findViewById(R.id.backToLoginButton)
        button.setOnClickListener (this)
        return view

    }

    override fun onClick(v: View?) {
        val mFragmentManager = fragmentManager
        val signInFragment = Login()
        mFragmentManager?.beginTransaction()?.apply {
            replace(R.id.MainFragment, signInFragment, Login::class.java.simpleName)
            addToBackStack(null)
            commit()
        }
    }

}