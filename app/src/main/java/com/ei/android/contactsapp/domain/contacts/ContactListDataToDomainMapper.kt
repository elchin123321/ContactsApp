package com.ei.android.contactsapp.domain.contacts

import com.ei.android.contactsapp.core.Mapper
import com.ei.android.contactsapp.data.contacts.ContactData
import com.ei.android.contactsapp.data.contacts.ContactDataToDomainMapper

interface ContactListDataToDomainMapper :
    Mapper.Data<List<ContactData>, List<ContactDomain>> {
    class Base(private val mapper: ContactDataToDomainMapper)
        :ContactListDataToDomainMapper{
        override fun map(source: List<ContactData>): List<ContactDomain> {
            return source.map { data ->
                data.map(mapper)
            }
        }
    }
}