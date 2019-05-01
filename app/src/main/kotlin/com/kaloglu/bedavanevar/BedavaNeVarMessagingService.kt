package com.kaloglu.bedavanevar

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.kaloglu.bedavanevar.data.LocalStorage
import com.kaloglu.bedavanevar.mobileui.splash.SplashActivity
import javax.inject.Inject

class BedavaNeVarMessagingService : FirebaseMessagingService() {

    private var deeplink: String? = null
    private var notificationId: Int = 0
    private lateinit var intent: Intent

    @Inject
    lateinit var localStorage: LocalStorage
//    private var localStorage = LocalStorage(this)

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: ${remoteMessage?.from}")

        // Check if commentText contains a notificationData payload.
        remoteMessage?.data?.isNotEmpty()?.let {
            val notificationData = remoteMessage.data
            try {
                notificationId = Integer.parseInt(notificationData["id"]!!)
                deeplink = notificationData["url"]
            } catch (ignored: Exception) {
            }

            Log.d(TAG, "Message notificationData payload: $notificationData")
        }

        // Check if message contains a notification payload.
        remoteMessage?.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
            sendNotification_old(it, deeplink)
        }

    }

    private fun sendNotification(notification: RemoteMessage.Notification, deeplink: String?) {
//        val uri = Uri.parse(deeplink).buildUpon()
        val notificationClass = SplashActivity::class.java
        val resultIntent = Intent(this, notificationClass)
        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val mBuilder: Notification.Builder
        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your app to the Home screen.
        val stackBuilder = TaskStackBuilder.create(this)
        val resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)

//        resultIntent.data = uri.build()

        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(notificationClass)
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent)

        // The id of the channel.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mBuilder = Notification.Builder(this, this.getString(R.string.default_notification_channel_id))
            mBuilder.setChannelId(this.getString(R.string.default_notification_channel_id))
        } else {
            mBuilder = Notification.Builder(this)
        }

        mBuilder
                .setSmallIcon(R.drawable.ic_stat_ic_notification)
                .setContentTitle(notification.title)
                .setContentText(notification.body)

        mBuilder.setContentIntent(resultPendingIntent)

        // mNotificationId is a unique integer your app uses to identify the
        // notification. For example, to cancel the notification, you can pass its ID
        // number to NotificationManager.cancel().
        mNotificationManager.notify(notificationId, mBuilder.build())
    }

    override fun onNewToken(refreshedToken: String?) {
        super.onNewToken(refreshedToken)

        localStorage.setToken(refreshedToken)
    }


    private fun sendNotification_old(notification: RemoteMessage.Notification?, deeplink: String?) {
        //TODO("will change with DeepLinkActivity)
        val intent = Intent(this, SplashActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        if (deeplink != null && !deeplink.isEmpty()) {
            val uri = Uri.parse(deeplink).buildUpon()
            intent.data = uri.build()
        }
        val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent, PendingIntent.FLAG_ONE_SHOT)

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val message = notification!!.body + " url:" + deeplink

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(
                notificationId,
                NotificationCompat.Builder(
                        this,
                        this.getString(R.string.default_notification_channel_id)
                )
                        .setSmallIcon(R.drawable.ic_stat_ic_notification)
                        .setContentTitle(notification.title)
                        .setContentText(message)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent)
                        .build()
        )
    }

    companion object {

        private const val TAG = "NotificationService"
    }

}
