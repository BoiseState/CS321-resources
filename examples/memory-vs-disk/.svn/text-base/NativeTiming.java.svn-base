

       /*Create a directory lib under your  home directory and copy the
         libtiming.so function to that directory.

                mkdir ~/lib
                cp ~amit/lib/libtiming.so ~/lib

        The library libtiming.so contains the timing function.
        In order to access the library, add the following line
        to the end of your .bashrc file (assuming that you are using bash)

                export LD_LIBRARY_PATH=$HOME/lib

        The above definition will be effective the next time you login.
        In order for this definition to be effective immediately, execute
        the .bashrc file as follows:

                source ~/.bashrc

        Now the java runtime system can find the shared library at run time.
        */

class NativeTiming {
    public static native float report_cpu_time();

    static {
        System.loadLibrary("timing");
    }
}

/* vim: set ts=4: */
