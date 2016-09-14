# -*- coding: UTF-8 -*-
'''
Created on Jun 25, 2016

@author: chenli
'''
import requests
from bs4 import BeautifulSoup
import sys
import smtplib
from email.mime.text import MIMEText
from email.header import Header

class MailService(object):
    __mail_host = 'smtp.163.com'
    __mail_port = 25
    __mail_user = 'chuzhouchenli@163.com' 
    __mail_pass = 'chenli123'
    __mail_prefix = '@qq.com'
    def __init__(self, email_Number, email_context):
        self.email_number = email_Number
        self.email_context = email_context
       
    def sendMail(self):
        return True
        flag = True
        try:
            server = smtplib.SMTP(MailService.__mail_host, MailService.__mail_port)
            server.login(MailService.__mail_user, MailService.__mail_pass)
            msg = MIMEText(self.email_context, 'plain', 'utf-8')
            msg['From'] = MailService.__mail_user + '<' + MailService.__mail_user + '>' 
            msg['Subject'] = Header(u'成绩通知', 'utf8').encode()
            msg['To'] = self.email_number + '<' + self.email_number + MailService.__mail_prefix + '>'
            server.sendmail(MailService.__mail_user, [self.email_number + MailService.__mail_prefix], msg.as_string())
        except Exception:
            flag = False
        return flag

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

    def getCourse(self):
        flag = True
        try:
            response = self.s.get(UCASEvaluate.__courserSelectionPage, headers = UCASEvaluate.__headers)
            soup = BeautifulSoup(response.text,'html.parser')
            indentity = str(soup.noscript).split('Identity=')[1].split('"'[0])[0]
            coursePage = UCASEvaluate.__studentCourseIdentify + indentity
            response = self.s.get(coursePage)
            soup = BeautifulSoup(response.text,'html.parser')
            recordIndex = 0
            scoreCourse = [UCASEvaluate.__studentCourseEvaluateUrl + geturl['href'] for geturl in soup.find(text='查询成绩').parent.parent.parent.find_all('a')[-2:]]
            for scoreurl in scoreCourse:
                response = self.s.get(scoreurl)
                soup = BeautifulSoup(response.text,'html.parser')
                courseListResource = soup.body.find_all('table')[-1].tbody.find_all('tr')
                for course in courseListResource:
                    tdList = course.find_all('td')
                    courseName = str(recordIndex) + str(tdList[0].string.encode('utf-8'),'utf-8','ignore')
                    if tdList[1].string is None:                    
                        courseScore = '0'
                    else:
                        courseScore = str(tdList[1].string.encode('utf-8'),'utf-8','ignore')
                    self.courseDict[courseName] = courseScore
                    recordIndex += 1
            self.courseDict['numbers'] = recordIndex
        except Exception:
            flag = False
        return flag

if __name__ == '__main__':
    returnDict = {'state':0}
    email = sys.argv[3]
    flag = int(sys.argv[4])
    ucasEvaluate = UCASEvaluate(sys.argv[1],sys.argv[2])
    if ucasEvaluate.login():
        if ucasEvaluate.getCourse():
            if ucasEvaluate.courseDict['numbers'] != flag:
                mailTest = MailService(email, ucasEvaluate.courseDict.__str__())
                if mailTest.sendMail():
                    returnDict['state'] = 1
                    returnDict['numbers'] = ucasEvaluate.courseDict['numbers']
    print(returnDict)