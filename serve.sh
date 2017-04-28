echo "starting cherry controller server";
nohup java -jar $1 --server.port=80 > journal/`date +%Y-%m-%d`.log 2>&1 &
echo $! > save_pid.txt
echo "server up and running, pid : " `cat save_pid.txt`
