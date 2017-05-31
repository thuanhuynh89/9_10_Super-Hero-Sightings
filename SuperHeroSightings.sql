DROP DATABASE IF EXISTS SuperHeroSightings;

CREATE DATABASE SuperHeroSightings;

USE SuperHeroSightings;

CREATE TABLE Hero (
	HeroId int not null auto_increment,
    Alias varchar(30) not null,
    firstname varchar(30) not null,
    lastname varchar(30) not null,
    description varchar(100) not null,
    Primary key(heroid)
)engine=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

create table powers (
	powersid int not null auto_increment,
    description varchar(100) not null,
    primary key(powersid)
)engine=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

create table heropower(
	heroid int not null,
	powersid int not null,
    primary key(heroid, powersid)
)engine=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE Organization (
	OrganizationId int not null auto_increment,
    OrganizationName varchar(30) not null,
    description varchar(100) not null,
    locationid int not null,
    primary key(organizationid)
)engine=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE address (
	addressid int not null auto_increment,
    StreetNumber varchar(30) not null,
    StreetName varchar(30) not null,
    City varchar(30) not null,
    State_Province varchar(30) not null,
    country varchar(30) not null,
    planet varchar(30) null,
    galaxy varchar(30) null,
    latitude varchar(30) null,
    longitude varchar(30) null,
    primary key(addressid)
)engine=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE location (
	locationid int not null auto_increment,
    locationname varchar(30) not null,
    description varchar(100) not null,
    addressid int null,
    primary key(locationid)
)engine=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

create table sighting (
	sightingid int not null auto_increment,
    sightingdate varchar(10) not null,
    description varchar(100) not null,
    heroid int not null,
    locationid int not null,
    primary key(sightingid)
)engine=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

create table member (
	memberid int not null auto_increment,
    StartDate varchar(10) null,
    EndDate varchar(10) null,
    heroid int not null,
    organizationid int not null,
    primary key(memberid)
)engine=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;


Alter TABLE Organization
ADD constraint fk_Organization_address
foreign key (locationid)
references location(locationid);

Alter TABLE location 
ADD constraint fk_location_address
foreign key(addressid)
references address(addressid);

Alter TABLE heropower
ADD constraint fk_heropower_powers
foreign key(powersid)
references powers(powersid);

Alter TABLE heropower
add constraint fk_heropower_hero
foreign key(heroid)
references Hero(heroid);  

alter table sighting
add constraint fk_sighting_hero
foreign key(heroid)
references Hero(heroid);

alter table sighting 
add constraint fk_sighting_location
foreign key(locationid)
references location(locationid);

alter table member
add constraint fk_member_hero
foreign key(heroid)
references Hero(heroid);

alter table member
add constraint fk_member_organization
foreign key(organizationid)
references Organization(organizationid);

InSERT INTO hero VALUES 
(1, "Batman", "Bruce", "Wayne", "American Billionaire and entreprenuer by day and hero by night"),
(2, "Captain America", "Steve", "Rogers", "100 year old never aging human military man from the USA"),
(3, "Spider Man", "Peter", "Parker", "teenage boy turned human spider"),
(4, "Wolverine", "Logan/James", "Howlett", "200 year American with full adamtium body"),
(5, "Cyclops", "Scott", "Summers", "American hero"),
(6, "Iceman", "Robert", "Drake", "American hero"),
(7, "Beast", "Henry", "McCoy", "smart American hero"),
(8, "Professor X", "Charles", "Xavier", "cares for other mutants and is a leader"),
(9, "Magneto",  "Max", "Eisenhardt", "hates non-mutants"),
(10, "Gambit", "Remy", "LeBeau", "great at throwing cards"),
(11, "Iron Man", "Anthony", "Stark", "Billionaire in tech, engineer and science proficiency");

InSERT INTO powers VALUES 
(1, "martial arts master"),
(2, "healing factor"),
(3, "spider web ejection"),
(4, "adamantium claws"),
(5, "laser beam eyes"),
(6, "ice creation"),
(7, "beast traits"),
(8, "telepathic abilities"),
(9, "magnetism"),
(10, "Kinectic conversion"),
(11, "high IQ"),
(12, "static generation"),
(13, "superhuman strength"),
(14, "skill-card thrower"),
(15, "invisible"),
(16, "laser hands"),
(17, "flight"),
(18, "leader");

InSERT INTO heropower VALUES 
(1,1),(1,18),(2,1),(2,2),(2,18),(3,1),(3,2),(3,3),(3,13),(4,2),(4,4),(4,7),(5,5),(5,18),(6,6),(7,2),(7,11),(7,13),(8,8),(8,10),(8,11),(8,17),(8,18),(9,9),(9,17),(9,18),
(10,1),(10,10),(10,12),(11,5),(11,11),(11,13),(11,16),(11,17),(11,18);

InSERT INTO address VALUES 
(1,"1212", "East 27th", "Erie", "PA", "USA", "Earth", "Milky Way", "1.000", "2.0001"),
(2,"1616", "East 30th", "Akron", "Ohio", "USA", "Earth", "Milky Way", "3.000", "3.0001"),
(3,"2020", "East 33th", "Philly", "Tennesee", "USA", "Earth", "Milky Way", "5.000", "5.0001");

InSERT INTO location VALUES 
(1,"Parkview Heights", "in the middle of nowhere",1),
(2,"Caveman Avenue", "located underground",2),
(3,"Hotland Blues", "located on the hills", 3),
(4,"Found Park", "building", 3);

InSERT INTO organization VALUES 
(1,"Justice League", "fights crime", 1),
(2,"X-Men", "school for gifted mutants; fights crime", 2),
(3,"Avengers", "strong heroes who fight crime", 3);

INSERT INTO member VALUES
(1,"1/01/2000", "present", 1, 1),
(2,"2/01/2000", "present", 2, 3),
(3,"3/01/2000", "present", 3, 3),
(4,"4/01/2000", "present", 4, 2),
(5,"5/01/2000", "present", 5, 2),
(6,"6/01/2000", "present", 6, 2),
(7,"7/01/2000", "present", 7, 2),
(8,"8/01/2000", "present", 8, 2),
(9,"9/01/2000", "present", 9, 2),
(10,"10/01/2000", "present", 10, 2),
(11,"11/01/2000", "present", 11, 3);

INSERT INTO sighting VALUES
(1,"4/15/2015", "on top of building", 1, 4),
(2,"4/15/2015", "on top of building", 2, 4),
(3,"4/15/2015", "on top of building", 3, 4),
(4,"4/15/2015", "on top of building", 4, 4),
(5,"4/15/2015", "on top of building", 5, 4),
(6,"4/15/2015", "on top of building", 6, 4),
(7,"4/15/2015", "on top of building", 7, 4),
(8,"4/15/2015", "on top of building", 8, 4),
(9,"4/15/2015", "on top of building", 9, 4),
(10,"4/15/2015", "on top of building", 10, 4),
(11,"4/15/2015", "on top of building", 11, 4);
