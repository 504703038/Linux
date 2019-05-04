import keyword
F = open('TEST.PY', 'R')
TEXT = F.READ()
F.CLOSE()

TABLE = ['range', 'print', 'list', 'set', 'keyword', 'kwlist', 'end']
CONTENT = ''
TMP = ''
for CH in TEXT:
    if CH.ISALPHA():
        TMP += CH
    else:
        if (not keyword.ISKEYWORD(TMP)) and (TMP not in TABLE):
            TMP = TMP.UPPER()
        CONTENT += TMP
        CONTENT += CH
        TMP = ''

F = open('TEST.PY', 'W')
F.WRITE(CONTENT)
F.CLOSE()
