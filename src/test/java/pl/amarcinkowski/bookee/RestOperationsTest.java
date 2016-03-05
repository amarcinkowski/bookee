package pl.amarcinkowski.bookee;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.amarcinkowski.bookee.TestUtil.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.amarcinkowski.bookee.Main;
import spark.Spark;

public class RestOperationsTest {

	/**
	 * Before class.
	 */
	@BeforeClass
	public static void beforeClass() {
		TestUtil.loggerLevel();
		Main.main(null);
		Spark.awaitInitialization();
	}

	/**
	 * After class.
	 */
	@AfterClass
	public static void afterClass() {
		Spark.stop();
	}

	@Test
	public void testGet() {
		String response = TestUtil.getResponse(getUrl("1"), "GET", null);
		assertThat(response).isEqualTo("one");
	}

	@Test
	public void testGetAll() {
		String response = TestUtil.getResponse(getUrl(), "GET", null);
		assertThat(response).isEqualTo("all");
	}

	@Test
	public void testPost() {
		String response = TestUtil.getResponse(getUrl(), "POST", null);
		assertThat(response).isEqualTo("done");
	}

	@Test
	public void testDelete() {
		String response = TestUtil.getResponse(getUrl("1"), "DELETE", null);
		assertThat(response).isEqualTo("removed");
	}

	@Test
	public void testUpdate() {
		String response = TestUtil.getResponse(getUrl("1"), "PUT", null);
		assertThat(response).isEqualTo("updated");
	}

}
