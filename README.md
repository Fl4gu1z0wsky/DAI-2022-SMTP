# DAI-2022-SMTP

## About this project
The prupose of this project is to get familiar with the SMTP (Simple Message Transfer Protocol), JAVA TCP Socket API and docker.
For that, we are going to implement a MockMock server (SMTP server) with docker. Then we are going to create our own client application in JAVA. It will use TCP to create a socket API in order to communicate with the MockMock server and generate partial SMTP requests. Those requests will send a "prank" to some people the user has chosen. For that, we will implement a .JSON file with a few emails and a text that the user can modify. Then, he will be able to select what he wants to send in the console line.

## What is MockMock ?
MockMock is a cross-platform SMTP server built on Java. It allows you to test if outgoing emails are sent (without actually sending them) and to see what they look like. It provides a web interface that displays which emails were sent and shows you what the contents of those emails are. If you use MockMock you can be sure that your outgoing emails will not reach anyone by accident. Thus, it is a good plateform to test your SMTP code.

You can find the repository here: https://github.com/DominiqueComte/MockMock

## Setting up your MockMock SMTP server with Docker
First, there is some problem that you will encounter if you install Docker on a Windows OS. It will interact badly with your VMs. To resolve this problem, you should use a Linux machine (I use Ubuntu 22.04) and install docker on it.         
           
The command to install it will be:
```sh
sudo apt install docker.io
```
And it will install it automatically. Next you should create a new file with:
```sh
mkdir mockServer
```
and go into it with
```sh
cd mockServer
```
It will serve for the next step. Finally enter:
```sh
mkdir docker
```
It will be your repository to launch docker.          
You will have to install the files of MockMock. To do it, you have to clone the repository from GitHub. Here's the cmd:
```sh
git clone https://github.com/DominiqueComte/MockMock.git
```
Then you will have to install Maven:
```sh
sudo apt install maven
```
It is a build automation tool used primarily for Java projects. If it is installed correctly, you should be able to enter
```sh
mvn --version
```
and see a description of it.          
Make sure you uploaded our "Dockerfile" from our github repository and added it in the "docker" folder. Add as well the "build_image.sh", and "run_container.sh" in this folder. **Be careful !** You may need to change the paths inside those files. It depends on how you structured your directories.           
The you can launch the creation of you image with the next cmd:
```sh
sudo ./build_image.sh
```
If it is not working, make sure you can execute it. If not, launch the cmd:
```sh
chmod 665 build_image.sh
```
It will give you the permission to execute it as a non-owner.           
Finally, launch your running docker cmd with:
```sh
sudo ./run_container.sh
```
*Same as before, make sure it is executable.*            
After running the script, you can make sure your docker container is running with:
```sh
sudo docker ps
```
This command will show you the containers running. Another test is to connect to http://127.0.0.1:80. You should see your MockMock page.          
![alt text](https://github.com/Fl4gu1z0wsky/DAI-2022-SMTP/blob/main/images/mockmock_home.png)          
From now on, you should be able to interact with you MockMock. Make sure you know the IP of the server to connect to it.             
For example, I run IntelliJ on Windows and my MockMock server on an Ubuntu machine. I just need to enter:
```sh
ip a
```
And it will display my IP.             
Then, I can connect to it from my Windows machine with "http://myIP:80". Make sure you Virtual Machine is running on **bridge mode** and not NAT. Bridge mode will allow you to have another IP from the scope delivered by the dhcp and interact with it.  

If you needs to change your ip and set a static one, you can follow this tutorial:
https://linuxconfig.org/how-to-configure-static-ip-address-on-ubuntu-18-10-cosmic-cuttlefish-linux

## Configuring your tool and running a prank campaign


## Description of your implementation
![alt text](https://github.com/Fl4gu1z0wsky/DAI-2022-SMTP/blob/main/images/classes.png)
### Main Class
#### main()
It will contain the code that will read the .JSON using the path where the user is. It checks as well if there is some errors parsing it.    
Then, it displays the groups for the user to chose with the prompt. And the messages (pranks) to send.    
Finally, the mail is send with the class MailSender.
#### displayGroups()
Display the list of available groups for the user to chose with the prompt.
#### displayMessages()
Display the list of available messages (pranks) for the user to chose with the prompt.
#### prompt()
Prompt the user for a numeric choice in order to chose what groups he want to send the prank and which one the group will receive.
### MailSender Class
This is the class that connect to the server (with a socket) and send the mail with the SMTP protocol we implemented in it.
- host: is the IP of th server
- port: is the port of the server
#### mailSender()
This is the constructor of the class, where we add the port and host.
#### send()
This is where we build the SMTP protocl in order to send the mail with the group chosen and the prank.
### Constants Class
It contain the constants that the user can change if needed (not all can be changed).
- MinimumMailPerGroup: Used to chose a minimum of member per group.
- ServerAddress: Is the address of the server and need to be changed in order to connect to it
- ServerPort: Is the port server and should not be changed.

### Example
The user is asked waht to chose:    
![alt text]https://github.com/Fl4gu1z0wsky/DAI-2022-SMTP/blob/main/images/prompt1.png    
![alt text]https://github.com/Fl4gu1z0wsky/DAI-2022-SMTP/blob/main/images/prompt2.png    
Then the server receive the mail:       
![alt text]https://github.com/Fl4gu1z0wsky/DAI-2022-SMTP/blob/main/images/new_mail.png    

