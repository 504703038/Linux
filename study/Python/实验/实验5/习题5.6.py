# 习题5.6
from datetime import datetime

birthday = datetime(1999, 10, 18)
print(birthday.strftime("%Y-%m-%d"))
print(birthday.strftime("%Y-%m-%d %A"))
print(birthday.strftime("%Y-%m-%d %a"))
print(birthday.strftime("%Y-%B-%d"))
print(birthday.strftime("%Y-%B-%d %A"))
print(birthday.strftime("%Y-%B-%d %a"))
print(birthday.strftime("%Y-%b-%d"))
print(birthday.strftime("%Y-%b-%d %A"))
print(birthday.strftime("%Y-%b-%d %a"))
print(birthday.strftime("%B %d,%Y"))
