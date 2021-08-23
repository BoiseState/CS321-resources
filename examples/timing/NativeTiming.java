



/**
 * Obtain cpu time used from native OS.
 * Requires a corresponding libtiming.so library for this to work under Linux.
 * Does not work under Windows or MAC OS. 
 * @author amit
 *
 */
class NativeTiming {
    public static native float report_cpu_time();
    public static native float report_sys_time();

    static {
        System.loadLibrary("timing");
    }
}

/* vim: set ts=4: */
