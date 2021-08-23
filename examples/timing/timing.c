

#include <stdio.h>
#include <sys/times.h>
#include <unistd.h>


/*
---------------------------------------------------------------------------
clock_t times(struct tms *buffer);

times() fills the structure pointed to by buffer with
time-accounting information.  The structure defined in 
<sys/times.h> is as follows:

struct tms {
    clock_t tms_utime;       user time 
    clock_t tms_stime;       system time 
    clock_t tms_cutime;      user time, children 
    clock_t tms_cstime;      system time, children 

The time is given in units of 1/CLK_TCK seconds where the
value of CLK_TCK can be determined using the sysconf() function
with the agrgument _SC_CLK_TCK.
---------------------------------------------------------------------------
*/
								  
clock_t times();

float report_cpu_time()
{ 
	struct tms buffer;
	float cputime;

	times(&buffer);
	cputime = (buffer.tms_utime)/ (float) sysconf(_SC_CLK_TCK);
	return (cputime);
}

