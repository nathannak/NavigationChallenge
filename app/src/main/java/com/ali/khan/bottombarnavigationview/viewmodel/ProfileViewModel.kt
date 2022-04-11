package com.ali.khan.bottombarnavigationview.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel: ViewModel() {

    var name = MutableLiveData<String>()
    val lastName = MutableLiveData<String>()

}