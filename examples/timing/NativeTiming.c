
#include <stdio.h>
#include <sys/times.h>
#include <unistd.h>
#include <jni.h>

clock_t times();

/*
 * Class:     NativeTiming
 * Method:    report_cpu_time
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_NativeTiming_report_1cpu_1time (JNIEnv *env, jclass class)
{
	struct tms buffer;
	float cputime;

	times(&buffer);
	cputime = (buffer.tms_utime)/ (float) sysconf(_SC_CLK_TCK);
	return (cputime);
}

/*
 * Class:     NativeTiming
 * Method:    report_sys_time
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_NativeTiming_report_1sys_1time (JNIEnv *env, jclass class)
{
	struct tms buffer;
	float systime;

	times(&buffer);
	systime = (buffer.tms_stime)/ (float) sysconf(_SC_CLK_TCK);
	return (systime);
}

