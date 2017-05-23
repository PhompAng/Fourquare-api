package me.phompang.fourquare_api

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val CLIENT_ID: String = "UD1H1DTUTCMBFYHVLRMYDTDMBYEYHALSNEZD4ROKLOR4PSKX"
    val CLIENT_SECRET: String = "55TQZ30KP2KP2FQOJQO3H14T0BJCPRJSBA4MGMM21K3CR3UG"
    val REDIRECT_URL: String = "foursquare-test-app://xyz"

    val foursquareApi: FoursquareApi = FoursquareApi(CLIENT_ID, CLIENT_SECRET, REDIRECT_URL)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth.setOnClickListener({
            val intent: Intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(
                    "https://foursquare.com/oauth2/authenticate" +
                            "?client_id=" + CLIENT_ID +
                            "&response_type=code" +
                            "&redirect_uri=" + REDIRECT_URL)
            startActivity(intent)
        })
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
}
