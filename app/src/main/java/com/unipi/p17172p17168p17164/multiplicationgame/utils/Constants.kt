package com.unipi.p17172p17168p17164.multiplicationgame.utils

import com.google.android.material.behavior.SwipeDismissBehavior
import com.google.android.material.snackbar.BaseTransientBottomBar

// Create a custom object to declare all the constant values in a single file. The constant values declared here is can be used in whole application.
/**
 * A custom object to declare all the constant values in a single file. The constant values declared here is can be used in whole application.
 */
object Constants {
    // Default Constants


    // General Constants
    const val TAG: String = "[MultiplicationGame]"
    const val SHARED_PREFERENCES_PREFIX: String = "MultiplicationGamePrefs"
    const val LOGGED_IN_USERNAME: String = "logged_in_username"
    const val SPLASH_SCREEN_DELAY: Long = 1500
    val SNACKBAR_BEHAVIOR = BaseTransientBottomBar.Behavior().apply {
        setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_ANY) }

    // Firebase Constants
    // This is used for the collection name for USERS.
    const val COLLECTION_USERS: String = "users"

    // Fields
    const val FIELD_DATE_ADDED: String = "dateAdded"
    const val FIELD_DATE_REGISTERED: String = "dateRegistered"
    const val FIELD_EMAIL: String = "email"
    const val FIELD_FULL_NAME: String = "fullName"
    const val FIELD_ID: String = "id"
    const val FIELD_NAME: String = "name"
    const val FIELD_PROFILE_COMPLETED: String = "profileCompleted"
    const val FIELD_USER_ID: String = "userId"

    // Intent Extras
    const val EXTRA_REG_USERS_SNACKBAR: String = "extraShowRegisteredUserSnackbar"
    const val EXTRA_PROFILE_NOT_COMPLETED_SNACKBAR: String = "extraShowProfileNotCompletedSnackbar"
    const val EXTRA_USER_EMAIL: String = "extraUserEmail"
}
// END