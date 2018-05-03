package com.jez.data.mybatis.flush;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedIOException;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * Created by SOP on 2017/3/21. MyBatis Mapper热部署线程
 */
public class MapperReloader implements Runnable {

  private static Logger logger = LoggerFactory.getLogger(MapperReloader.class);

  private static final String CONFIG_FILE_PATH = "classpath:mybatis-reloader.properties";
  private static final String MSG_CONFIG_INITIALED = "Mapper reloader configuration has vo initialized. {}";
  private static final String MSG_MAPPER_REAL_PATHS = "Mapper real paths: {}.";
  private static final String MSG_CONFIGURATION = "Configuration: {}.";
  private static final String MSG_MAPPER_LOCATIONS_SIZE = "Mapper locations size: {}.";
  private static final String MSG_MAPPER_REAL_PATH = "Mapper real path: {}.";
  private static final String MSG_MAPPER_REAL_PATHS_SIZE = "Mapper real paths size: {}.";
  private static final String MSG_THREAD_INTERRUPTED = "MapperReloader was interrupted.";
  private static final String MSG_ENABLE_MAPPER_RELOADER = "MapperReloader was enabled.";
  private static final String MSG_ERROR_WHILE_RELOADING = "Error while flush mapper file.";
  private static final String MSG_RELOAD_FILES_SIZE = "Reload files size: {}.";
  private static final String MSG_UNKNOWN_FILE = "Unknown file: {}.";
  private static final String MSG_RELOAD_MAPPER_FILE = "Reload mapper file: {}.";
  private static final String MSG_FAILED_TO_PARSE_MAPPER_FILE = "Failed to parse mapping resource: %s";

  private static final String DOUBLE_BACK_SLANT = "\\\\";
  private static final String SLANT = "/";
  private static final String FILE_SYSTEM_RESOURCE_DESCRIPTION_PREFIX = "file [";
  private static final String[] CONFIGURATION_RELOAD_FIELD_NAMES = new String[]{"mappedStatements",
      "caches", "resultMaps", "parameterMaps", "keyGenerators", "sqlFragments"};
  private static final String CONFIGURATION_FIELD_NAME_LOADED_RESOURCES = "loadedResources";
  private static final String FORMATTER_CONFIGURATION_STRICT_MAP_NAME = "%s collection";

  private static final long MILLIS_PER_SECOND = 1000L;
  private static final String CONFIG_KEY_ENABLED = "enabled";
  private static final String CONFIG_DEFAULT_ENABLED = "false";
  private static final String CONFIG_KEY_DELAY_SECONDS = "delaySeconds";
  private static final String CONFIG_DEFAULT_DELAY_SECONDS = "60";
  private static final String CONFIG_KEY_SLEEP_SECONDS = "sleepSeconds";
  private static final String CONFIG_DEFAULT_SLEEP_SECONDS = "5";
  private static final String CONFIG_KEY_MAPPING_PATH = "mappingPath";
  private static final String CONFIG_DEFAULT_MAPPING_PATH = "mappings";

  private static final boolean ENABLED;         // 是否启用Mapper刷新线程功能
  private static final int DELAY_SECONDS;        // 延迟刷新秒数
  private static final int SLEEP_SECONDS;        // 休眠时间
  private static final String MAPPING_PATH;      // xml文件夹匹配字符串，需要根据需要修改

  private static boolean refresh;         // 刷新启用后，是否启动了刷新线程
  private Long lastReloadedTime = 0L;           // 上一次刷新时间

  private Set<String> mapperRealPaths;         // Mapper实际资源路径

  private Resource[] mapperLocations;     // Mapper资源路径
  private Configuration configuration;        // MyBatis配置对象

  static {
    ResourceLoader resourceLoader = new DefaultResourceLoader();
    Properties properties = new Properties();
    Resource resource = resourceLoader.getResource(CONFIG_FILE_PATH);
    try {
      PropertiesLoaderUtils.fillProperties(properties, resource);
    } catch (IOException e) {
    }
    logger.info(MSG_CONFIG_INITIALED, properties);
    ENABLED = Boolean
        .parseBoolean(properties.getProperty(CONFIG_KEY_ENABLED, CONFIG_DEFAULT_ENABLED));
    DELAY_SECONDS = Integer
        .parseInt(properties.getProperty(CONFIG_KEY_DELAY_SECONDS, CONFIG_DEFAULT_DELAY_SECONDS));
    SLEEP_SECONDS = Integer
        .parseInt(properties.getProperty(CONFIG_KEY_SLEEP_SECONDS, CONFIG_DEFAULT_SLEEP_SECONDS));
    MAPPING_PATH = properties.getProperty(CONFIG_KEY_MAPPING_PATH, CONFIG_DEFAULT_MAPPING_PATH);
  }

