from swiplserver import PrologMQI, create_posix_path
import re
from queries import *

KNOWLEDGE_PATH = "/home/draen/Documents/ifmo/ifmo/sem5/ai/lab1/terraria.pl"
EXIT_COMMAND = "exit"

query_handlers = {
    RecommendedBossesQueryHandler(),
    AvailableBossesQueryHandler(),
    UnlockedByTierBossesQueryHandler()
}

prolog = PrologMQI().create_thread()

path = create_posix_path(KNOWLEDGE_PATH)
prolog.query(f'consult("{path}")')

print("Use '" + EXIT_COMMAND + "' to exit")
while True:
    query = input("Enter your prompt: ")
    if query.lower() == EXIT_COMMAND:
        break

    query_handled = False
    for query_handler in query_handlers:
        res = query_handler.try_match(prolog, query)
        if res is not None:
            query_handled = True
            print(res, '\n')
            break
    if not query_handled:
        print("Unknown prompt!")


prolog.halt_server()