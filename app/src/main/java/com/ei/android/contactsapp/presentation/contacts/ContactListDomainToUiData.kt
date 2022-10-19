package com.ei.android.contactsapp.presentation.contacts

import com.ei.android.contactsapp.core.Mapper
import com.ei.android.contactsapp.domain.contacts.ContactDomain
import com.ei.android.contactsapp.domain.contacts.ContactDomainToUiMapper

interface ContactListDomainToUiData:Mapper.Data<List<ContactDomain>,List<ContactUi>> {

    class Base(private val mapper: ContactDomainToUiMapper):ContactListDomainToUiData {
        override fun map(source: List<ContactDomain>): List<ContactUi> {
            return source.map { data->
                data.map(mapper)
            }
        }
    }
}