  public static boolean isRefresh() {
    return refresh;
  }

  public MapperReloader(Resource[] mapperLocations, Configuration configuration) {
    this.mapperLocations = mapperLocations;
    this.configuration = configuration;
  }

  @Override
  public void run() {
    lastReloadedTime = System.currentTimeMillis();
    if (logger.isDebugEnabled()) {
      logger.debug(MSG_MAPPER_REAL_PATHS, mapperRealPaths);
      logger.debug(MSG_CONFIGURATION, configuration);
    }
    if (ENABLED) {
      logger.info(MSG_ENABLE_MAPPER_RELOADER);
      // 启动刷新线程
      final MapperReloader runnable = this;
      Thread thread =
          new Thread(new Runnable() {
            @Override
            public void run() {
              if (mapperRealPaths == null) {
                mapperRealPaths = new HashSet<>();
                if (logger.isDebugEnabled()) {
                  logger.debug(MSG_MAPPER_LOCATIONS_SIZE, mapperLocations.length);
                }
                for (Resource mapperLocation : mapperLocations) {
                  String mapperRealPath = StringUtils
                      .replace(mapperLocation.getDescription(), DOUBLE_BACK_SLANT, SLANT);
                  if (!StringUtils
                      .startsWith(mapperRealPath, FILE_SYSTEM_RESOURCE_DESCRIPTION_PREFIX)) {
                    continue;
                  }
                  mapperRealPath = StringUtils
                      .substring(mapperRealPath, FILE_SYSTEM_RESOURCE_DESCRIPTION_PREFIX.length(),
                          mapperRealPath.lastIndexOf(MAPPING_PATH) + MAPPING_PATH.length());
                  if (!mapperRealPaths.contains(mapperRealPath)) {
                    mapperRealPaths.add(mapperRealPath);
                    if (logger.isDebugEnabled()) {
                      logger.debug(MSG_MAPPER_REAL_PATH, mapperRealPath);
                    }
                  }
                }
                if (logger.isDebugEnabled()) {
                  logger.debug(MSG_MAPPER_REAL_PATHS_SIZE, mapperRealPaths.size());
                }
              }
              try {
                Thread.sleep(DELAY_SECONDS * MILLIS_PER_SECOND);
              } catch (InterruptedException e) {
                logger.error(MSG_THREAD_INTERRUPTED, e);
              }
              refresh = true;
              while (true) {
                try {
                  for (String mapperRealPath : mapperRealPaths) {
                    runnable.reload(mapperRealPath, lastReloadedTime);
                  }
                } catch (Exception e) {
                  logger.error(MSG_ERROR_WHILE_RELOADING, e);
                }
                try {
                  Thread.sleep(SLEEP_SECONDS * MILLIS_PER_SECOND);
                } catch (InterruptedException e) {
                  logger.error(MSG_THREAD_INTERRUPTED, e);
                }

              }
            }
          }, MapperReloader.class.getName());
      thread.setDaemon(true);
      thread.start();
    }
  }

  /**
   * 执行刷新
   *
   * @param filePath 刷新目录
   * @param lastReloadedTime 上次刷新时间
   * @throws NestedIOException 解析异常
   * @author ThinkGem
   */
  @SuppressWarnings({"rawtypes", "unchecked"})
  private void reload(String filePath, Long lastReloadedTime) throws Exception {

    // 本次刷新时间
    Long reloadTime = System.currentTimeMillis();

    // 获取需要刷新的Mapper文件列表
    List<File> reloadFiles = getReloadFiles(new File(filePath), lastReloadedTime);
    if (reloadFiles.size() > 0) {
      if (logger.isDebugEnabled()) {
        logger.debug(MSG_RELOAD_FILES_SIZE, reloadFiles.size());
      }
    }
    for (int i = 0; i < reloadFiles.size(); i++) {
      File reloadFile = reloadFiles.get(i);
      String resource = reloadFile.getAbsolutePath();
      try (InputStream inputStream = new FileInputStream(reloadFile)) {
        // 清理原有资源，更新为自己的StrictMap方便，增量重新加载
        for (String fieldName : CONFIGURATION_RELOAD_FIELD_NAMES) {
          Field field = configuration.getClass().getDeclaredField(fieldName);
          field.setAccessible(true);
          Map map = ((Map) field.get(configuration));
          if (!(map instanceof MapperReloadableStrictMap)) {
            Map newMap = new MapperReloadableStrictMap(String
                .format(FORMATTER_CONFIGURATION_STRICT_MAP_NAME,
                    StringUtils.capitalize(fieldName)));
            for (Object key : map.keySet()) {
              try {
                newMap.put(key, map.get(key));
              } catch (IllegalArgumentException ex) {
                newMap.put(key, ex.getMessage());
              }
            }
            field.set(configuration, newMap);
          }
        }

        // 清理已加载的资源标识，方便让它重新加载。
        Field loadedResourcesField = configuration.getClass()
            .getDeclaredField(CONFIGURATION_FIELD_NAME_LOADED_RESOURCES);
        loadedResourcesField.setAccessible(true);
        Set loadedResourcesSet = ((Set) loadedResourcesField.get(configuration));
        loadedResourcesSet.remove(resource);

        //重新编译加载资源文件。
        XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(inputStream, configuration,
            resource, configuration.getSqlFragments());
        xmlMapperBuilder.parse();
      } catch (Exception e) {
        throw new NestedIOException(String.format(MSG_FAILED_TO_PARSE_MAPPER_FILE, resource), e);
      } finally {
        ErrorContext.instance().reset();
      }
      logger.info(MSG_RELOAD_MAPPER_FILE,
          MAPPING_PATH + StringUtils.substringAfterLast(resource, MAPPING_PATH));
    }
    // 如果刷新了文件，则修改刷新时间，否则不修改
    if (reloadFiles.size() > 0) {
      this.lastReloadedTime = reloadTime;
    }
  }

