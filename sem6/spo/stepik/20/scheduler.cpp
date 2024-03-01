#include <stdlib.h>
struct thread {
    int id;
    int time;
    struct thread *next;
};

static struct sched_rr {
    int slice; /* длительность кванта времени в тиках */
    struct thread *head; /* голова очереди на выполнение */
    struct thread *tail; /* хвост очереди на выполнение */
    struct thread *blocked; /* односвязный список заблокированных потоков */
} sched_rr;

/* переменная нужна лишь чтобы писать привычные стрелочки, а не точки */
static struct sched_rr *sched;

/* добавляет поток в хвост очереди, которая может быть пустой */
#define to_tail(p) do {                 \
    if(!sched->head) sched->head = p;   \
    else sched->tail->next = p;         \
    sched->tail = p;                    \
    p->time = sched->slice;             \
    p->next = NULL;                     \
} while(0)

/* добавляет поток в голову списка заблокированных */
#define to_blkd(p) do {         \
    p->next = sched->blocked;   \
    sched->blocked = p;         \
} while(0)

/* удаляет первый поток из очереди, предполагается, что очередь не пуста */
#define skip_head() do { sched->head = sched->head->next; } while(0)

void scheduler_setup(int timeslice) {
    sched = &sched_rr;
    sched->head = sched->blocked = NULL;
    sched->slice = timeslice;
}

void new_thread(int thread_id) {
    struct thread *ptr;
    ptr = (struct thread *) malloc(sizeof(struct thread));
    ptr->id = thread_id;
    to_tail(ptr);
}

void exit_thread() {
    struct thread *ptr;
    if (ptr = sched->head) {
        skip_head();
        free(ptr);
    }
}

void block_thread() {
    struct thread *ptr;
    if (ptr = sched->head) {
        skip_head();
        to_blkd(ptr);
    }
}

void wake_thread(int thread_id) {
    struct thread *cur;
    struct thread **ptr = &sched->blocked;
    cur = sched->blocked;
    while(cur->id != thread_id) {
        ptr = &cur->next;
        cur = cur->next;
    }
    *ptr = cur->next;
    to_tail(cur);
}

void timer_tick() {
    struct thread *ptr;
    if ((ptr = sched->head) && !--ptr->time) {
        skip_head();
        to_tail(ptr);
    }
}

int current_thread() {
    if (sched->head) return sched->head->id;
    else return -1;
}