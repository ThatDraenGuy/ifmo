input()
values = sorted(enumerate(map(int, input().split())), key=lambda t: t[1])
print(' '.join(map(lambda t: str(t[0]), values)))