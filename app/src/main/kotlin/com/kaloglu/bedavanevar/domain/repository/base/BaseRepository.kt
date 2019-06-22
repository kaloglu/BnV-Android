package com.kaloglu.bedavanevar.domain.repository.base

import com.google.firebase.firestore.DocumentReference
import com.kaloglu.bedavanevar.domain.repository.interfaces.Repository

abstract class BaseRepository : Repository {
    override lateinit var documentRef: DocumentReference
}
