package me.phompang.fourquare_api

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import me.phompang.fourquare_api.model.Result
import me.phompang.fourquare_api.model.user.CompleteUser

class MainActivity : AppCompatActivity() {

    val CLIENT_ID: String = "UD1H1DTUTCMBFYHVLRMYDTDMBYEYHALSNEZD4ROKLOR4PSKX"
    val CLIENT_SECRET: String = "55TQZ30KP2KP2FQOJQO3H14T0BJCPRJSBA4MGMM21K3CR3UG"
    val REDIRECT_URL: String = "foursquare-test-app://xyz"

    val foursquareApi: FoursquareApi = FoursquareApi(CLIENT_ID, CLIENT_SECRET, REDIRECT_URL)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(
                    "https://foursquare.com/oauth2/authenticate" +
                            "?client_id=" + CLIENT_ID +
                            "&response_type=code" +
                            "&redirect_uri=" + REDIRECT_URL)
            startActivity(intent)
        }

        user.setOnClickListener {
            foursquareApi.getUser("self")
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onNext = {
                                val type = Types.newParameterizedType(Result::class.java, CompleteUser::class.java)
                                longLog(Moshi.Builder().build().adapter<Result<CompleteUser>>(type).toJson(it))
                                code.text = it.toString()
                            },
                            onError = {
                                Toast.makeText(baseContext, it.message, Toast.LENGTH_LONG).show()
                            })
        }
    }

    override fun onResume() {
        super.onResume()
        val uri: Uri? = intent.data
        if (uri != null) {
            val codeParam: String? = uri.getQueryParameter("code")
            if (codeParam != null) {
                Log.d("code", codeParam)
                foursquareApi.authenticateCode(codeParam)
                code.text = foursquareApi.accessToken
            }
        }
    }

    fun longLog(str: String) {
        if (str.length > 1000) {
            Log.d("user", str.substring(0, 1000))
            longLog(str.substring(1000))
        } else
            Log.d("user", str)
    }
}
