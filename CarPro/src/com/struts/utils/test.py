#coding:utf-8
'''
Created on Jun 12, 2016

@author: chenli
'''
  
import requests
import sys

class UCASEvaluate:
    __loginUrl = 'http://sep.ucas.ac.cn/slogin'
    __courserSelectionPage = 'http://sep.ucas.ac.cn/portal/site/226/821'
    __studentCourseIdentify = 'http://jwxk.ucas.ac.cn/login?Identity='
    __studentCourseEvaluateUrl = 'http://jwxk.ucas.ac.cn'
    __headers = {
                'Host': 'sep.ucas.ac.cn',
                'Connection': 'keep-alive',
                'Pragma': 'no-cache',
                'Cache-Control': 'no-cache',
                'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8',
                'Upgrade-Insecure-Requests': '1',
                'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36',
                'Accept-Encoding': 'gzip, deflate, sdch',
                'Accept-Language': 'zh-CN,zh;q=0.8,en;q=0.6',

    }
    
    def __init__(self, parausername, parauserpass):
        self.username= parausername
        self.password= parauserpass
        self.s = requests.Session()
        self.courseDict = {'numbers':0}

    def login(self):
        flag = False
        try:
            postdata = {
                'userName' : self.username,
                'pwd' : self.password,
                'sb'       : 'sb'
            }
            self.s.post(UCASEvaluate.__loginUrl, data=postdata, headers = UCASEvaluate.__headers)
            if 'sepuser' in self.s.cookies.get_dict():
                flag = True
        except Exception:
            pass
        return flag

if __name__ == '__main__':
    returnDict = {'state':0}
    ucasEvaluate = UCASEvaluate(sys.argv[1],sys.argv[2])
    if ucasEvaluate.login():
        returnDict['state'] = 1
    print(returnDict)