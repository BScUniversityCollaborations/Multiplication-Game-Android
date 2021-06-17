package com.unipi.p17172p17168p17164.multiplicationgame.database

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.unipi.p17172p17168p17164.multiplicationgame.models.Table
import com.unipi.p17172p17168p17164.multiplicationgame.models.User
import com.unipi.p17172p17168p17164.multiplicationgame.ui.activities.SignInActivity
import com.unipi.p17172p17168p17164.multiplicationgame.ui.activities.SignUpActivity
import com.unipi.p17172p17168p17164.multiplicationgame.ui.activities.TablesTestActivity
import com.unipi.p17172p17168p17164.multiplicationgame.utils.Constants

class FirestoreHelper {

    // Access a Cloud Firestore instance.
    private val dbFirestore = FirebaseFirestore.getInstance()

    /**
     * A function to get the user id of current logged user.
     */
    fun getCurrentUserID(): String {
        // An Instance of currentUser using FirebaseAuth
        val currentUser = FirebaseAuth.getInstance().currentUser

        // A variable to assign the currentUserId if it is not null or else it will be blank.
        var currentUserID = ""
        if (currentUser != null) {
            currentUserID = currentUser.uid
        }

        return currentUserID
    }

    /**
     * A function to get the logged user details from from FireStore Database.
     */
    fun getUserDetails(activity: Activity) {

        // Here we pass the collection name from which we wants the data.
        dbFirestore.collection(Constants.COLLECTION_USERS)
            // The document id to get the Fields of user.
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener { document ->

                Log.d(activity.javaClass.simpleName, document.toString())

                // Here we have received the document snapshot which is converted into the User Data model object.
                val user = document.toObject(User::class.java)!!

                val sharedPreferences =
                    activity.getSharedPreferences(
                        Constants.SHARED_PREFERENCES_PREFIX,
                        Context.MODE_PRIVATE
                    )

                // Create an instance of the editor which is help us to edit the SharedPreference.
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString(
                    Constants.LOGGED_IN_EMAIL,
                    user.email
                )
                editor.apply()

                when (activity) {
                    is SignInActivity -> {
                        // Call a function of base activity for transferring the result to it.
                        activity.userLoggedInSuccess()
                    }
                }
            }
            .addOnFailureListener { e ->
                // Hide the progress dialog if there is any error. And print the error in log.
                when (activity) {
                    is SignInActivity -> {
                        activity.hideProgressDialog()
                    }
                }

                Log.e(
                    activity.javaClass.simpleName,
                    "Error while getting user details.",
                    e
                )
            }
    }

    /**
     * A function to make an entry of the registered user in the FireStore database.
     */
    fun registerUser(activity: SignUpActivity, userInfo: User) {

        // The "users" is collection name. If the collection is already created then it will not create the same one again.
        dbFirestore.collection(Constants.COLLECTION_USERS)
            // Document ID for users fields. Here the document it is the User ID.
            .document(userInfo.userId)
            // Here the userInfo are Field and the SetOption is set to merge. It is for if we wants to merge later on instead of replacing the fields.
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {

                // Here call a function of base activity for transferring the result to it.
                activity.userRegistrationSuccess()
            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while registering the user.",
                    e
                )
            }
    }

    /**
     * A function to get the tables list from cloud firestore.
     *
     * @param activity The fragment is passed as parameter as the function is called from fragment and need to the success result.
     */
    fun getTablesList(activity: Activity) {
        // The collection name for PRODUCTS
        dbFirestore.collection(Constants.COLLECTION_TABLES)
            .orderBy(Constants.FIELD_NUMBER, Query.Direction.ASCENDING)
            .get() // Will get the documents snapshots.
            .addOnSuccessListener { document ->

                // Here we get the list of boards in the form of documents.
                Log.d("Tables List", document.documents.toString())

                // Here we have created a new instance for Products ArrayList.
                val tablesList: ArrayList<Table> = ArrayList()

                // A for loop as per the list of documents to convert them into Products ArrayList.
                for (i in document.documents) {

                    val table = i.toObject(Table::class.java)
                    table!!.tableId = i.id

                    tablesList.add(table)
                }
                when (activity) {
                    is TablesTestActivity -> {
                        activity.successTablesList(tablesList)
                    }
                    else -> {}
                }
            }
            .addOnFailureListener { e ->
                Log.e("Get Tables List", "Error while getting tables list.", e)
            }
    }

}