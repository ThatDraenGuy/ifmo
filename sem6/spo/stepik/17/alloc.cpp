struct header {
    struct header *prev;
    struct header *next;
    std::size_t capacity;
    bool free;
    std::uint8_t contents[];
};

struct header *memory;

// Эта функция будет вызвана перед тем как вызывать myalloc и myfree
    // используйте ее чтобы инициализировать ваш аллокатор перед началом
    // работы.
    //
    // buf - указатель на участок логической памяти, который ваш аллокатор
    //       должен распределять, все возвращаемые указатели должны быть
    //       либо равны NULL, либо быть из этого участка памяти
    // size - размер участка памяти, на который указывает buf
	void mysetup(void *buf, std::size_t size)
	{
        memory = (struct header *)buf;
        memory->prev = NULL;
        memory->next = NULL;
        memory->free = true;
        memory->capacity = size - sizeof(struct header);
    }

    // Функция аллокации
	void *myalloc(std::size_t size)
	{
        struct header *header = memory;
        do {
            if (header->free && (header->capacity >= size + sizeof(struct header)))
            {
                if (header->capacity >= size + 3 * sizeof(struct header))
                {
                    //struct header *next = (struct header *)(((void *)header) + header->capacity - size);
                    struct header *next = (struct header *)(header->contents + header->capacity - size - sizeof(struct header));
                    next->next = header->next;
                    header->next = next;
                    next->prev = header;
                    if (next->next)
                    {
                        next->next->prev = next;
                    }
                    next->capacity = size;
                    header->capacity -= size + sizeof(struct header);
                    next->free = false;
                    return (void *)next->contents;
                } else 
                {
                    header->free = false;
                    return (void *)header->contents;
                }
            }
        } while (header = header->next);
        return NULL;
    }

    // Функция освобождения
	void myfree(void *p)
	{
        struct header *head = (struct header *)((std::uint8_t *)p - sizeof(struct header));
        head->free = true;
        if (head->prev && head->prev->free)
        {
            struct header *prev = head->prev;
            prev->capacity += head->capacity + sizeof(struct header);
            prev->next = head->next;
            if (prev->next)
            {
                prev->next->prev = prev;
            }
            head = prev;
        }
        if (head->next && head->next->free)
        {
            struct header *next = head->next;
            head->capacity += next->capacity + sizeof(struct header);
            head->next = next->next;
            if (next->next)
            {
                next->next->prev = head;
            }
        }
    }