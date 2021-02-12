package com.ikmr.banbara23.yaeyama_liner_checker.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.ikmr.banbara23.yaeyama_liner_checker.model.top.PortStatus
import com.ikmr.banbara23.yaeyama_liner_checker.model.top.Ports
import com.ikmr.banbara23.yaeyama_liner_checker.model.top.Status
import com.ikmr.banbara23.yaeyama_liner_checker.model.top.TopPort
import com.ikmr.banbara23.yaeyama_liner_checker.ui.dashboard.TopPortStatusState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

/**
 * トップ用ステータス一覧 Repository
 */
class TopPortStatusRepository(private val dbRef: DatabaseReference) {

    /**
     * 天気を取得
     */
    @Suppress("EXPERIMENTAL_API_USAGE")
    fun getTopPortStatus(): Flow<TopPortStatusState> = callbackFlow { // callbackFlowの戻り値はFlow型
        // DBへの接続
        dbRef.addValueEventListener(object : ValueEventListener {
            // 正常に取得できた
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.getValue(TopPort::class.java)?.let { response ->
                    Timber.d(response.toString())
                    // offerで購読側に値を流せる
                    offer(TopPortStatusState.Success(response))
                }
            }

            // エラー
            override fun onCancelled(error: DatabaseError) {
                offer(TopPortStatusState.Error(error.toException()))
            }
        })
        awaitClose {
            // DatabaseReference は自動で破棄される
        }
    }

    /**
     * テスト表示用
     */
    fun getDummyTopPortStatus(): Flow<TopPortStatusState> = flow {
        while (true) {
            delay(2000)
            // 値を送信
            emit(TopPortStatusState.Success(createDummyData()))
        }

    }

    private fun createDummyData(): TopPort {
        val dummy = TopPort(
            hateruma = Ports(
                anei = PortStatus(
                    portCode = "a",
                    portName = "aE,",
                    comment = "teste",
                    status = Status(
                        text = "normal",
                        code = "nomarl",
                    )
                )
            )
        )

        return dummy
    }
}


