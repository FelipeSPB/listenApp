package custom

import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.res.Resources.getSystem
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.viewbinding.ViewBinding
import android.widget.*
import android.widget.Toast.LENGTH_SHORT
import com.example.listenapp.R
import com.example.listenapp.custom.RecyclerAdapter
import com.example.listenapp.custom.recyclerAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder


import com.squareup.picasso.Picasso
import custom.adapter.ItemViewBuilder
import java.io.*
import java.text.Normalizer

import kotlin.reflect.KClass
import kotlin.reflect.KFunction0

fun String.normalize(): String {
    return Normalizer.normalize(toLowerCase(), Normalizer.Form.NFD)
            .replace("[\\p{InCombiningDiacriticalMarks}]".toRegex(), "").trim()
}

fun <T : Any> Context.launchActivity(clazz: Class<T>, extras: Bundle.() -> Unit = {}) {
    val intent = Intent(this, clazz)
    startActivity(intent.putExtras(Bundle().apply(extras)))
}

inline fun <reified Model : ViewModel> Fragment.viewModel(): Model =
        ViewModelProviders.of(this).get(Model::class.java)

operator fun <T> Collection<T>.get(index: Int): T {
    forEachIndexed { indexed, element -> if (indexed == index) return element }
    throw IndexOutOfBoundsException()
}

inline fun <reified Builder : ItemViewBuilder<*, *>>
        RecyclerView.setup(list: Collection<*>) =
        recyclerAdapter<Builder>(list).apply { adapter = this }

val RecyclerView.recyclerAdapter get() = adapter as RecyclerAdapter?

fun RecyclerView.update() = recyclerAdapter?.notifyDataSetChanged()

fun <T> MutableList<T>.update(collection: MutableList<T>) {
    clear()
    collection.forEach { add(it) }
}

fun Activity.hideKeyBoard() {
    (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?)
            ?.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
}

val Int.isEven get() = this % 2 == 0

val Int.isOdd get() = this % 2 != 0

val EditText.string get() = text.toString()

val EditText.int get() = string.toInt()

private var toast: Toast? = null

fun Context.toast(message: String) {
    toast?.cancel()
    toast = Toast.makeText(this, message, LENGTH_SHORT)
    toast?.show()
}

fun String.isDigit(): Boolean {
    for (char in this) {
        if (!char.isDigit()) return false
    }
    return true
}

fun <T : Comparable<T>> listOfRange(iterable: Iterable<T>): MutableList<T> {
    val list = mutableListOf<T>()
    iterable.forEach { list.add(it) }
    return list
}

@Suppress("UNCHECKED_CAST") // Converts Pixel value to DensityPixel value
val <N : Number> N.dp
    get() = (toFloat() * getSystem().displayMetrics.density) as N
val <N : Number> N.sp
    get() = (toFloat() * getSystem().displayMetrics.scaledDensity) as N

fun onTextSubmit(block: (String) -> Unit) = object : SearchView.OnQueryTextListener {
    override fun onQueryTextSubmit(dota: String): Boolean {
        block(dota)
        return false
    }

    override fun onQueryTextChange(s: String): Boolean {
        return false
    }
}

fun Context.newButton(style: Int = 0) =
        Button(this, null, 0, style)

// reflexão é uma forma da linguagem referenciar a própria linguagem
// é uma forma de via código saber o que define uma classe
inline fun <reified ViewType : View> Context.new(
        style: Int = R.style.Button,
        setup: ViewType.() -> Unit = {}
): ViewType {
    val view = ViewType::class.java.getConstructor(
            Context::class.java,
            AttributeSet::class.java,
            Int::class.java,
            Int::class.java
    ).newInstance(this, null, 0, style)
    view.setup()
    return view
}

fun <V : View> V.onClick(function: V.() -> Unit = {}) {
    setOnClickListener { function() }
}

fun <V : View> V.onClick(function: KFunction0<*>) {
    setOnClickListener { function() }
}


val Context.inflater get() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

@Suppress("UNCHECKED_CAST")
inline fun <reified Binding : ViewBinding> IContext.viewBind() = lazy {
    Binding::class.java.getMethod("inflate", LayoutInflater::class.java)
            .invoke(null, activity.inflater) as Binding
}

@Suppress("UNCHECKED_CAST")
fun <Binding : ViewBinding> Context.viewBind(klass: KClass<Binding>) =
        klass.java.getMethod("inflate", LayoutInflater::class.java)
                .invoke(null, inflater) as Binding

fun Context.shareText(text: String) {
    startActivity(
            Intent.createChooser(
                    Intent().apply {
                        action = Intent.ACTION_SEND
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, text)
                    }, "Whiskas Sache"
            )
    )
}

infix fun ImageView.setImageFromURL(url: Any?) = Picasso.get().load(url.toString()).into(this)

fun ImageView.setImageFromURLwError(url: Any?, placeholder: Int) = Picasso.get().load(url.toString()).error(placeholder).into(this)

val <T : Parcelable> T.toJson
    get(): String = GsonBuilder().setPrettyPrinting()
            .create()
            .toJson(this)

fun Context.toFile(text: String, name: String = "config.txt") = try {
    OutputStreamWriter(openFileOutput(name, MODE_PRIVATE)).run {
        write(text)
        close()
    }
    "Wrote file:\n\n$text"
} catch (ioException: IOException) {
    "File write failed: $ioException"
}

fun Context.fromFile(name: String = "config.txt") = try {
    val input = openFileInput(name)
    val reader = BufferedReader(InputStreamReader(input))
    val builder = StringBuilder()
    while (reader.readLine() != null) {
        builder.append(reader.readLine())
    }
    input.close()
    builder.toString()
} catch (notFound: FileNotFoundException) {
    "File not found: $notFound"
} catch (ioException: IOException) {
    "Can not read file: $ioException"
}

inline fun <reified T : Parcelable>
        T.fromJson(json: String): T = Gson().fromJson(json, T::class.java)

@Suppress("UNCHECKED_CAST")
val <T : Any> Class<T>.klass: KClass<T>
    get() = this::class as KClass<T>