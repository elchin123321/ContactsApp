package com.ei.android.contactsapp.core

interface Mapper {
    interface Data<S, R>:Mapper {
        fun map(source: S): R
    }

}