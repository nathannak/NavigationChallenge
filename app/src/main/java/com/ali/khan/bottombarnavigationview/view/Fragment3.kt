package com.ali.khan.bottombarnavigationview.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ali.khan.bottombarnavigationview.databinding.Fragment3Binding
import com.ali.khan.bottombarnavigationview.viewmodel.ProfileViewModel

class Fragment3: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = Fragment3Binding.inflate(inflater, container, false)
        val viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding.viewModel=viewModel
        binding.lifecycleOwner = this

        setInitialValues(viewModel, context)
        setupObservers(viewModel)

        return binding.root
    }

    private fun setupObservers(viewModel: ProfileViewModel) {
        viewModel.name.observe(viewLifecycleOwner, {
            if (!it.isNullOrBlank()) {
                val sharedPreference =
                    context?.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
                var editor = sharedPreference?.edit()
                editor?.putString("name", it)
                editor?.commit()
//                Toast.makeText(context, "first name: " + it.toString(), Toast.LENGTH_LONG).show()
            }
        })
        viewModel.lastName.observe(viewLifecycleOwner, {
            if (!it.isNullOrBlank()) {
                val sharedPreference =
                    context?.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
                var editor = sharedPreference?.edit()
                editor?.putString("lastname", it)
                editor?.commit()
//                Toast.makeText(context, "last name: " + it.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setInitialValues(
        viewmodel: ProfileViewModel,
        context: Context?
    ) {
        val sharedPreference = context?.getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)
        viewmodel.name.value = sharedPreference?.getString("name","First name")
        viewmodel.lastName.value = sharedPreference?.getString("lastname","Last name")
    }
}