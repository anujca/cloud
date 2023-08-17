getjdbc() {
cd
mkdir -p oracle
cd oracle
for i in ojdbc8-21.9.0.0.jar ojdbc8-23.2.0.0.jar ojdbc10-19.20.0.0.jar ojdbc11-21.9.0.0.jar ojdbc11-23.2.0.0.jar ojdbc8-19.20.0.0.jar ojdbc5-11.2.0.4.jar  ojdbc6-11.2.0.4.jar  oracle.java func.sh
do
if [ -f $i ]; then
rm $i
fi
wget https://raw.githubusercontent.com/anujca/cloud/main/oracle/$i
done
cat func.sh
}

findjava() {
echo Finding java
sudo su -c "find / -name java 2>/dev/null"

echo finding javac
sudo su -c "find / -name javac 2>/dev/null"
}

findnetstat() {
ps -ef|grep java|grep ssm|grep -v grep|awk '{print $2}'|while read line
do
netstat -townp|grep $line
done
}


runme() {
cd ~/oracle
export java=/apps/tcserver/install/java/jdk1.7.0_04/bin/java
export javac=/apps/tcserver/install/java/jdk1.7.0_04/bin/javac
jdbc1=ojdbc10-19.20.0.0.jar
jdbc2=ojdbc11-21.9.0.0.jar
jdbc3=ojdbc11-23.2.0.0.jar
jdbc4=ojdbc5-11.2.0.4.jar
jdbc5=ojdbc6-11.2.0.4.jar
jdbc6=ojdbc8-19.20.0.0.jar
jdbc7=ojdbc8-21.9.0.0.jar
jdbc8=ojdbc8-23.2.0.0.jar
export class=~/oracle/.
jdbc=$(eval echo \${jdbc$2})
$javac oracle.java
$java -version
$java -jar $jdbc
$java  -cp $jdbc:$class OracleSql system gLmVI7_9R2 l4driddb1272.hewitt.com 1526 qupn0100.hewitt.com tcp ${1:-0}
}

