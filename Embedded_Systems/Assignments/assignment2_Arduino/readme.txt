Oppgave 2
Robert Mattias Molin
 
Nödvändigheter:

  Software:

RTCLib		https://github.com/adafruit/RTCLib
AdafruitGFX	 	https://github.com/adafruit/Adafruit-GFX-Library 
Adafruit ST7735	https://github.com/adafruit/Adafruit-ST7735-Library

Hardware:

Arduino UNO
ST7735 1.8” TFT LCD
DS1307 RTC module
Piezo speaker

Dokument:

Denna pdf, samt en readme.txt fil som backup.
Fritzing fil
Fritzing screen shot.png 
Video dokumentation – länk till Google Drive  


Fritzing:

Delarna som används i skissen är inte helt likt med hårdvaran som används i det fysiska projektet, det vill säga att rent visuellt sitter kablarna inte helt identiskt. Kopplingen ser ut som följande i sketchen:

TFT Skärm ST7735- Wires:		Klocka DS1307 Wires:		Piezo speaker Wires:
Röd – VCC 5V+			Röd – VCC 5V+			Röd – VCC 5V+
Svart – GND				Svart – GND				Svart - GND
Grön - !SCL				Lila - SCL
Blå - !SDA				Brun - DS
Lila – DC
Ochre – RES
Orange – CS
Rosa - *CS
Gul – MISO
VitA2 – X (3-Axis)
VitA1 – Y (3-Axis)
VitA0 – Z (3-Axis)
Cyan - Backlight





Design och implementation: 

Från början var min plan att göra en väckarklocka med en liten högtalare som skulle spela Super Mario’s main theme song vid alarm, men detta har jag tyvärr inte fått till. Istället blev alarmet bara en smått irriterande tone :). Alarmet ställs in manuellt i koden med hjälp av metoden setAlarmTime(). 

Det finns många saker som kunde förbättras, så som positionering av tiden på skärmen. Datum och dag tänkte jag också lägga till. 

Men klockan fungerar och alarmet ringer så det är ändå positivt. Det enda användaren behöver att göra är att ställa in tid för önskat alarm, så projektet är en simpel anordning. 



Kod:

Det mesta av koden har jag använt med är skriven av medstudent William Judi’s. Små modifikationer är gjorda på sina ställen i koden. 

En del av kod och inspiration är tagen ifrån följande projekt som kan hittas på följande sida:  http://educ8s.tv/arduino-real-time-clock/


Bugs:

Vid en längre utskrift i alarmText() blir textens positionering lite konstigt på TFT skärmen. 

