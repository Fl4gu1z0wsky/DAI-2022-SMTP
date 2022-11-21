# DAI-2022-SMTP

## About this project
The prupose of this project is to get familiar with the SMTP (Simple Message Transfer Protocol), JAVA TCP Socket API and docker.
For that, we are going to implement a MockMock server (SMTP server) with docker. Then we are going to create our own client application in JAVA. It will use TCP to create a socket API in order to communicate with the MockMock server and generate partial SMTP requests.

## What is MockMock ?
MockMock is a cross-platform SMTP server built on Java. It allows you to test if outgoing emails are sent (without actually sending them) and to see what they look like. It provides a web interface that displays which emails were sent and shows you what the contents of those emails are. If you use MockMock you can be sure that your outgoing emails will not reach anyone by accident. Thus, it is a good plateform to test your SMTP code.

You can find the repository here: https://github.com/DominiqueComte/MockMock

## Setting up your mock SMTP server with Docker
First of all, there is some problem that you will encounter if you install Docker on a Windows OS. It will interact baddly with your VMs. In order to resolve this proble, you should use a linux machine (I use Ubuntu 22.04) and install docker on it.
The command to install it will be "$ sudo apt install docker.io". And it will install it automatically. It's as easy as a simple cmd !
Next you should create a new file with "$ mkdir mockServer" and go into it with "$ cd mockServer". It will serve for the next step. Finally enter "$ mkdir docker". It will be your repository to launch docker.
You will have to install the files of MockMock. In order to do it, you have to clone the repository from github. Here's the cmd:
"$ git clone https://github.com/DominiqueComte/MockMock.git". Then you will have to install Maven: "$ sudo apt install maven". It is a build automation tool used primarily for Java projects. If it is installed correctly, you should enter "$ mvn --version" and see a description of it.
Make sure you have your "Dockerfile" is in the docker folder. And make sure that "build_image.sh", and "run_container.sh" are installed on your folder from our github repository. Be careful ! You may need to change the paths inside those files. It depends on how you structured your directories.
The you can launch the creation of you image with the next cmd "$ sudo ./build_image.sh". If it is not working, make sure you can execute it. It not, launch the cmd "$ chmod 665 build_image.sh". It will give you the permission to execture it as a non-owner.
Finally, launch your running docker cmd with: "$ sudo ./run_container.sh" (same as before,  make sure it is executable).
When it has finally run, you can make sure it running with "$ sudo docker ps". This command will show you the containers running. Another test is to connect to http://127.0.0.1:80. You should see your MockMock page.

From now on, you should be able to interact with you MockMock. Make sure you know it's IP to use connect to it.
For example, I run IntelliJ on Windows and my MockMock server on an Ubuntu machine. I just need to enter "$ ip a" on my ubuntu to find out my IP.
Then, I can connect to it from my Windows machine with "http://IP:80". Make sure you VirtualMachine is running on bridge mode and not NAT. Bridge mode will allow you to have another IP from the scope delivered by the dhcp and interact with it.

## Configuring your tool and running a prank campaign


## Description of your implementation

