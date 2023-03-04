package io.github.yuazer.zaxlib.Utils;

public class ZaxClassLoader extends ClassLoader{
    public Class<?> ZaxdefineClass(String name, byte[] bytes) {
        return defineClass(name, bytes, 0, bytes.length);
    }
}
