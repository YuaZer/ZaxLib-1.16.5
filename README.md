### 自用的1.16.5插件开发工具库

# 异步监听事件工具类
```plain
io.github.yuazer.zaxlib.AsyncUtils.EventListenerAsync

void listen(Plugin plugin, Listener listener) 异步监听事件
```
# AES字符串加解密工具类
```plain
io.github.yuazer.zaxlib.CustomUtils.EncryptUtil

/**
 * 生成 AES 密钥
 * @return 密钥字符串
 * @throws NoSuchAlgorithmException
 */
String generateAESKey()

/**
 * 使用 AES 加密字符串
 * @param plainText 明文字符串
 * @param key 密钥字符串
 * @return 加密后的字符串
 * @throws Exception
 */
 String encryptAES(String plainText, String key)
 
/**
 * 使用 AES 解密字符串
 * @param encryptedText 加密后的字符串
 * @param key 密钥字符串
 * @return 明文字符串
 * @throws Exception
 */
 String decryptAES(String encryptedText, String key)
```
# 云端读取资源工具类
```plain
io.github.yuazer.zaxlib.Utils.NetClassLoader

从URL获取Class
形参:
jarUrl – 目标jar的URL链接 
classFullName – class路径
返回值:
Class

Class getUrlClass(String jarUrl, String classFullName)

从URL获取Class
形参:
jarUrl – 目标jar的URL链接(经过AES混淆后的) 
classFullName – class路径
key - AES Key
返回值:
Class

Class getUrlClass_Encrypt(String EncryptjarUrl, String classFullName,String key)

从URL获取YAML文件
形参:
Url – Yaml文件的URL链接
返回值:
YamlConfiguration

YamlConfiguration getYamlFromUrl(String Url)

将Class转化为byte[]
形参:
clazz – 需要被转为byte[]的Class对象
返回值:
byte[]

byte[] classToByte(Class clazz)
```
# NMS工具
```plain
io.github.yuazer.zaxlib.Utils.NMSUtils

将org.bukkit.World world转为net.minecraft.world.World
World bkToNmsWorld(org.bukkit.World world)

将org.bukkit.inventory.ItemStack转为net.minecraft.item.ItemStack
net.minecraft.item.ItemStack bkToNmsItemStack(org.bukkit.inventory.ItemStack itemStack)

将org.bukkit.inventory.ItemStack转为net.minecraft.item.ItemStack
net.minecraft.item.ItemStack nmsToBkItemStack(org.bukkit.inventory.ItemStack itemStack)
```

# 插件加载提示工具
```plain
检测前置是否完整
void checkDepend(JavaPlugin beLoad, List<String> DependName)
```
# Pixelmon模组工具[1.16.5]
```plain
io.github.yuazer.zaxlib.Utils.PokeUtils

将宝可梦存储为NBT File
void setPokemonInFile_NBT(Pokemon pokemon, File file)

从NBT File获取宝可梦对象
Pokemon getPokemonInFile_NBT(File file)

发起玩家与指定List<Pokemon>的单打对战(通过创建NPCTrainer,因此宝可梦数量不能超过6只)
void battlePokemon(Player player, List<Pokemon> pokemons)

将org.bukkit.entity.Player转为ServerPlayerEntity 
ServerPlayerEntity getServerPlayerEntity(Player player)

发起玩家与指定NBT File宝可梦的对战(精灵属于野生精灵)
void battlePokemon_Wild(Player player, File file)

通过Attack中文名获取Attack对象
Attack getMoveCN(String moveName)

通过Nature中文名获取Nature对象
Nature getNatureCN(String natureName)
```
# 时间工具
```plain
io.github.yuazer.zaxlib.Utils.TimeUtils

获取今天是星期几
(周一，周二，周三，周四，周五，周六，周日)
String getWeek()
```
# Inventory工具
```plain
io.github.yuazer.zaxlib.CustomUtils.InventoryUtils

/**
     * 将给定的Inventory对象存储为YAML文件
     *
     * @param inventory 需要被存储的Inventory对象
     * @param file      需要被存储的文件
     * @throws IOException 如果文件无法被创建或写入时抛出异常
     */
saveInventoryToFile(Inventory inventory, File file)

/**
     * 从给定的YAML文件中读取Inventory对象
     *
     * @param file 包含Inventory数据的YAML文件
     * @return 生成的Inventory对象
     * @throws IOException 如果文件无法被读取时抛出异常
     */
Inventory loadInventoryFromFile(File file)
```
# 文件工具
```plain
io.github.yuazer.zaxlib.Utils.TimeUtils

将文件复制粘贴到指定位置(会覆盖)
oldPath: 被复制的文件的路径
newPath: 想粘贴到的文件路径
void copyFile(String oldPath, String newPath)
```
# GuiModel工具
我先承认一下，我抄了AyCore的GuiModel的代码，并新增了一些代码

因此可以在ZaxLib里也可以使用AyCore的GuiModel工具类(具体使用方法看AyCore的文档吧)

[PokemonAPI - 开发文档 (mc9y.com)](http://www.mc9y.com/docs/PokemonAPI/#/)

AyCore的打开Inventory的方法:

```plain
void openInventory(Player player)
```
ZaxLib保留并新增了一个方法
```plain
异步打开Inventory
void openInventoryAsync(Plugin plugin, Player player)
```

# 插件验证工具
懒得写使用说明了，需要的直接加作者QQ:1109132，我直接告诉你怎么用

