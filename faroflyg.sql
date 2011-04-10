CREATE TABLE Landsinfo(
	LandsID tinyint UNSIGNED NOT NULL,
	Land varchar(25),
	Visum text,
	Information text,
	PRIMARY KEY(LandsID)
);

CREATE TABLE Vaccination(
	VaccinationsID int NOT NULL,
	VaccBeskrivning text,
	PRIMARY KEY (VaccinationsID)
);


CREATE TABLE VacciLand(
	LandsID tinyint UNSIGNED NOT NULL,
	VaccinationsID int NOT NULL,
	PRIMARY KEY (LandsID,VaccinationsID),
	FOREIGN KEY (LandsID) REFERENCES Landsinfo(LandsID),
	FOREIGN KEY (VaccinationsID) REFERENCES Vaccination(VaccinationsID)
);

CREATE TABLE Ort(
	OrtID int NOT NULL,
	Ort varchar(30) NOT NULL,
	LandsID tinyint UNSIGNED NOT NULL,
	PRIMARY KEY (OrtID),
	FOREIGN KEY (LandsID) REFERENCES Landsinfo(LandsID)
);

CREATE TABLE Flygplanstyp(
	FlygplanstypsID int UNSIGNED NOT NULL,
	Platser smallint UNSIGNED,
	Rokfri boolean,
	PRIMARY KEY (FlygplansID)
);

CREATE TABLE Flygplan(
	FlygplansID int UNSIGNED NOT NULL,
	FlygplanstypsID int UNSIGNED NOT NULL,
	PRIMARY KEY (FlygplansID),
	FOREIGN KEY (FlygplanstypsID) REFERENCES Flygplanstyp(FlygplanstypsID)
	


	
CREATE TABLE Flight(
	FlightID int UNSIGNED NOT NULL,
	Avgangstid time,
	Ankomsttid time,
	Avgangsdatum date,
	Ankomstdatum date,
	Avgangsort int NOT NULL,
	Ankomstort int NOT NULL,
	FlygplansID int NOT NULL,
	Stolskostnad int UNSIGNED,
	PRIMARY KEY (FlightID),
	FOREIGN KEY (Avgangsort) REFERENCES Ort(OrtID),
	FOREIGN KEY (Ankomstort) REFERENCES Ort(OrtID),
	FOREIGN KEY (FlygplansID) REFERENCES Flygplan(FlygplansID)
);



CREATE TABLE Stol(
	Nummer smallint UNSIGNED NOT NULL,
	FlygplansID int UNSIGNED NOT NULL,
	Typ ENUM('B', 'E', 'F'),
	PRIMARY KEY (Nummer, FLygplansID),
	FOREIGN KEY (FlygplansID) REFERENCES Flygplan(FlygplansID)
);

CREATE TABLE Platser(
	PlatsID int NOT NULL UNSIGNED,
	PlatsNamn varchar(25),
	OrtID int NOT NULL,
	PRIMARY KEY (PlatsID),
	FOREIGN KEY (OrtID) REFERENCES Ort(OrtID)
);
	
CREATE TABLE Evenemang(
	EvenemangsID int UNSIGNED NOT NULL,
	Namn varchar(25),
	Startdatum date,
	Slutdatum date,
	Starttid time,
	Sluttid time,
	Beskrivning text,
	AntalPlatser smallint UNSIGNED,
	PlatsID int NOT NULL,
	PRIMARY KEY (EvenemangsID),
	FOREIGN KEY (PlatsID) REFERENCES Platser(PlatsID)
);


	

CREATE TABLE Depot(
	DepotID int NOT NULL UNSIGNED,
	OrtID int NOT NULL,
	Adress varchar(60),
	PRIMARY KEY (DepotID),
	FOREIGN KEY (OrtID) REFERENCES Ort(OrtID)
);

CREATE TABLE Bilmodell(
	ModellID int NOT NULL UNSIGNED,
	Drift ENUM(4,2),
	Vaxel ENUM ('A','M'),
	Tillverkare varchar(25),
	Modell varchar(25),
	PRIMARY KEY (ModellID)
);

CREATE TABLE Bil(
	BilID int NOT NULL UNSIGNED,
	ModellID int NOT NULL UNSIGNED,
	PRIMARY KEY(BilID),
	FOREIGN KEY (ModellID) REFERENCES Bilmodell(ModellID),
);





CREATE TABLE Hotell(
	HotellID int NOT NULL UNSIGNED,
	Namn varchar(25),
	Adress varchar(60),
	OrtID int UNSIGNED NOT NULL,
	Stjarnor tinyint(1),
	Beskrivning text,
	PRIMARY KEY (HotellID),
	FOREIGN KEY (OrtID) REFERENCES Ort(OrtID)
);

CREATE TABLE Rum(
	RumsID int UNSIGNED NOT NULL,
	HotellID int NOT NULL UNSIGNED,
	Rumsnummer smallint UNSIGNED,
	Rokfri boolean,
	PRIMARY KEY(RumsID),
	FOREIGN KEY (HotellID) REFERENCES Hotell(HotellID)
);