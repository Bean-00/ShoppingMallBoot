#/bin/sh
creme='ssh -i /Users/bean/Development/AWS/creme-key.pem ec2-user@ec2-3-34-238-157.ap-northeast-2.compute.amazonaws.com'

#build
./gradlew bootJar

#transfer
rsync -avg -e "ssh -i /Users/bean/Development/AWS/creme-key.pem -o StrictHostKeyChecking=no" /Users/bean/Development/ShoppingMallBoot/build/libs/ShoppingMallBoot-0.0.1-SNAPSHOT.jar ec2-user@ec2-3-34-238-157.ap-northeast-2.compute.amazonaws.com:/home/ec2-user/creme

$creme "sh /home/ec2-user/creme/stop.sh"
$creme "sh /home/ec2-user/creme/start.sh"
