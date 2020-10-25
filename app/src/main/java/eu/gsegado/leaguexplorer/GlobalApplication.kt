package eu.gsegado.leaguexplorer

import android.app.Application
import eu.gsegado.leaguexplorer.modules.DomainModule
import eu.gsegado.leaguexplorer.modules.NetModule
import eu.gsegado.leaguexplorer.modules.PresenterModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GlobalApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GlobalApplication)
            modules(
                NetModule.modules,
                DomainModule.modules,
                PresenterModule.modules,
            )
        }
    }

}