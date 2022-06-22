package com.example.anifox.domain.model.user

import com.example.anifox.domain.model.common.ShikimoriImage

interface UserEntity {
    val id: Long
}

interface ShikimoriEntity {
    val shikimoriId: Long?
}

interface ShikimoriContentEntity : ShikimoriEntity {
    val image: ShikimoriImage?
    val name: String
    val nameRu: String?
}
