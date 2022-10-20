package com.ei.android.contactsapp.presentation.permissions

import android.Manifest.permission.*

sealed class Permissions(val permission:String) {

    object ReadContacts:Permissions(READ_CONTACTS)
    object ReadCallLog:Permissions(READ_CALL_LOG)

    companion object{
        fun from(permission: String) = when(permission){
            READ_CONTACTS -> ReadContacts.permission
            READ_CALL_LOG -> ReadCallLog.permission
            else -> throw IllegalArgumentException("Unknown permission: $permission")
        }
    }

}