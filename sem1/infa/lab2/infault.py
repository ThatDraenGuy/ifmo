def error():
    print("Error!")
    quit()
def swap(a):
    if a==1:
        return 0
    else:
        return 1
def tell():
    print("Сообщение: ")
    for i in range (0, len(a)+1):
        i2=i
        if i!=0 and i!=1:
            infcheck=0
            while i!=1:
                if i%2==1:
                    infcheck=1
                i=i//2
            if infcheck==1:
               print(a[i2-1]) 
def name(b):
    b2=b
    count=0
    infcheck=0
    while b!=1:
        if b%2==1:
            infcheck=1
        b=b//2
        count=count+1
    if infcheck==0:
        return "r", count+1
    else:
        return "i", b2-count-1

    
a=list(input())
b=len(a)+1
count=0
while b!=1:
    if b%2==1:
        error()
    b=b//2
    count=count+1
info=len(a)-count


for i in range (0, len(a)):
    if a[i]!='0' and a[i]!='1':
        error()
    a[i]=int(a[i])
print(a)
s=list(range(count))
for i in range (0, count):
    s[i]=0
    c=2**i-1
    for j in range (0, 2**(count-1)//2**i):
        for k in range (2**i):
            s[i]=s[i]+a[c]
            c=c+1
        c=c+2**i
    s[i]=s[i]%2
errbit=0
for i in range (0, count):
    errbit=errbit+s[i]*2**i
        
if errbit!=0:
    a[errbit-1]=swap(a[errbit-1])
    print("Ошибка в бите №", errbit, name(errbit))
else:
    print("Ошибок нет")
tell()

