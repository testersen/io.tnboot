tasks.withType<JavaExec> {
	doFirst {
		jvmArguments.addAll(listOf("--add-opens", "java.base/java.util=ALL-UNNAMED"))
	}
}
