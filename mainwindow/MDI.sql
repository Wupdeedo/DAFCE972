CREATE TABLE Landsinfo(
	LandsID smallint NOT NULL,
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
	LandsID smallint  NOT NULL,
	VaccinationsID int NOT NULL,
	PRIMARY KEY (LandsID,VaccinationsID),
	FOREIGN KEY (LandsID) REFERENCES Landsinfo(LandsID),
	FOREIGN KEY (VaccinationsID) REFERENCES Vaccination(VaccinationsID)
);

CREATE TABLE Ort(
	OrtID int NOT NULL,
	Ort varchar(30) NOT NULL,
	LandsID smallint  NOT NULL,
	PRIMARY KEY (OrtID),
	FOREIGN KEY (LandsID) REFERENCES Landsinfo(LandsID)
);

CREATE TABLE Flygplanstyp(
	FlygplanstypsID int  NOT NULL,
	Platser smallint ,
	Rokfri boolean,
	PRIMARY KEY (FlygplanstypsID)
);

CREATE TABLE Flygplan(
	FlygplansID int  NOT NULL,
	FlygplanstypsID int  NOT NULL,
	PRIMARY KEY (FlygplansID),
	FOREIGN KEY (FlygplanstypsID) REFERENCES Flygplanstyp(FlygplanstypsID)
	
);

	
CREATE TABLE Flight(
	FlightID int  NOT NULL,
	Avgangstid time,
	Ankomsttid time,
	Avgangsdatum date,
	Ankomstdatum date,
	Avgangsort int NOT NULL,
	Ankomstort int NOT NULL,
	FlygplansID int NOT NULL,
	PRIMARY KEY (FlightID),
	FOREIGN KEY (Avgangsort) REFERENCES Ort(OrtID),
	FOREIGN KEY (Ankomstort) REFERENCES Ort(OrtID),
	FOREIGN KEY (FlygplansID) REFERENCES Flygplan(FlygplansID)
);

CREATE TYPE klass AS ENUM ('B', 'F', 'E');

CREATE TABLE Stol(
	Nummer smallint  NOT NULL,
	FlygplansID int  NOT NULL,
	Typ klass,
	Kostnad int ,
	PRIMARY KEY (Nummer, FLygplansID),
	FOREIGN KEY (FlygplansID) REFERENCES Flygplan(FlygplansID)
);

CREATE TABLE Platser(
	PlatsID int NOT NULL ,
	PlatsNamn varchar(25),
	OrtID int NOT NULL,
	PRIMARY KEY (PlatsID),
	FOREIGN KEY (OrtID) REFERENCES Ort(OrtID)
);
	
CREATE TABLE Evenemang(
	EvenemangsID int  NOT NULL,
	Namn varchar(25),
	Startdatum date,
	Slutdatum date,
	Starttid time,
	Sluttid time,
	Beskrivning text,
	AntalPlatser smallint ,
	PlatsID int NOT NULL,
	Kostnad int ,
	PRIMARY KEY (EvenemangsID),
	FOREIGN KEY (PlatsID) REFERENCES Platser(PlatsID)
);


	

CREATE TABLE Depot(
	DepotID int NOT NULL ,
	OrtID int NOT NULL,
	Adress varchar(60),
	PRIMARY KEY (DepotID),
	FOREIGN KEY (OrtID) REFERENCES Ort(OrtID)
);

CREATE TYPE drift AS ENUM('4','2');
CREATE TYPE vaxel AS ENUM('A','M');

CREATE TABLE Bilmodell(
	ModellID int NOT NULL ,
	Drift drift,
	Vaxel vaxel,
	Tillverkare varchar(25),
	Modell varchar(25),
	PRIMARY KEY (ModellID)
);

CREATE TABLE Bil(
	BilID int NOT NULL ,
	ModellID int NOT NULL ,
	Kostnad int ,
	PRIMARY KEY(BilID),
	FOREIGN KEY (ModellID) REFERENCES Bilmodell(ModellID)
);





CREATE TABLE Hotell(
	HotellID int NOT NULL ,
	Namn varchar(25),
	Adress varchar(60),
	OrtID int  NOT NULL,
	Stjarnor smallint,
	Beskrivning text,
	PRIMARY KEY (HotellID),
	FOREIGN KEY (OrtID) REFERENCES Ort(OrtID)
);

CREATE TABLE Rum(
	RumsID int  NOT NULL,
	HotellID int NOT NULL ,
	Rumsnummer smallint ,
	Rokfri boolean,
	Kostnad int ,
	PRIMARY KEY(RumsID),
	FOREIGN KEY (HotellID) REFERENCES Hotell(HotellID)
);








CREATE TABLE Konto(
	KontoID SERIAL NOT NULL,
	Login Varchar(40) UNIQUE,
	Password bytea,
	Fornamn Varchar(16),
	Efternamn Varchar(32),
	Postort Varchar(32),
	Postnr int,
	email Varchar(40), 
	Adress Varchar(60),
	Telefon bigint,
	TaBort Date,
	PRIMARY KEY (KontoID)
);


CREATE TABLE Bokning(
	BokningsID SERIAL NOT NULL UNIQUE,
	KontoID bigint  NOT NULL UNIQUE,
	PRIMARY KEY (BokningsID, KontoID),
	FOREIGN KEY (KontoID) REFERENCES Konto(KontoID)
);



CREATE TABLE Rumsschema(
	BokningsID bigint  NOT NULL UNIQUE,
	RumsID int  NOT NULL,
	Frandatum date NOT NULL,
	Tilldatum date NOT NULL,
	PRIMARY KEY (RumsID,Frandatum,Tilldatum),
	FOREIGN KEY (BokningsID) REFERENCES Bokning(BokningsID),
	FOREIGN KEY (RumsID) REFERENCES Rum(RumsID)
);

CREATE TABLE Evenemangsschema(
	EvenemangsID int  NOT NULL,
	BokningsID bigint  NOT NULL,
	Antal smallint ,
	PRIMARY KEY (EvenemangsID, BokningsID),
	FOREIGN KEY (BokningsID) REFERENCES Bokning(BokningsID),
	FOREIGN KEY (EvenemangsID) REFERENCES Evenemang(EvenemangsID)
);



CREATE TABLE Bilschema(
	BilID int  NOT NULL,
	BokningsID bigint  NOT NULL,
	Hamtdatum date NOT NULL,
	Lamningsdatum date NOT NULL,
	Hamtplats int  NOT NULL,
	Lamningsplats int  NOT NULL,
	PRIMARY KEY (BilID, Hamtdatum, Lamningsdatum),
	FOREIGN KEY (BokningsID) REFERENCES Bokning(BokningsID),
	FOREIGN KEY (BilID) REFERENCES Bil(BilID),
	FOREIGN KEY (Hamtplats) REFERENCES Depot(DepotID),
	FOREIGN KEY (Lamningsplats) REFERENCES Depot(DepotID)
);

CREATE TABLE Flightbokning(
	FlightID int  NOT NULL,
	BokningsID bigint  NOT NULL,
	Nummer smallint  NOT NULL,
	PRIMARY KEY (FlightID, BokningsID, Nummer),
	FOREIGN KEY (BokningsID) REFERENCES Bokning(BokningsID),
	FOREIGN KEY (FlightID) REFERENCES Flight(FlightID)
);












