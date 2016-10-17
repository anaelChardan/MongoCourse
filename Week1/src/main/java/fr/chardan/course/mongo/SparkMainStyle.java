package fr.chardan.course.mongo;

import static spark.Spark.*;

public class SparkMainStyle {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World From Spark");
    }
}
