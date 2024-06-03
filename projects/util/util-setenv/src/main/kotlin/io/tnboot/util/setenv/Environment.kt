package io.tnboot.util.setenv

import java.lang.invoke.MethodHandles

object Environment : MutableMap<String, String?> {
	private val inner: MutableMap<String, String?> = System.getenv().let { unwritable ->
		val lookup = MethodHandles.privateLookupIn(unwritable::class.java, MethodHandles.lookup())
		val field = lookup.findVarHandle(unwritable::class.java, "m", Map::class.java)
		@Suppress("UNCHECKED_CAST")
		field.get(unwritable) as MutableMap<String, String?>
	}

	override val entries: MutableSet<MutableMap.MutableEntry<String, String?>> = inner.entries
	override val keys: MutableSet<String> = inner.keys
	override val size: Int = inner.size
	override val values: MutableCollection<String?> = inner.values
	override fun clear() = inner.clear()
	override fun isEmpty(): Boolean = inner.isEmpty()
	override fun remove(key: String): String? = inner.remove(key)
	override fun putAll(from: Map<out String, String?>) = inner.putAll(from)
	override fun put(key: String, value: String?): String? = inner.put(key, value)
	override fun get(key: String): String? = inner[key]
	override fun containsValue(value: String?): Boolean = inner.containsValue(value)
	override fun containsKey(key: String): Boolean = inner.containsKey(key)

	/** Creates a copy of the current environment variable map. */
	private fun snapshot() = inner.toMap(mutableMapOf())

	/** Restores the environment variable map to the given snapshot. */
	private fun restore(snapshot: Map<String, String?>) {
		inner.clear()
		inner.putAll(snapshot)
	}
}
