package io.tnboot.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.JavaExec

/**
 * A plugin that adds the `--add-opens java.base/java.lang=ALL-UNNAMED`
 * JVM argument to all JavaExec tasks.
 */
class AddOpensPlugin : Plugin<Project> {
	override fun apply(target: Project) {
		target.tasks.withType(JavaExec::class.java) {
			it.jvmArgs.addAll(listOf("--add-opens", "java.base/java.util=ALL-UNNAMED"))
		}
	}
}
