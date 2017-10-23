# LightBase-Server
Open-source emulation of google firebase's realtime database in JAVA. 

Basically , a non-relational document-based database server that handles normal request-response on one port , and serves realtime update notifications on another port .

requests are to be made using simple text commands similar to SQL

responses will be in JSON formatted plaintext , may add support for XML later if needed

not decided the notification stuff yet

will support multiple concurrent clients in order to be useful in web servers

I have only just started developing it , any help and suggestions will be welcome :) 

Progress :
1. Made the CLI command handling framework
2. Created the basic graph-like data structure

To do :
1. Implement query handling
2. Implement client queuing
3. Save the data to the data store file periodically
4. Implement everything xD
