package top.whalefall.summerframework.util;

public class ClassUtils {
    private ClassUtils() {}

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            // No thread context class loader -> use class loader of this class.
            classLoader = ClassUtils.class.getClassLoader();
        }
        return classLoader;
    }

    /**
     * Check whether the specified class is a CGLIB-generated class.
     * @param clazz the class to check
     */
    public static boolean isCglibProxyClass(Class<?> clazz) {
        return clazz != null && isCglibProxyClassName(clazz.getName());
    }

    /**
     * Check whether the specified class name is a CGLIB-generated class.
     * @param className the class name to check
     */
    public static boolean isCglibProxyClassName(String className) {
        return className != null && className.contains("$$");
    }
}
