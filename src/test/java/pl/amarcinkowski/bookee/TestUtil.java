package pl.amarcinkowski.bookee;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import spark.utils.IOUtils;

public class TestUtil {

	public static Logger logger = (Logger) LoggerFactory.getLogger(TestUtil.class.getCanonicalName());

	public static String getResponse(String targetURL, String method, String urlParameters) {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(targetURL);
			logger.debug(String.format("%6s %s", "ADR", url));
			connection = (HttpURLConnection) url.openConnection();
			connection.setUseCaches(false);
			connection.setDoOutput(true);
			connection.setRequestMethod(method);
			connection.setRequestProperty("Content-Type", "application/json");

			if (urlParameters != null) {
				connection.getOutputStream().write(urlParameters.getBytes());
			}
			logger.debug(String.format(" >> %6s =[%s]=", method, urlParameters));
			connection.connect();

			String response = "";
			response = IOUtils.toString(connection.getInputStream());
			logger.debug(String.format(" << %6s =[%s]= ", method, response));
			return response;
		} catch (IOException e) {
			logger.error(e.getMessage());
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public static void loggerLevel() {
		Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		root.setLevel(Level.INFO);
		logger.setLevel(Level.DEBUG);
	}

}
