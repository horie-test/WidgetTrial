package com.example.widgettrial.ui.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.Toast
import com.example.widgettrial.MainActivity
import com.example.widgettrial.R

class WidgetProvider : AppWidgetProvider() {

    /*
     * 呼ばれるケースは以下
     * 設定ファイルで指定された間隔でウィジェットが更新されるとき
     * ユーザがウィジェットを追加したとき
     * ウィジェットレイアウト上のイベントが発火した時
     */
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // このプロバイダに属する全てのウィジェットに対して適応する
        appWidgetIds.forEach { appWidgetId ->
            // イベント発火時に遷移するアクティビティのインテントを作成
            val pendingIntent: PendingIntent = Intent(context, MainActivity::class.java)
                .let { intent ->
                    PendingIntent.getActivity(context, 0, intent, 0)
                }

            // 配置されているウィジェットレイアウトのイベントを定義
            val views: RemoteViews = RemoteViews(
                context.packageName,
                R.layout.view_widget_trial
            ).apply {
                setOnClickPendingIntent(R.id.button, pendingIntent)
            }

            // AppWidgetManager から更新を促す
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    // ウィジェットに変更が加わった際のコールバック
    override fun onAppWidgetOptionsChanged(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetId: Int,
        newOptions: Bundle?
    ) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions)

        Toast.makeText(context, "ウィジェットが変更された", Toast.LENGTH_SHORT).show()
    }

    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        super.onDeleted(context, appWidgetIds)

        Toast.makeText(context, "ウィジェットが削除された", Toast.LENGTH_SHORT).show()
    }

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)

        Toast.makeText(context, "ウィジェットが作成された", Toast.LENGTH_SHORT).show()
    }

}