package eu.gsegado.leaguexplorer.modules

import org.junit.Test
import org.koin.core.logger.Level
import org.koin.dsl.koinApplication
import org.koin.test.AutoCloseKoinTest
import org.koin.test.check.checkModules

class ModulesTest : AutoCloseKoinTest() {

    @Test
    fun test_AllModules() {
        koinApplication {
            printLogger(Level.DEBUG)
            modules(listOf(
                DomainModule.modules,
                NetModule.modules,
                PresenterModule.modules)
            )
        }.checkModules()
    }

}
