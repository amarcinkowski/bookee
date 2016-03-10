package pl.amarcinkowski.bookee;

import static spark.Spark.*;

import spark.Request;
import spark.Response;

import static pl.amarcinkowski.bookee.JsonUtil.*;

public class BookeeService {

	public static final Integer PORT = 8080;
	public static final String PROTOCOL = "http://";
	public static final String DOMAIN = "localhost";
	public static final String API = "/books/";
	public static final String API_WITH_ID = "/books/:id";

	public BookeeService() {
		port(PORT);
		staticFileLocation("public");
		get(API, (res, req) -> findAll(res, req), json());
		get(API_WITH_ID, (res, req) -> find(res, req), json());
		post(API, (res, req) -> save(res, req), json());
		delete(API_WITH_ID, (res, req) -> remove(res, req), json());
		put(API_WITH_ID, (res, req) -> update(res, req), json());
		exception(Exception.class, (e, req, res) -> {
			res.body(e.getClass().getCanonicalName());
			res.status(400);
		});
		after((req, res) -> {
			res.type("application/json");
		});
	}

	private Object findAll(Request res, Response req) {
		return new Object[] { "all" };
	}

	private Object find(Request res, Response req) {
		return new Object[] { "one" };
	}

	private Object save(Request res, Response req) {
		return new Object[] { "done" };
	}

	private Object remove(Request res, Response req) {
		return new Object[] { "removed" };
	}

	private Object update(Request res, Response req) {
		return new Object[] { "updated" };
	}

	public static String getUrl(String... params) {
		String url = String.format("%s%s:%s%s%s", PROTOCOL, DOMAIN, PORT, API, urilze(params));
		return url;
	}

	public static String urilze(String... params) {
		StringBuilder builder = new StringBuilder();
		for (String param : params) {
			if (builder.length() > 0) {
				builder.append("/");
			}
			builder.append(param);
		}
		return builder.toString();
	}

}
