package com.ei.android.contactsapp.presentation.contacts

import com.ei.android.contactsapp.core.Abstract
import com.ei.android.contactsapp.presentation.contacts.adapter.ToUiMapper

data class ContactUi(
    private val id: String,
    private val lookupKey: String,
    private val name: String,
    private val starred:Boolean,
    private val photoUri:String?
):Abstract<Unit, ToUiMapper> {

    override fun map(mapper: ToUiMapper) = mapper.map(name, starred, photoUri)
}
