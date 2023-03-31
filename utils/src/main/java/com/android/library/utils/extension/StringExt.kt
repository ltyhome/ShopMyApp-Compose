import android.graphics.Bitmap
import android.graphics.Color
import com.android.library.utils.moshi
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Types
import org.json.JSONException
import org.json.JSONObject
import java.lang.reflect.ParameterizedType
import java.nio.charset.Charset
import java.security.MessageDigest
import java.util.*

fun String.formatJson(indentSpaces: Int = 4): String {
    try {
        var i = 0
        val len = this.length
        while (i < len) {
            val c = this[i]
            if (c == '{') {
                return JSONObject(this).toString(indentSpaces)
            } else if (c == '[') {
                return JSONObject(this).toString(indentSpaces)
            } else if (!Character.isWhitespace(c)) {
                return this
            }
            i++
        }
    } catch (e: JSONException) {
        e.printStackTrace()
    }
    return this
}

inline fun <reified T> String.fromJsonToList(): MutableList<T>? {
    val parameterizedType = Types.newParameterizedType(MutableList::class.java, T::class.java)
    return fromJson<MutableList<T>>(parameterizedType)
}

inline fun <reified T> String.fromJson(parameterizedType: ParameterizedType): T? {
    val adapter = moshi.adapter<T>(parameterizedType)
    return fromJson(adapter)
}

inline fun <reified T> String.fromJson(adapter: JsonAdapter<T>): T? {
    try {
        return adapter.fromJson(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}

inline fun <reified T> String.fromJson(): T? {
    return moshi.adapter(T::class.java).fromJson(this)
}

private val hexDigits =
    charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')

fun String.md5(): String {
    try {
        val digest = MessageDigest.getInstance("MD5")
        val btInput = toByteArray()
        digest.update(btInput)
        // 获得密文
        val md = digest.digest()
        // 把密文转换成十六进制的字符串形式
        val j = md.size
        val str = CharArray(j * 2)
        var k = 0
        for (i in 0 until j) {
            val byte0 = md[i]
            str[k++] = hexDigits[byte0.toInt() ushr 4 and 0xf]
            str[k++] = hexDigits[byte0.toInt() and 0xf]
        }
        return String(str).uppercase()
    } catch (e: Exception) {
        e.printStackTrace()
        return ""
    }
}

fun String.createQRCode(
    width: Int = 300,
    height: Int = 300
): Bitmap? {
    var bitmap: Bitmap? = null
    val result: BitMatrix?
    val multiFormatWriter = MultiFormatWriter()
    try {
        val hints =
            Hashtable<EncodeHintType, Any?>()
        hints[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.H//这里调整二维码的容错率
        hints[EncodeHintType.MARGIN] = 0
        result = multiFormatWriter.encode(
            String(this.toByteArray(charset("UTF-8")), Charset.forName("ISO-8859-1")),
            BarcodeFormat
                .QR_CODE,
            width,
            height,
            hints
        )
        val w = result.width
        val h = result.height
        val pixels = IntArray(w * h)
        for (y in 0 until h) {
            val offset = y * w
            for (x in 0 until w) {
                pixels[offset + x] = if (result.get(
                        x,
                        y
                    )
                ) Color.BLACK else Color.WHITE
            }
        }
        bitmap =
            Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, w, 0, 0, w, h)
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
    return bitmap
}