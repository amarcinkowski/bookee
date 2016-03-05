package pl.amarcinkowski.bookee;

import static spark.Spark.*;

public class BookeeService {

	public static final Integer PORT = 8080;
	public static final String DOMAIN = "localhost";
	public static final String API = "books";

	public BookeeService() {
		port(PORT);
		staticFileLocation("public");
		get(API + "/", (res, req) -> "all");
		get(API + "//:id", (res, req) -> "one");
		post(API + "/", (res, req) -> "done");
		delete(API + "/:id", (res, req) -> "removed");
		put(API + "/:id", (res, req) -> "updated");
	}

}
