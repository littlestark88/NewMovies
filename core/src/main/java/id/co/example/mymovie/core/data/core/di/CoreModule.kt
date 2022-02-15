package id.co.example.core.data.core.di


import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import id.co.example.core.data.core.helper.AppExecutors
import id.co.example.core.data.core.helper.Constant.BASE_URL
import id.co.example.core.data.source.local.LocalDataSource
import id.co.example.core.data.source.local.MovieDatabase
import id.co.example.core.data.source.remote.MovieRepository
import id.co.example.core.data.source.remote.RemoteDataSource
import id.co.example.core.data.source.remote.network.ApiService
import id.co.example.core.domain.repository.IMovieRepository
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieDatabase>().movieNowPlayingDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java, "Movie.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        val chuckCollector = ChuckerCollector(
            context = androidContext(),
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )
        val chuckInterceptor = ChuckerInterceptor.Builder(androidContext())
            .collector(chuckCollector)
            .maxContentLength(250_000L)
            .alwaysReadResponseBody(true)
            .build()
        OkHttpClient.Builder()
            .addInterceptor(chuckInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository> { MovieRepository(get(),get(), get()) }
}