#!/bin/bash

UPGRADE_HOME=/usr/local/upgrade/hkapp_v1.1.5
TOMCAT_HOME=/usr/local/hktomcat
APP_NAME=hk
WEB_ROOT=webapps

if [ ! -e $UPGRADE_HOME/$APP_NAME.war ];then
    echo "$UPGRADE_HOME/$APP_NAME.war 文件不存在！"
    exit 1
fi

echo "解压缩war包"
unzip $UPGRADE_HOME/$APP_NAME.war -d $UPGRADE_HOME/$APP_NAME/

cd $TOMCAT_HOME

check_tomcat_pid() {
  psid_lines=`ps gaux | grep "org.apache.catalina.startup.Bootstrap start" | grep -v grep | awk '{print $2}'`
  psid=`echo $psid_lines`
}

check_tomcat_pid

if [ -n "$psid" ];then
    echo '停止tomcat服务...'
    ./bin/shutdown.sh
fi

while [ -n "$psid" ]
do
    sleep 1s
    check_tomcat_pid
done
echo "tomcat服务已停止"

echo '备份原服务包...'
mv $TOMCAT_HOME/$WEB_ROOT/$APP_NAME/ $TOMCAT_HOME/$WEB_ROOT/$APP_NAME.bak.$(date +%Y%m%d)

if [ -e $TOMCAT_HOME/backup/$APP_NAME.bak.$(date +%Y%m%d) ];then
    mv $TOMCAT_HOME/backup/$APP_NAME.bak.$(date +%Y%m%d) $TOMCAT_HOME/backup/$APP_NAME.bak.$(date +%Y%m%d%H%M%S)
fi

mv $TOMCAT_HOME/$WEB_ROOT/$APP_NAME.bak.$(date +%Y%m%d) $TOMCAT_HOME/backup/
echo "备份原服务包完成，备份文件目录$TOMCAT_HOME/backup/$APP_NAME.bak.$(date +%Y%m%d)"

echo "拷贝新服务包至tomcat，目标目录$TOMCAT_HOME/$WEB_ROOT/"
mv $UPGRADE_HOME/$APP_NAME/ $TOMCAT_HOME/$WEB_ROOT/

echo "启动tomcat服务..."
./bin/startup.sh
echo "启动tomcat服务完成"
