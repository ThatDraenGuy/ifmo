def error():
    print("Error!")
    quit()
    
a=list(input())
if len(a)!=7:
    error()
for i in range (0, 7):
    if a[i]!='0' and a[i]!='1':
        error()
    a[i]=int(a[i])
print(a)
s1=(a[0]+a[2]+a[4]+a[6])%2
s2=(a[1]+a[2]+a[5]+a[6])%2
s3=(a[3]+a[4]+a[5]+a[6])%2

def swap(a):
    if a==1:
        return 0
    else:
        return 1

def tell():
    print("Сообщение: ", a[2], a[4], a[5], a[6])

def err(bit):
    if bit==6:
        return "Ошибка в бите i4 (№7)"
    elif bit==3:
        return "Ошибка в бите r3 (№4)"
    elif bit==2:
        return "Ошибка в бите i1 (№3)"
    elif bit==1:
        return "Ошибка в бите r2 (№2)"
    elif bit==4:
        return "Ошибка в бите i2 (№5)"
    elif bit==5:
        return "Ошибка в бите i3 (№6)"
    elif bit==0:
        return "Ошибка в бите r1 (№1)"

    
bit=999
if s1==s2 and s2==s3:
    if s1==0:
        print("Ошибок нет")
    else:
        bit=6
elif s1==s2 and s2!=s3:
    if s1==0:
        bit=3
    else:
        bit=2
elif s1==s3 and s1!=s2:
    if s1==0:
        bit=1
    else:
        bit=4
else:
    if s1==0:
        bit=5
    else:
        bit=0
        
if bit!=999:
    a[bit]=swap(a[bit])
    print(err(bit))
tell()

