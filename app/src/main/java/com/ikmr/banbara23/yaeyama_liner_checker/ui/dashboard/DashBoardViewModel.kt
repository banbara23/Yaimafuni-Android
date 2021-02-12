package com.ikmr.banbara23.yaeyama_liner_checker.ui.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ikmr.banbara23.yaeyama_liner_checker.model.top.Ports
import com.ikmr.banbara23.yaeyama_liner_checker.model.top.TopPort
import com.ikmr.banbara23.yaeyama_liner_checker.repository.TopPortStatusRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * トップに表示するステータスのダッシュボード ViewModel
 */
class DashBoardViewModelImpl : DashBoardViewModel() {

    override val uiState = MutableLiveData<TopPort>()

    private val database: DatabaseReference by lazy {
        FirebaseDatabase.getInstance().reference.ref.child("top_port")
    }

    private val topPortStatusRepository: TopPortStatusRepository by lazy {
        TopPortStatusRepository(database)
    }

    fun fetchTopPortStatus() {
        viewModelScope.launch {
            topPortStatusRepository.getTopPortStatus().collect { state ->
                when (state) {
                    is TopPortStatusState.Success -> uiState.value = state.topPort
                    else -> Timber.e("$state")
                }
            }

        }
    }

    /**
     * 港クリック
     */
    override fun onClickPort(ports: Ports) {
        Timber.d("clicked: $ports")
    }
}

sealed class TopPortStatusState {
    data class Success(val topPort: TopPort) : TopPortStatusState()
    data class Error(val error: Throwable) : TopPortStatusState()
}
