package uz.gita.data.util

import javax.inject.Qualifier


/*
    Created by Boburjon Murodov 27/10/24 at 20:09
*/



@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Auth


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Home



@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Chucker


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Authenticator