  /**
   * 获取需要刷新的文件列表
   *
   * @param dir 目录
   * @param lastReloadedTime 上次刷新时间
   * @return 刷新文件列表
   */
  private List<File> getReloadFiles(File dir, Long lastReloadedTime) {
    List<File> files = new ArrayList<>();
    File[] subFiles = dir.listFiles();
    if (subFiles != null) {
      for (int i = 0; i < subFiles.length; i++) {
        File file = subFiles[i];
        if (file.isDirectory()) {
          files.addAll(this.getReloadFiles(file, lastReloadedTime));
        } else if (file.isFile()) {
          if (file.lastModified() > lastReloadedTime) {
            files.add(file);
          }
        } else {
          logger.error(MSG_UNKNOWN_FILE, file);
        }
      }
    }
    return files;
  }

  /**
   * 重写 org.apache.ibatis.session.Configuration.StrictMap 类 修改 put 方法，允许反复 put更新
   */
  protected static class MapperReloadableStrictMap<V> extends HashMap<String, V> {

    private static final long serialVersionUID = 6171997609819237547L;

    private static final String MSG_RELOAD_KEY = "Reload key: {}.";
    private static final String DOT = ".";

    private String name;

    public MapperReloadableStrictMap(String name, int initialCapacity, float loadFactor) {
      super(initialCapacity, loadFactor);
      this.name = name;
    }

    public MapperReloadableStrictMap(String name, int initialCapacity) {
      super(initialCapacity);
      this.name = name;
    }

    public MapperReloadableStrictMap(String name) {
      super();
      this.name = name;
    }

    public MapperReloadableStrictMap(String name, Map<String, ? extends V> m) {
      super(m);
      this.name = name;
    }


    @SuppressWarnings("unchecked")
    public V put(String key, V value) {
      // BEGIN 如果现在状态为刷新，则刷新(先删除后添加)
      if (MapperReloader.isRefresh()) {
        remove(key);
        if (MapperReloader.logger.isDebugEnabled()) {
          MapperReloader.logger.debug(MSG_RELOAD_KEY, key.substring(key.lastIndexOf(DOT) + 1));
        }
      }
      // END
      if (containsKey(key)) {
        throw new IllegalArgumentException(name + " already contains value for " + key);
      }
      if (key.contains(".")) {
        final String shortKey = getShortName(key);
        if (super.get(shortKey) == null) {
          super.put(shortKey, value);
        } else {
          super.put(shortKey, (V) new Ambiguity(shortKey));
        }
      }
      return super.put(key, value);
    }

    public V get(Object key) {
      V value = super.get(key);
      if (value == null) {
        throw new IllegalArgumentException(name + " does not contain value for " + key);
      }
      if (value instanceof Ambiguity) {
        throw new IllegalArgumentException(
            ((Ambiguity) value).getSubject() + " is ambiguous in " + name
                + " (try using the full name including the namespace, or rename one of the entries)");
      }
      return value;
    }

    private String getShortName(String key) {
      final String[] keyparts = key.split("\\.");
      return keyparts[keyparts.length - 1];
    }

    protected static class Ambiguity {

      private String subject;

      public Ambiguity(String subject) {
        this.subject = subject;
      }

      public String getSubject() {
        return subject;
      }
    }
  }
}
