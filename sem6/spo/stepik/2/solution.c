#include <dlfcn.h>
#include <stddef.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

int (*some_function)(const int);

bool init_lib(const char *dlib, const char *lib) {
  void *handle = dlopen(dlib, RTLD_LAZY);

  if (handle == NULL) {
    return false;
  }

  some_function = dlsym(handle, lib);
  return some_function != NULL;
}

int main(int argc, char *argv[]) {
  if (init_lib(argv[1], argv[2]))
    printf("%d\n", some_function(atoi(argv[3])));
  else
    printf("Library could not load!\n");
  return 0;
}
