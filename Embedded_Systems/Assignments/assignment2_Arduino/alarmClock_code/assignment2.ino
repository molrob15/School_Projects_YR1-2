

/**
  Simple alarmclock
  @author Robert M Molin
  Inspired by: William Judi &&
  Project on http://educ8s.tv/arduino-real-time-clock/
*/

//libraries
#include <Adafruit_ST7735.h>
#include <Adafruit_GFX.h>
#include <RTClib.h>
#include <Wire.h>
#include <SPI.h>

//pins
#define TFT_CS     10
#define TFT_RST    8
#define TFT_DC     9
#define TFT_BCL    5
#define TFT_SCLK    13
#define TFT_MOSI    11
#define PIEZO_SPEAKER 3

#define ALARM_FREQUENCY 1000
#define ALARM_DURATION  500


//Objects
Adafruit_ST7735 tft = Adafruit_ST7735(TFT_CS,  TFT_DC, TFT_RST);
RTC_Millis rtc;

void setup(void) {
  currentTime();
  tft.initR(INITR_BLACKTAB);// initialize a ST7735S
}

void loop() {
  showTime();
  setAlarmTime(23, 35); //Manually set alarm ( first param: hour, second param: minute) to make it go off
  delay(1000);
}

//Gets the current time
void currentTime() {
  rtc.begin(DateTime(F(__DATE__), F(__TIME__)));
}

/**

  Gets time from the DateTime class and sends data to function printToScreen()
*/
void showTime() {
  DateTime timeNow = rtc.now();
  printToScreen(String(timeNow.hour()) + ":", String(timeNow.minute()) + ":", String(timeNow.second()), 20, 25, 2);
}

/**
   Function setAlarmTime
  @ param1 hour, param2 min
*/
void setAlarmTime(int h, int m) {
  DateTime t = rtc.now();
  if (t.hour() == h && t.minute() == m && t.second() < 10) {
    tft.fillScreen(ST7735_RED);
    alarmText("WAKE UP SLEEPYHEAD", ST7735_GREEN);//Message to display on active alarm
    soundAlarm();
  }
}

/**
   Function printToScreen
  @ param1 hour, param2 min, param3 sec, param4 x-pos on screen, param5 y-pos on screen, param6 text size
*/
void printToScreen(String h, String m, String s, int x, int y, int textSize) {
  tft.fillScreen(ST7735_BLACK);
  tft.setTextSize(textSize);
  tft.setTextWrap(true);
  printToAdafruitDisplay(h, m, s, ST7735_GREEN, 2);//Call to function printToAdafruitDisplay  parmeters hr min sec, color on text, size
}


/**
   Function printToAdafruitDisplay
  @ param1 hour, param2 min, param3 sec, param4 color on screen,  param5 text size
*/
void printToAdafruitDisplay(String h, String m, String s, uint16_t color, int textSize) {
  tft.setCursor(20, 25);
  tft.setTextColor(color);
  tft.setTextSize(textSize);
  tft.setTextWrap(true);
  tft.print(h);
  tft.print(m);
  tft.print(s);
}

/**
   Function alarmText
  @ param1 message to be printed on screen, param2 color
*/
void alarmText(String info, uint16_t color) {
  tft.setCursor(40, 100);
  tft.setTextColor(color);
  tft.setTextWrap(true);
  tft.print(info);
}

//Plays alarm on speaker
void soundAlarm() {
  noTone(PIEZO_SPEAKER);
  tone(PIEZO_SPEAKER, ALARM_FREQUENCY, ALARM_DURATION);
}






