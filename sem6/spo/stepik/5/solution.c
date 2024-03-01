#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define LENGTH 2048

int get_ppid(int pid)
{
    char path[LENGTH], line[LENGTH];

    sprintf(path, "/proc/%d/status", pid);
    FILE *f = fopen(path, "rb");
    int count = 7;
    while (count --> 0)
    {
        fgets(line, LENGTH, f);
    }
    fclose(f);
    sscanf(line, "PPid: %d", &pid);
    return pid;
}

int main(int argc, char *argv[])
{
    int pid = atoi(argv[1]);
    while (pid)
    {
        printf("%d\n", pid);
        pid = get_ppid(pid);
    }
    return 0;
}
