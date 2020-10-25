package eu.gsegado.leaguexplorer.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import eu.gsegado.leaguexplorer.R
import eu.gsegado.leaguexplorer.presentation.home.HomeSearchActivity
import kotlinx.android.synthetic.main.activity_splashscreen.*

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splashscreen)

        animation_ball.addAnimatorUpdateListener {
            if (it.animatedFraction >= 1.0) {
                startActivity(Intent(this, HomeSearchActivity::class.java))
                it.cancel()
                finish()
            }
        }
    }

    override fun onDestroy() {
        animation_ball.removeAllAnimatorListeners()
        animation_ball.removeAllUpdateListeners()
        super.onDestroy()
    }
}