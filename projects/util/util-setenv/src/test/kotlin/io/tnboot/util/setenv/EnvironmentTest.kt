package io.tnboot.util.setenv

import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals

class EnvironmentTest {
	@Test
	fun `Environment-set sets environment variable`() {
		val randomValue = "my-test-value-${UUID.randomUUID()}"

		Environment["MY_TEST_ENVIRONMENT_VARIABLE"] = randomValue

		assertEquals(randomValue, System.getenv("MY_TEST_ENVIRONMENT_VARIABLE"))
	}
}
