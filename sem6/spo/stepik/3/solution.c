#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main() {
  char path[1024];
  char line[1024];
  sprintf(path, "/proc/%d/status", getpid());

  FILE *file = fopen(path, "rb");
  
  int i = 0;
  while (i != 7) { 
    fgets(line, 1024, file);
    i++;
  }

  int pid;
  sscanf(line, "PPid:	%d\n", &pid);
  printf("%d\n", pid);

  return 0;
}
