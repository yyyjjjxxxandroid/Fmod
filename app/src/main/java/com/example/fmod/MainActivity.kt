package com.example.fmod

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.fmod.databinding.ActivityMainBinding
import com.google.android.material.transition.MaterialContainerTransform.FadeMode
import org.fmod.FMOD

class MainActivity : AppCompatActivity() {


    private val MODE_NORMAL = 0 // 正常
    private val MODE_LUOLI = 1 // 萝莉
    private val MODE_DASHU = 2 // 大叔
    private val MODE_JINGSONG = 3 // 惊悚
    private val MODE_GAOGUAI = 4 // 搞怪
    private val MODE_KONGLING = 5 // 空灵
    private lateinit var binding: ActivityMainBinding
   private val PATH = "file:///android_asset/derry.mp3"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
  FMOD.init(this)
        // Example of a call to a native method


    }
    // 给native调用的   方法签名： (Ljava/lang/String;)V
    fun playerEnd(nativeMessageContent: String) {
        Toast.makeText(this, "" + nativeMessageContent, Toast.LENGTH_SHORT).show()
    }
    override fun onDestroy() {
        super.onDestroy()
        FMOD.close()
    }
    // 六个 点击事件
    fun onFix(view: View) {
        when (view.id) {
            R.id.btn_normal -> voiceChangeNative(MODE_NORMAL, PATH)
            R.id.btn_luoli -> voiceChangeNative(MODE_LUOLI, PATH)
            R.id.btn_dashu -> voiceChangeNative(MODE_DASHU, PATH)
            R.id.btn_jingsong -> voiceChangeNative(MODE_JINGSONG, PATH)
            R.id.btn_gaoguai -> voiceChangeNative(MODE_GAOGUAI, PATH)
            R.id.btn_kongling -> voiceChangeNative(MODE_KONGLING, PATH)
        }
    }
    /**
     * A native method that is implemented by the 'fmod' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI()
     external fun voiceChangeNative(mode: Int, path: String)
    companion object {
        // Used to load the 'fmod' library on application startup.
        //APK lib 平台 libnative-libs.so 里面的代码
        //libnative-libs.so 里必须包含fmod代码才能调音，借助cmake集成进去
        init {
            System.loadLibrary("native-lib")
        }
    }
}