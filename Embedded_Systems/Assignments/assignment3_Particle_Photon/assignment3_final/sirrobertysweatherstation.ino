/**
 *
 * This is the code for my weatherstation
 * it gets the temperature and humididy from a DHT11 sensor
 * And it also gets data from external API using a webhook.
 *
 * @author Robert Mattias Molin
 * version: 2017.06.09
 *
 * Inspired by: @Author Gustavo Gonnet,
 * Project available from: https://www.hackster.io/gusgonnet/temperature-humidity-monitor-with-blynk-7faa51
 */

//Sets maximum size for webhook request
 #define BLYNK_MAX_READBYTES 4096

 //Libs
 #include <PietteTech_DHT.h>
 #include <Adafruit_DHT.h>
 #include <blynk.h>

 // DHT parameters
 #define DHTPIN 5
 #define DHTTYPE DHT11


 // Sample every minute
 #define DHT_SAMPLE_INTERVAL 60000
 #define READ_INTERVAL 60000

 char auth[] = "Your Auth Token from Blynk goes here";//Id for sync with Blynk app on phone.
 BlynkTimer timer;

 void dht_wrapper(); // must be declared before the lib initialization

 // Lib instantiate
 PietteTech_DHT DHT(DHTPIN, DHTTYPE, dht_wrapper);

 // globals
 unsigned int DHTnextSampleTime;	    // Next time we want to start sample
 bool bDHTstarted;		    // flag to indicate we started acquisition
 int counter;

 //this is coming from http://www.instructables.com/id/Datalogging-with-Spark-Core-Google-Drive/?ALLSTEPS
 char resultstr[64]; //String to store the sensor data
 char VERSION[64] = "0.04";

 // This wrapper is in charge of calling
 // must be defined like this for the lib work
 void dht_wrapper() {
     DHT.isrCallback();
 }


 // Digital clock display of the current time and date in Oslo/This time zone
 void clockDisplay()
 {
      Time.zone(+2); //Oslo time
      String currentTime = String(Time.hour()) + ":" + Time.minute() + ":" + Time.second();
      Particle.publish("Current time: ", currentTime);
      // Send time to Blynk app (sirrobertysweatherstation)
      Blynk.virtualWrite(V1, currentTime);

      String currentDate = String(Time.year()) + ":" + Time.month() + ":" + Time.day();
      Particle.publish("Current date: ", currentDate);
      // Send date to Blynk app (sirrobertysweatherstation)
      Blynk.virtualWrite(V2, currentDate);
 }

 /**
  * This two functions print's sunrise & sumset time for Oslo from URI in WebHook.
  * All times are in UTC and summer time adjustments are not included in the returned data.
  */
 BLYNK_WRITE(V0)
 {
    String webhookdata = param.asStr();
    Particle.publish(webhookdata);
 }

 //This triggers the Get sunrise & sunset button in the app to get data from API
 BLYNK_WRITE(V3)
 {
   int V3btn = param.asInt();
   if(V3btn ==  1)
   {
    String webhookdata = param.asStr();
    Particle.publish(webhookdata);
     //prints content from webhook
    Blynk.virtualWrite(V0, "https://api.sunrise-sunset.org/json?lat=50.4495484&lng=30.5253873");
   }
 }



 void setup()
 {
    Serial.begin(9600);
    Blynk.begin(auth);

    //prints content from webhook
    Blynk.virtualWrite(V0, "https://api.sunrise-sunset.org/json?lat=50.4495484&lng=30.5253873");

    DHTnextSampleTime = 0;  // Start the first sample immediately
    Particle.variable("result", resultstr, STRING);
    Particle.publish("DHT11 - firmware version", VERSION, 60, PRIVATE);

    // Updates the clockDisplay function every 10 seconds
    timer.setInterval(10000L, clockDisplay);
 }


 void loop()
 {
    Blynk.run();
    timer.run();

    Serial.println(Time.timeStr());
     // Check if we need to start the next sample
    if (millis() > DHTnextSampleTime)
    {

 	if (!bDHTstarted) {	// start the sample
 	    DHT.acquire();
 	    bDHTstarted = true;
 	}

    // has sample completed?
    if (!DHT.acquiring())
         {
           float temp = (float)DHT.getCelsius();
           int temp1 = (temp - (int)temp) * 100;

           char tempInChar[32];
           sprintf(tempInChar,"%0d.%d", (int)temp, temp1);
           Particle.publish("The temperature from the dht11 is:", tempInChar, 60, PRIVATE);

           //virtual pin 7 in the Blynk app is temperature
           Blynk.virtualWrite(V7, tempInChar);

           //google docs can get this variable
           sprintf(resultstr, "{\"t\":%s}", tempInChar);

           float humid = (float)DHT.getHumidity();
           int humid1 = (humid - (int)humid) * 100;

           sprintf(tempInChar,"%0d.%d", (int)humid, humid1);
           Particle.publish("The humidity from the dht11 is:", tempInChar, 60, PRIVATE);

           //virtual pin 5 in the Blynk app is humidity
           Blynk.virtualWrite(V5, tempInChar);

           counter++;
           bDHTstarted = false;  // reset the sample flag so we can take another
           DHTnextSampleTime = millis() + DHT_SAMPLE_INTERVAL;  // set the time for next sample

             delay(10000);
         }

     }


 }
