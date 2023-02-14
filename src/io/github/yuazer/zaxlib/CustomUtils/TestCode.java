package io.github.yuazer.zaxlib.CustomUtils;


import org.bukkit.configuration.file.YamlConfiguration;

import javax.swing.*;
import java.io.*;
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

public class TestCode {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("&aaa");
        list.add("&bbb");
        list.add("&ccc");
        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i).replace("&","t"));
        }
    }
}

