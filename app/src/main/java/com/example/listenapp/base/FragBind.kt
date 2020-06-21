package base


import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.ViewBinding


abstract class FragBind<T : ViewBinding> : FragBase() {

    abstract val binding: T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?) =
            binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.onBoundView()
    }

    abstract fun T.onBoundView()
}