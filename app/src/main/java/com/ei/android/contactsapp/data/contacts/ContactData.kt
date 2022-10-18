package com.ei.android.contactsapp.data.contacts

import com.ei.android.contactsapp.core.Abstract
import com.ei.android.contactsapp.domain.contacts.ContactDomain

data class ContactData(
    private val id: String,
    private val lookupKey: String,
    private val name: String,
    private val starred:Int,
    private val photoUri:String?
):Abstract<ContactDomain,ContactDataToDomainMapper>{
    override fun map(mapper: ContactDataToDomainMapper) = mapper.map(id,lookupKey,name, starred, photoUri)

}