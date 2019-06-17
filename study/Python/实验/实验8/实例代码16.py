# e16.1BatchInstall.py
import os
libs = {"bs4"}
try:
    for lib in libs:
        os.system("pip3 install " + lib)
    print("Successful")
except:
    print("Failed Somehow")
