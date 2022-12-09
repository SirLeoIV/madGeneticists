package src.support;
public class Debug {

    public static int LOG_LEVEL = 0;
    public static boolean DEBUG = true;
        
    public static void log0(Object o) {
        if (LOG_LEVEL >= 0) System.out.println(o.toString());
    }
    
    public static void log1(Object o) {
        if (LOG_LEVEL >= 1) System.out.println(o.toString());
    }
    
    public static void log2(Object o) {
        if (LOG_LEVEL >= 2) System.out.println(o.toString());
    }
    
    public static void log3(Object o) {
        if (LOG_LEVEL >= 3) System.out.println(o.toString());
    }
        
    public static void log4(Object o) {
        if (LOG_LEVEL >= 4) System.out.println(o.toString());
    }
    
    public static void debug(Object o) {
        if (DEBUG) System.out.println("# DEBUG: " + o.toString());
    }
}
