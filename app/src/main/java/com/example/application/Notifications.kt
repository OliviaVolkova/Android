package com.example.application

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

object Notifications {

        private const val CHANNEL_ID: String = "channelId"
        private const val CHANNEL_NAME: String = "channel"
        private const val NOTIFICATION_ID: Int = 14212
        private var manager: NotificationManagerCompat? = null

        private fun pending(actionRes: String,context: Context) : PendingIntent{
                val intent = Intent(context, MusicService::class.java)
                intent.action = actionRes
                return PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        private fun channel(context: Context){
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                        val channel = NotificationChannel(
                                CHANNEL_ID, CHANNEL_NAME,
                                NotificationManager.IMPORTANCE_DEFAULT).apply {
                                lightColor = Color.BLACK
                                enableLights(true)
                        }
                        val channelManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                        channelManager.createNotificationChannel(channel)
                }
        }

        fun startNotification(context: Context, audio: Music, isStart: Boolean):Notification{
                channel(context)
                val intent = Intent(context, MainActivity::class.java)
                val content = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
                val stop = pending("STOP",context)
                val start = pending("START",context)
                val previous = pending("PREVIOUS",context)
                val next = pending("NEXT",context)
                val notification =
                        if(isStart) NotificationCompat.Builder(context, CHANNEL_ID).apply {
                                setStyle(androidx.media.app.NotificationCompat.MediaStyle())
                                addAction(R.drawable.previous, "", previous)
                                addAction(R.drawable.stop, "", stop)
                                addAction(R.drawable.next, "", next)
                                setContentText(audio.author)
                                setLargeIcon(BitmapFactory.decodeResource(context.resources, audio.icon))
                                setSmallIcon(R.drawable.ic_launcher_foreground)
                                setContentIntent(content)
                                setContentTitle(audio.name)
                                priority = NotificationCompat.PRIORITY_DEFAULT
                        }.build()
                        else NotificationCompat.Builder(context, CHANNEL_ID).apply {
                                setStyle(androidx.media.app.NotificationCompat.MediaStyle())
                                addAction(R.drawable.previous, "", previous)
                                addAction(R.drawable.start, "", start)
                                addAction(R.drawable.next, "", next)
                                setContentText(audio.author)
                                setLargeIcon(BitmapFactory.decodeResource(context.resources, audio.icon))
                                setSmallIcon(R.drawable.ic_launcher_foreground)
                                setContentIntent(content)
                                setContentTitle(audio.name)
                                priority = NotificationCompat.PRIORITY_DEFAULT
                        }.build()
                if(manager == null) manager = NotificationManagerCompat.from(context)
                manager?.notify(NOTIFICATION_ID, notification)
                return notification
        }





}