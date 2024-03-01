#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>

#define PATTERN "/proc/%d/task/%d/children"
#define LENGTH 2048

char path[LENGTH];

int dfs(int pid)
{
    int result = 1;
    sprintf(path, PATTERN, pid, pid);
    FILE *f = fopen(path, "rb");
    int id;
    while (fscanf(f, "%d", &id) >= 0)
    {
        result += dfs(id);
    }
    fclose(f);
    return result;
}

int main(int argc, char *argv[])
{
    int pid = atoi(argv[1]);
    printf("%d\n", dfs(pid));
    return 0;
}
