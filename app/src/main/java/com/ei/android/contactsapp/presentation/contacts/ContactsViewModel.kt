package com.ei.android.contactsapp.presentation.contacts

import androidx.lifecycle.*
import com.ei.android.contactsapp.domain.contacts.ContactsInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContactsViewModel(
    private val interactor: ContactsInteractor,
    private val mapper:ContactListDomainToUiData
): ViewModel() {

    private val contacts = MutableLiveData<List<ContactUi>>()
    private val starredContacts = MutableLiveData<List<ContactUi>>()



    fun fetchContacts(onlyStarred:Boolean = false){
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = interactor.fetchContacts(onlyStarred)
            val resultUi = mapper.map(resultDomain)
            withContext(Dispatchers.Main){
                if(onlyStarred)
                    starredContacts.value = resultUi
                else
                    contacts.value = resultUi

            }
        }
    }
    fun changeStar(id:String, starred:Boolean,onlyStarred: Boolean = false){
        viewModelScope.launch(Dispatchers.IO) {
            val success = interactor.changeStar(id,starred)
            if(success){
                fetchContacts(onlyStarred)
            }
        }
    }


    fun observeContacts(owner: LifecycleOwner, observer: Observer<List<ContactUi>>){
        contacts.observe(owner, observer)
    }

    fun observeStarredContacts(owner: LifecycleOwner, observer: Observer<List<ContactUi>>){
        starredContacts.observe(owner, observer)
    }
}