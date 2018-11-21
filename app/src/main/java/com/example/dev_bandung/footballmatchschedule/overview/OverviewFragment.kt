package com.example.dev_bandung.footballmatchschedule.overview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dev_bandung.footballmatchschedule.R
import kotlinx.android.synthetic.main.fragment_overview.view.*

class OverviewFragment : Fragment() {
    private lateinit var v: View
    private var temp : String? = "default"

    companion object {
        private val ARG_SECTION_NUMBER = "section_param"
        fun newInstance(sectionParam: String?): OverviewFragment {
            val fragment = OverviewFragment()
            val args = Bundle()
            args.putString(ARG_SECTION_NUMBER, sectionParam)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_overview, container, false)
        temp = arguments?.getString(ARG_SECTION_NUMBER)
        v.txtDescription.text = temp
        return v
    }
}
