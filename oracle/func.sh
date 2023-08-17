getjdbc() {
wget https://raw.githubusercontent.com/anujca/cloud/main/oracle/ojdbc8-21.9.0.0.jar
wget https://raw.githubusercontent.com/anujca/cloud/main/oracle/ojdbc8-23.2.0.0.jar
wget https://raw.githubusercontent.com/anujca/cloud/main/oracle/ojdbc10-19.20.0.0.jar 
wget https://raw.githubusercontent.com/anujca/cloud/main/oracle/ojdbc11-21.9.0.0.jar 
wget https://raw.githubusercontent.com/anujca/cloud/main/oracle/ojdbc11-23.2.0.0.jar 
wget https://raw.githubusercontent.com/anujca/cloud/main/oracle/ojdbc8-19.20.0.0.jar
wget https://raw.githubusercontent.com/anujca/cloud/main/oracle/ojdbc5-11.2.0.4.jar 
wget https://raw.githubusercontent.com/anujca/cloud/main/oracle/ojdbc6-11.2.0.4.jar 
wget https://raw.githubusercontent.com/anujca/cloud/main/oracle/oracle.java
wget https://raw.githubusercontent.com/anujca/cloud/main/oracle/func.txt
}

findjava() {
echo Finding java
sudo su -c "find / -name java 2>/dev/null"

echo finding javac
sudo su -c "find / -name javac 2>/dev/null"
}

findnetstat() {
sudo su -c "pid=$(ps -ef|grep java|grep ssm|awk '{print $2}');netstat -townp|grep $pid"
}


runme() {
cd ~/oracle
export java=/apps/tcserver/install/java/jdk1.6.0_171/bin/java
export javac=/apps/tcserver/install/java/jdk1.6.0_171/bin/javac
export jdbc=/apps/tcserver/local/vfabric-tc-server-standard-2.7.0.RELEASE/instances/2_LR_TU_CL8003_M1/lib/ojdbc6.jar
export class=~/oracle/.
jdbc=$(eval echo \${jdbc$2})
$javac oracle.java
$java -version
$java -jar $jdbc
$java  -cp $jdbc:$class OracleSql system gLmVI7_9R2 l4driddb1272.hewitt.com 1526 qupn0100.hewitt.com tcp ${1:-0}
}

