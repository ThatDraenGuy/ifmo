#include <sys/types.h>
#include <sys/stat.h>
#include <linux/fs.h>
#include <stdlib.h>
#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>

#define ROOT "/"

int main(void)
{
    pid_t pid = fork();

    if (pid == -1)
        return -1;

    if (pid != 0)
        exit(EXIT_SUCCESS);

    if (setsid() == -1)
        return -1;

    if (chdir(ROOT) == -1)
        return -1;

    printf("%d\n", pid);

    close(0);
    close(1);
    close(2);

    sleep(100);
    return 0;
}
