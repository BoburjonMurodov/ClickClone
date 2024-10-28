package uz.gita.data.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalStorage @Inject constructor(@ApplicationContext context: Context) : SharedPreferenceHelper(context) {
    var isSignIn: Boolean by booleans(false)
    var password: String by strings("")
    var verifyPassword: String by strings("")

    var token: String by strings("")
    var refreshToken: String by strings("")
    var accessToken: String by strings("")

    var userId: Int by ints()
    var parentId: Int by ints()
    var username: String by strings()
    var phone: String by strings()
}