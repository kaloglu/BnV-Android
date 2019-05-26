package com.kaloglu.bedavanevar.presentation.splash

import com.kaloglu.bedavanevar.data.LocalStorage
import com.kaloglu.bedavanevar.data.repository.devicetoken.DeviceTokenRepository
import com.kaloglu.bedavanevar.data.repository.user.UserRepository
import com.kaloglu.bedavanevar.injection.scopes.PerActivity
import com.kaloglu.bedavanevar.presentation.base.BasePresenter
import com.kaloglu.bedavanevar.presentation.base.GenericDependencies
import com.kaloglu.bedavanevar.presentation.interfaces.splash.SplashContract
import javax.inject.Inject


@PerActivity
class SplashPresenter @Inject constructor(
        private val localStorage: LocalStorage,
        private val userRepository: UserRepository,
        private val deviceTokenRepository: DeviceTokenRepository,
        override val genericDependencies: GenericDependencies
) : BasePresenter<SplashContract.View>(), SplashContract.Presenter {


    override fun onLogin() {
        loginUser?.let {
//            registerNewUser(it)
            activityNavigator
                    .toMainActivity()
                    .singleTop()
                    .clearTop()
                    .finishThis()
                    .navigate()
        }

    }

//    private fun registerNewUser(it: FirebaseUser) {
//
//        val userDetail = UserDetail(
//                id = it.uid,
//                fullname = it.displayName,
//                email = it.email,
//                gsm = it.phoneNumber,
//                deviceToken = localStorage.deviceToken,
//                profilePicUrl = it.photoUrl.toString()
//        )
//
//        userRepository
//                .add(userDetail)
//                .addOnCompleteListener { task ->
//                    if (task.isSuccessful) {
//                        userDetail.checkUnregisterToken()
//                    }
//                }
//    }
//
//    private fun UserDetail.checkUnregisterToken() {
//        getView()?.findUnregisteredToken(
//                deviceTokenRepository
//                        .get(
//                                Filters()
//                                        .addEqualTo(
//                                                field = "deviceToken",
//                                                value = deviceToken
//                                        )
//                        )
//        )
//
//    }
//
//    override fun removeUnregisteredToken(id: String) {
//        deviceTokenRepository.remove(id)
//    }

}