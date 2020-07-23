# dadal-cloud-common
VM-SERVICE-START-CMD:
======Redis(port:6379)======
cd /home/frank/redis-4.0.10
redis-server redis.conf  --START
redis-cli -a 123456  --TO CONSOLE
redis-cli -p 6379 -a 123456 shutdown  or shutdown in console

auth 123456 -- to login while you need

keys *
del key
flushdb -- delete current data of db

exit

======MongoDB(port:27017)======
cd /home/frank/mongodb/bin
./mongod -f mongo.conf --bind_ip_all  --START
./mongo  --TO CONSOLE
use admin
db.auth("root","123456")
use testdb
db.user.find()
exit

======ActiveMQ(port:5671)======
cd /home/frank/apache-activemq-5.15.10/bin
./activemq start  --START

======RabbitMQ(port:5672)======
cd /home/frank/rabbitmq_server-3.7.7
erl  --check the env
halt().  --exit the env
rabbitmq-server start &  --START
rabbitmqctl status  --check status
rabbitmqctl stop  --close
rabbitmq-plugins enable rabbitmq_management  --START WEB
http://192.168.75.130:15672/
