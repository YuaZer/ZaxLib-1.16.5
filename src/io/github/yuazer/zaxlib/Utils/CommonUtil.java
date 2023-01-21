package io.github.yuazer.zaxlib.Utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import io.github.yuazer.zaxlib.Interface.*;
import org.bukkit.plugin.java.JavaPlugin;

public class CommonUtil {
    public CommonUtil() {
    }

    public static Integer[] formatSlots(String text) {
        if (text == null) {
            return new Integer[0];
        } else {
            int n2;
            int min;
            if (text.contains(",")) {
                List<Integer> list = new ArrayList();
                String[] var10 = text.split(",");
                n2 = var10.length;

                for(min = 0; min < n2; ++min) {
                    String s = var10[min];
                    list.addAll(Arrays.asList(formatSlots(s)));
                }

                return (Integer[])list.toArray(new Integer[0]);
            } else if (!text.contains("-")) {
                return new Integer[]{Integer.parseInt(text)};
            } else {
                String[] split = text.split("-");
                int n1 = Integer.parseInt(split[0]);
                n2 = Integer.parseInt(split[1]);
                min = Math.min(n1, n2);
                int max = Math.max(n1, n2);
                Integer[] result = new Integer[max - min + 1];
                int index = 0;

                for(int temp = min; temp <= max; ++index) {
                    result[index] = temp;
                    ++temp;
                }

                return result;
            }
        }
    }

    public static void outputFileTool(InputStream in, File file) {
        try {
            if (!file.getParentFile().exists()) {
                Files.createDirectory(file.getParentFile().toPath());
            }

            if (!file.exists()) {
                Files.createFile(file.toPath());
            }

            OutputStream out = new FileOutputStream(file);
            byte[] b = new byte[1024];

            int length;
            while((length = in.read(b)) != -1) {
                out.write(b, 0, length);
            }

            out.close();
            in.close();
        } catch (IOException var5) {
        }

    }

    public static byte[] transferFileToBytes(File f) {
        try {
            FileInputStream fis = new FileInputStream(f);
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();

            int b;
            while((b = fis.read()) != -1) {
                byteArray.write(b);
            }

            byteArray.flush();
            byteArray.close();
            fis.close();
            return byteArray.toByteArray();
        } catch (IOException var4) {
            var4.printStackTrace();
            return new byte[0];
        }
    }

    public static void saveResource(JavaPlugin plugin, String source, String target, boolean replace, CustomExecute<File> execute) {
        File file = new File(plugin.getDataFolder(), target);
        boolean exists = file.exists();
        if (exists && !replace) {
            if (execute != null) {
                execute.run(file);
            }

        } else {
            outputFileTool(plugin.getResource(source), file);
            if (execute != null) {
                execute.run(file);
            }

        }
    }

    public static List<String> matchStart(List<String> list, String start) {
        return (List)list.stream().filter((s) -> {
            return s.toLowerCase().startsWith(start.toLowerCase());
        }).collect(Collectors.toList());
    }
}