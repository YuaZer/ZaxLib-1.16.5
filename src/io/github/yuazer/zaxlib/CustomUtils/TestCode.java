package io.github.yuazer.zaxlib.CustomUtils;

import io.github.yuazer.zaxlib.Utils.NetClassLoader;
import io.github.yuazer.zaxlib.Utils.ZaxClassLoader;
import org.bukkit.configuration.file.YamlConfiguration;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestCode {
    private static Map<String, String> parseQueryParams(String query) {
        Map<String, String> queryParams = new HashMap<>();
        if (query != null) {
            String[] params = query.split("&");
            for (String param : params) {
                String[] parts = param.split("=");
                if (parts.length == 2) {
                    queryParams.put(parts[0], parts[1]);
                }
            }
        }
        return queryParams;
    }

    public static void main(String[] args) {
        System.out.println(parseQueryParams("personName=YuaZer&papiName=cmi_user_name"));
    }
}

