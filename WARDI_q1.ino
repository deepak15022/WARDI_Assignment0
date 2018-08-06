/*
  REFRENCES:
 for circuit diag :  https://programmingelectronics.com/tutorial-20-analog-io-and-serial-communications-old-version/
 for code :  https://www.arduino.cc/en/tutorial/potentiometer
*/

int ledPin = 11;  
int potentiometer = 0;  
int potentiometerReading = 0;        

void setup() {
  pinMode(ledPin, OUTPUT);  
}

void loop() {
  potentiometerReading = analogRead(potentiometer); 
  
  if ( potentiometerReading > 512 ) 
    digitalWrite(ledPin, HIGH); 
  else
    digitalWrite(ledPin, LOW); 
}
