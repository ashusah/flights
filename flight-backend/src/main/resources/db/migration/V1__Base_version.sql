/*****************************/
/* Base Schema  */
/*****************************/

/*Table structure for table `Countries` */

CREATE TABLE Countries (
    id INT NOT NULL,
    code VARCHAR(10) NOT NULL,
    name VARCHAR(500) NOT NULL,
    continent VARCHAR(10) NOT NULL,
    wikipedia_link VARCHAR(80),
    keywords VARCHAR(500)
)
AS
SELECT * FROM CSVREAD('classpath:csv/countries.csv');

CREATE TABLE Airports (
                           id INT NOT NULL,
                           ident VARCHAR(10) NOT NULL,
                           type VARCHAR(40) NOT NULL,
                           name VARCHAR(500) NOT NULL,
                           latitude_deg FLOAT,
                           longitude_deg FLOAT,
                           elevation_ft INT,
                           continent VARCHAR(10),
                           isocountry VARCHAR(10),
                           iso_region VARCHAR(10),
                           municipality VARCHAR(200),
                           scheduled_service VARCHAR(40),
                           gps_code VARCHAR(40),
                           iata_code VARCHAR(10),
                           local_code VARCHAR(10),
                           home_link VARCHAR(200),
                           wikipedia_link VARCHAR(200),
                           keywords VARCHAR(500)
)
AS
SELECT * FROM CSVREAD('classpath:csv/airports.csv');

CREATE TABLE Runways (
                          id INT NOT NULL,
                          airportref INT NOT NULL,
                          airport_ident VARCHAR(10) NOT NULL,
                          length_ft INT,
                          width_ft INT,
                          surface VARCHAR(200),
                          lighted INT,
                          closed INT,
                          le_ident VARCHAR(25),
                          le_latitude_deg FLOAT,
                          le_longitude_deg FLOAT,
                          le_elevation_ft INT,
                          le_heading_degT FLOAT,
                          le_displaced_threshold_ft INT,
                          he_ident VARCHAR(10),
                          he_latitude_deg FLOAT,
                          he_longitude_deg FLOAT,
                          he_elevation_ft INT,
                          he_heading_degT FLOAT,
                          he_displaced_threshold_ft INT
)
AS
SELECT * FROM CSVREAD('classpath:csv/runways.csv');
