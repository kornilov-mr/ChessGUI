package org.example.utils.logical;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * Utility to get information from .env file
 */
public class DotEnv {
    private static Dotenv dotenv = null;

    public static Dotenv getDotenv() {
        if (dotenv == null) {
            dotenv = Dotenv.configure()
                    .directory("src/main/resources")
                    .filename("ServerConnectionConfig.env")
                    .load();
        }
        return dotenv;
    }
    public static boolean contains(String key) {
        return getDotenv().get(key) != null;
    }
    public static String getValue(String name){
        if(getDotenv().get(name)==null){
            return null;
        }
        return getDotenv().get(name);
    }
}
