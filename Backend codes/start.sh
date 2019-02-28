echo "Staring MySQL Server..."
killall mysqld
/opt/lampp/share/mysql/mysql.server start
echo "Staring XAMPP Server..."
/opt/lampp/lampp start
export CLASSPATH=$CLASSPATH:"/home/shreyas/scripts/mysql-connector-java-5.1.21.jar"
export CLASSPATH=$CLASSPATH:"/home/shreyas/scripts/weka-3.7.0.jar"
