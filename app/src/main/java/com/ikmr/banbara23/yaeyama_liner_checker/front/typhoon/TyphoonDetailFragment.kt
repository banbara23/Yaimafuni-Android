package com.ikmr.banbara23.yaeyama_liner_checker.front.typhoon

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ikmr.banbara23.yaeyama_liner_checker.R
import com.ikmr.banbara23.yaeyama_liner_checker.api.ApiClient
import com.ikmr.banbara23.yaeyama_liner_checker.databinding.TyphoonDetailListBinding
import com.ikmr.banbara23.yaeyama_liner_checker.model.Typhoon
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber

class TyphoonDetailFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null

    private val apiClient = ApiClient()
    private val disposable = CompositeDisposable()

    private lateinit var binding: TyphoonDetailListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.typhoon_detail_list, container, false)
        binding.list.adapter = TyphoonRecyclerViewAdapter(listOf(), listener)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchTyphoon()
    }

    /**
     * 台風を取得
     */
    private fun fetchTyphoon() {
        disposable.add(
            apiClient
                    .typhoon
                    .subscribeWith(
                        object : ResourceSubscriber<List<Typhoon>>() {
                            override fun onNext(typhoonList: List<Typhoon>) {
                                Log.d("fetchTyphoon", typhoonList.toString())
                                bindTyphoon(typhoonList)
                            }

                            override fun onError(t: Throwable) {
                                Log.d("fetchTyphoon", t.toString())
                            }

                            override fun onComplete() {
                            }
                        })
        )
    }

    private fun bindTyphoon(typhoonList: List<Typhoon>) {
        val adapter = binding.list.adapter as TyphoonRecyclerViewAdapter
        adapter.updateData(typhoonList)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            // throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
        disposable.dispose()
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: Typhoon?)
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
                TyphoonDetailFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }
}
