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

    fun fetchContacts(){
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = interactor.fetchContacts()
            val resultUi = mapper.map(resultDomain)
            withContext(Dispatchers.Main){
                contacts.value = resultUi
            }
        }
    }

    fun observe(owner: LifecycleOwner,observer: Observer<List<ContactUi>>){
        contacts.observe(owner, observer)
    }
}