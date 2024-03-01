#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <dirent.h>

int main()
{
    struct dirent **names;
    int count;

    count = scandir("/proc", &names, NULL, alphasort);
    if (count == -1)
    {
        perror("scansur");
        exit(EXIT_FAILURE);
    }

    FILE *f;
    char *name, path[1024];
    int result = 0;

    while (count --> 0)
    {
        if (atoi(names[count]->d_name) != 0)
        {
            sprintf(path, "/proc/%s/comm", names[count]->d_name);
            f = fopen(path, "rb");

            fscanf(f, "%s", name);
            if (strcmp("genenv", name) == 0)
            {
                result++;
            }
            fclose(f);
        }
        free(names[count]);
    }
    free(names);
    printf("%d\n", result);

    exit(EXIT_SUCCESS);
}
