package retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass


private val gsonConverter: GsonConverterFactory = GsonConverterFactory.create()

class RetroInit(url: String) {

    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(gsonConverter)
            .build()

    fun <T : Any> create(clazz: KClass<T>): T = retrofit.create(clazz.java)

}

