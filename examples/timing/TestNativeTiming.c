
#include <stdio.h>
#include <stdlib.h>


int main(int argc, char **argv)
{
	float start_time, total_time;
	float report_cpu_time();
	int i, loop;

	start_time = report_cpu_time();

	i=0; loop=0;
	for (loop=0; loop<100000000; loop++)
		i = i + loop;

	total_time = report_cpu_time() - start_time;

	printf("%f\n", total_time);

}
