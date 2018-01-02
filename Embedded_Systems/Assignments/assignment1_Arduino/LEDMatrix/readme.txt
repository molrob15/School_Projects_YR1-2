
	Dokumentation Oppgave1 LED Matris

På MAX7219 är SEG DP-SEG G är markerade med grön wire.
Och DIG 0-7 är markerade med orange wire.

De två resistorerna är på 10K vardera, här skall alltså 
20K användas totalt. De skall kopplas till pin 18 på MAX7219 enheten.
 <--------------------------------------------------------------------->

	Kopplingar till Arduino UNO: 

DIN (pin 1 MAX 7219) kopplas till digital pin 11, lila wire.

CLK (pin 13 MAX7219) kopplas till digital pin 10, cyan 
wire.

LOAD (pin 12 MAX7219) kopplas till digital pin 9, rosa wire.

V+ (5V) är markerat med röd wire, kopplas till pin 19 på MAX7219 enheten.

GND (pin 4, 9 MAX7219) är markerat med svart (black) wire.	
 <------------------------------------------------------------------------>


					< Funktionalitet >

Genom att använda MAX7219 så behöver man endast tillkoppla tre stycken ledningar till Arduinon, detta möjliggör att den kan driva fler än en display på en och samma gång. 
7219 använder sig utav multiplexing som innebär att den kombinerar analoga och digitala signaler från Arduinon. 
MAX7219 bidrar till man kan kontrollera 64 LEDs med exempelvis en 8x8 matris som 1588AS som användes i detta projektet. Och i koden definieras den output som skall visas på matrisen. 
DIG eller digit som det står för, kontrollerar raderna på matrisen och SEG eller segment kontrollerar kolonnerna. Matrisen är på 30 mA så det blir enligt data arket 17.1k så därav används två stycken 10k resistorer.   
  
 Robert Mattias Molin 

2017.02.14

