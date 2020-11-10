# CovidWhatsappBot

A Whatsapp Bot which makes use of https://covid-19-apis.postman.com to get Covid 19 Data. And Twilio to integrate with WhatsApp.

The Demo is deployed to Heroku: https://guarded-hamlet-85692.herokuapp.com/

This WebService has expose endpoint "/covid" which is hooked to twilio.

Steps to use:
1. Save the Twilio provided number.
2. Send Messages in below format:
  - CASES \<CountryCode\> - to get active cases in particular country.
  - DEATHS \<CountryCode\> - to get death cases in particular country.
  - CASES TOTAL - to get total active cases in the world.
  - DEATHS TOTAL - to get total deaths cases in the world.
  
Working:

Whenever someone send a message to Twilio number through WhatsApp,
Twilio sends a post request to our exposed endpoint through webhook.
As soon as our web server gets the request.
Another request is made to covid-19 Apis to get the latest data.
The data is modified/sent directly to Twilio as a response.

Note: Data Accuracy is as provided by the covid-19 postman apis.

