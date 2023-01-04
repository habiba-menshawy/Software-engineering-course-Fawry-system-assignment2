# FawrySystem-webServices
Converting the old Fawry System to web services by building REST APIs for the existing features


# How to build the project  
-> form eclipse : import existence maven project and run as java application project <br>
-> form intellij : open the project from file and import as maven project <br>
make sure to go to setting->build, execution, deployment -> build tools -> change maven home path into Bundled (Maven 3)

## versions used: 
org.springframework.boot : 3.0.1 <br>
the project depends on Java 17

# System notes: 

## steps when first running for a customer
1-sign up as a customer (customer/signup)<br>
2-login as a customer (customer/login)<br>
3-add to wallet if needed (transaction/addToWallet/{{value}}) 

 ## steps when first running for an admin
1-sign up as a admin (admin/signup)<br>
2-login as a admin (admin/login)<br> 


1- if you need to log in as a customer/admin you would need to log out first if you're logged in already<br>
2- discounts are added as float values from 0 to <1 otherwise it would show a bad request <br>
3- before doing any payment/transaction using a wallet you need to use (transaction/addToWallet/{{value}}) <br>
