package base

import android.os.Bundle
import android.viewbinding.ViewBinding
import custom.bindView


abstract class ActBind<Binding : ViewBinding> : ActBase() {

    abstract val binding: Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.onBoundView()
    }

    open fun Binding.onBoundView() {}

    inline fun <reified B : ViewBinding> viewBind() = lazy { bindView(B::class) }

}


