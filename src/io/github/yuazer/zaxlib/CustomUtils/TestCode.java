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
        for(int x=11;x<=92;x++){
            System.out.println(String.format("addModel(EnumSpecies.Hu%s, new PixelmonSmdFactory(EnumPokemonModel.Hu%s));",String.valueOf(x),String.valueOf(x)));
        }
    }
}

