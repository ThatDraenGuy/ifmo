int load_linked(atomic_int *x);

bool store_conditional(atomic_int *x, int new_value);

int atomic_fetch_add(atomic_int *x, int arg) {
    int oldval;
    do {
        oldval = load_linked(x);
    } while (!store_conditional(x, oldval + arg));
    return oldval;
}

bool atomic_compare_exchange(atomic_int *x, int *expected_value, int new_value) {
    do {
        int oldval = load_linked(x);
        if (oldval != *expected_value) {
            *expected_value = oldval;
            return false;
        }
    } while (!store_conditional(x, new_value));
    return true;
}