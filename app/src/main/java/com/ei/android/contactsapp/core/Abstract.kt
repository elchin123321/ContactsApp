package com.ei.android.contactsapp.core

interface Abstract<T,R:Mapper> {
    fun map(mapper: R):T
}