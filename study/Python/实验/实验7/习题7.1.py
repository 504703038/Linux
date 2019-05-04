# 习题7.1
import keyword
f = open('test.py', 'r')
text = f.read()
f.close()

table = ['range', 'print', 'list', 'set', 'keyword', 'kwlist', 'end', 'open']
content = ''
tmp = ''
for ch in text:
    if ch.isalpha():
        tmp += ch
    else:
        if (not keyword.iskeyword(tmp)) and (tmp not in table):
            tmp = tmp.upper()
        content += tmp
        content += ch
        tmp = ''

f = open('test.py', 'w')
f.write(content)
f.close()
