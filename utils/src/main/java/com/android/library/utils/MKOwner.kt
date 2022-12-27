package com.android.library.utils

import android.os.Parcelable
import com.tencent.mmkv.MMKV
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface MKOwner {
    val kv: MMKV
        get() = default
            ?: throw IllegalStateException("If you use MMKV in Application, you should set MMKVOwner.default first.")

    companion object {
        @JvmStatic
        var default: MMKV? = null
    }
}

class MKProperty<V>(
    private val decode: (String) -> V,
    private val encode: Pair<String, V>.() -> Boolean
) : ReadWriteProperty<MKOwner, V> {
    private var cache: V? = null

    override fun getValue(thisRef: MKOwner, property: KProperty<*>): V =
        cache ?: decode(property.name).also { cache = it }

    override fun setValue(thisRef: MKOwner, property: KProperty<*>, value: V) {
        if (encode(property.name to value)) {
            cache = value
        }
    }
}

inline fun <reified T : MKOwner> T.mkInt(default: Int = 0) =
    MKProperty({ kv.decodeInt(it, default) }, { kv.encode(first, second) })

inline fun <reified T : MKOwner> T.mkLong(default: Long = 0) =
    MKProperty({ kv.decodeLong(it, default) }, { kv.encode(first, second) })

inline fun <reified T : MKOwner> T.mkBool(default: Boolean = false) =
    MKProperty({ kv.decodeBool(it, default) }, { kv.encode(first, second) })

inline fun <reified T : MKOwner> T.mkFloat(default: Float = 0f) =
    MKProperty({ kv.decodeFloat(it, default) }, { kv.encode(first, second) })

inline fun <reified T : MKOwner> T.mkDouble(default: Double = 0.0) =
    MKProperty({ kv.decodeDouble(it, default) }, { kv.encode(first, second) })

inline fun <reified T : MKOwner> T.mkString() =
    MKProperty({ kv.decodeString(it) }, { kv.encode(first, second) })

inline fun <reified T : MKOwner> T.mkString(default: String) =
    MKProperty({ kv.decodeString(it) ?: default }, { kv.encode(first, second) })

inline fun <reified T : MKOwner> T.mkStringSet() =
    MKProperty({ kv.decodeStringSet(it) }, { kv.encode(first, second) })

inline fun <reified T : MKOwner> T.mkStringSet(default: Set<String>) =
    MKProperty({ kv.decodeStringSet(it) ?: default }, { kv.encode(first, second) })

inline fun <reified T : MKOwner> T.mkBytes() =
    MKProperty({ kv.decodeBytes(it) }, { kv.encode(first, second) })

inline fun <reified T : MKOwner> T.mkBytes(default: ByteArray) =
    MKProperty({ kv.decodeBytes(it) ?: default }, { kv.encode(first, second) })

inline fun <reified T : MKOwner, reified V : Parcelable> T.mkParcel() =
    MKProperty({ kv.decodeParcelable(it, V::class.java) }, { kv.encode(first, second) })

inline fun <reified T : MKOwner, reified V : Parcelable> T.mkParcel(default: V) =
    MKProperty({ kv.decodeParcelable(it, V::class.java) ?: default }, { kv.encode(first, second) })
