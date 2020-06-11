# Experience Calculator

## Inspiration
This comes right out of my gaming habit. I play a mud called The Inquisition: Legacy, and it has an experience-gain system that is non-linear.
The experience one gains per level changes after a certain number of levels have passed, and the resulting name of your rank on the skill. 
So if you are Level 48 in a skill, for example, your rank would be 'Adept'. I made a calculator to tell me how much xp I'd need to gain the allowed
number of levels in the game, and record that calculation for reporting purposes.

## Technologies used
* Springboot with H2 as the in memory database, itext for pdf generation and owm for easier weather-retrieval.
* Angular9 with a mix of material and bootstrap design


## How to use
* Clone the repo. The springboot portion is located in the *tonextlevel* folder, angular is in the *experience-calculator* folder.

## Exposed Endpoints
* /getExperienceNeeded
  * Type : POST
  * Accepts: { fromLevel: long, toLevel: long}, JSON
  * Task: Calculates the amount of xp you need to gain a certain amount of level, records result rank into history table.
* /getHistoricalRecords
  * Type: GET
  * Task: Gets all records saved in the history table.
* /generateReport
  * Type: POST
  * Accepts: { rank: string}, JSON
  * Task: Generates a pdf report for the specified rank.
* /getWeatherReport
  * Type: GET
  * Task: Gets the current temperature in Durban, ZA