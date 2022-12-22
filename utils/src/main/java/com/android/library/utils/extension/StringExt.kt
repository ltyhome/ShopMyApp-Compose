import org.json.JSONException
import org.json.JSONObject

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