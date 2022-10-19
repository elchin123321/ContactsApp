package com.ei.android.contactsapp.core

import android.app.Application
import com.ei.android.contactsapp.data.contacts.BaseContactsRepository
import com.ei.android.contactsapp.data.contacts.ContactsDataSource
import com.ei.android.contactsapp.domain.contacts.BaseContactDataToDomainMapper
import com.ei.android.contactsapp.domain.contacts.ContactListDataToDomainMapper
import com.ei.android.contactsapp.domain.contacts.ContactsInteractor
import com.ei.android.contactsapp.domain.contacts.ContactsRepository
import com.ei.android.contactsapp.presentation.contacts.BaseContactDomainToUiData
import com.ei.android.contactsapp.presentation.contacts.ContactListDomainToUiData
import com.ei.android.contactsapp.presentation.contacts.ContactsViewModel

class ContactsApp:Application() {
    lateinit var viewModel: ContactsViewModel
    override fun onCreate() {
        super.onCreate()

        viewModel = ContactsViewModel(
            ContactsInteractor(
                BaseContactsRepository(
                    ContactsDataSource(this)
                ),
                ContactListDataToDomainMapper.Base(BaseContactDataToDomainMapper())
            ),
            ContactListDomainToUiData.Base(BaseContactDomainToUiData())
        )
    }
}