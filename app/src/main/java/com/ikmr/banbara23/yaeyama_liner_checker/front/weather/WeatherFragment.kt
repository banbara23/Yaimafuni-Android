package com.ikmr.banbara23.yaeyama_liner_checker.front.weather

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ikmr.banbara23.yaeyama_liner_checker.R
import com.ikmr.banbara23.yaeyama_liner_checker.core.BaseFragment
import com.ikmr.banbara23.yaeyama_liner_checker.databinding.WeatherFragmentBinding
import com.ikmr.banbara23.yaeyama_liner_checker.model.weather.WeatherViewModel
import com.ikmr.banbara23.yaeyama_liner_checker.utils.CustomTabUtil

/**
 * A placeholder fragment containing a simple view.
 */
class WeatherFragment : BaseFragment(), WeatherView {

    private lateinit var presenter: WeatherPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<WeatherFragmentBinding>(inflater, R.layout.weather_fragment, container, false)

        val today = WeatherViewModel()
        val tomorrow = WeatherViewModel()
        binding.today = today
        binding.tomorrow = tomorrow

        presenter = WeatherPresenter(today, tomorrow)
        binding.presenter = presenter
        presenter.attachView(this)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override fun openBrowser() {
        CustomTabUtil.start(activity, getString(R.string.weather_open_url))
    }

    override fun getContext(): Context? {
        return this.activity
    }

    companion object {

        fun NewInstance(): WeatherFragment {
            return WeatherFragment()
        }
    }
}