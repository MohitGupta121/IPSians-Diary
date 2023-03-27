package com.mohit.ipsians_diary.dagger

import com.mohit.ipsians_diary.activities.Navigation
import dagger.Component

@Component(modules = [FirebaseModule::class])
interface CollegeComponent {

    fun injectNavigation(navigation: Navigation)
}