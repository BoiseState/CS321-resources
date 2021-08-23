
import java.text.*;


/**
 * A simple example to show usage of native timing
 * @author amit
 *
 */
class TestNativeTiming {

    public static void main(String[] args) {

        float systemTime  = NativeTiming.report_sys_time();
        float cpuTime = NativeTiming.report_cpu_time();

		// do some computing
		double result = 1.0;

		for (int loop=1; loop< 80000000; loop++) {
			result  = result + Math.sqrt(loop)/loop;
			if (result > 500) {
				result = result - 500;
			}
			/*System.out.println(result);*/
		}

		NumberFormat formatter = NumberFormat.getNumberInstance();
		formatter.setMaximumFractionDigits(2);
        cpuTime = NativeTiming.report_cpu_time() - cpuTime;
        systemTime  = NativeTiming.report_sys_time() - systemTime;

		System.out.println("cpu time used = "+ formatter.format(cpuTime));
		System.out.println("system time used = "+ formatter.format(systemTime));
    }
}
