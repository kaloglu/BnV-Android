@file:JvmName("Utility")

package com.kaloglu.bedavanevar.utils.extensions

import com.kaloglu.bedavanevar.domain.repository.base.BaseRepository

/**
 * Created by kaloglu on 6.01.2019.
 */

internal val String.Companion.empty
    get() = ""

inline fun <reified C : Any> C?.checkInjection() =
        checkNotNull(this) {
            throwProvidingError<C>()
        }

inline fun <reified C : BaseRepository> C?.checkInjection() =
        checkNotNull(this) {
            throwProvidingError<C>("ListPresenter")
        }

inline fun <reified C : Any> throwProvidingError(PresenterType: String = "Presenter"): String {
    val simpleClassName = C::class.java.simpleName
    val firstChar = simpleClassName.first()
    val firstCharLowerCase = simpleClassName.replaceFirst(firstChar, firstChar.toLowerCase())
    return "you should add \"$firstCharLowerCase: $simpleClassName\" to providing $PresenterType method at Module"
}
