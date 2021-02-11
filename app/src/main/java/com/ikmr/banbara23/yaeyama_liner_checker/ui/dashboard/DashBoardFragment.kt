package com.ikmr.banbara23.yaeyama_liner_checker.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ikmr.banbara23.yaeyama_liner_checker.R

/**
 * トップに表示するステータスのダッシュボード画面
 */
class DashBoardFragment : Fragment() {

    private lateinit var viewModel: DashBoardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dash_board_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DashBoardViewModel::class.java)
        // TODO: Use the ViewModel
    }

    companion object {
        fun newInstance() = DashBoardFragment()
    }
}