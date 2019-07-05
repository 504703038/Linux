# 习题10.1
import requests
from bs4 import BeautifulSoup
allUniv = []
pUniv = {}
Pro = []


def getHTMLText(url):
    try:
        r = requests.get(url, timeout=30)
        r.raise_for_status()
        r.encoding = 'utf-8'
        return r.text
    except:
        return ""


def fillUnivList(soup):
    data = soup.find_all('tr')
    for tr in data:
        ltd = tr.find_all('td')
        if len(ltd) == 0:
            continue
        singleUniv = []
        for td in ltd:
            singleUniv.append(td.string)
        allUniv.append(singleUniv)


def printUnivList():
    print("{1:^2}{2:{0}^10}{3:{0}^6}{4:{0}^4}{5:{0}^10}".format(
        chr(12288), "排名", "学校名称", "省市", "总分", "培养规模"))
    for i in Pro:
        lis = pUniv[i]
        for u in lis:
            print("{1:^4}{2:{0}^10}{3:{0}^5}{4:{0}^8.1f}{5:{0}^10}".format(
                chr(12288), u[0], u[1], u[2], eval(u[3]), u[6]))


def getUnivList(num):
    for i in range(num):
        province = allUniv[i][2]
        if province not in Pro:
            Pro.append(province)
        lis = pUniv.get(province, [])
        lis.append(allUniv[i])
        pUniv[province] = lis


def main():
    url = 'http://www.zuihaodaxue.cn/zuihaodaxuepaiming2016.html'
    html = getHTMLText(url)
    soup = BeautifulSoup(html, "html.parser")
    fillUnivList(soup)
    num = 20
    getUnivList(num)
    printUnivList()


main()
