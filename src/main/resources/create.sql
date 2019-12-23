drop table if exists Bomber cascade;
drop table if exists Fither cascade;
drop table if exists FireAircraft cascade;
drop table if exists Agricultural cascade;
drop table if exists Freight cascade;
drop table if exists Passenger cascade;
drop table if exists Pilot cascade;
drop table if exists Fly cascade;

create table Pilot (
    pilotId int primary key,
    firstName text,
    lastName text,
    typePilot text,
    admission boolean
);

create table Fly (
    flyId int primary key,
    airId int,
    airType text,
    pilotId int,
    time int
);

create table Bomber (
    id int primary key,
    model text,
    producer text,
    maxDistance int,
    country text,
    numBombs int,
    typeBombs text
);

create table Fither (
    id int primary key,
    model text,
    producer text,
    maxDistance int,
    country text,
    numRocket int,
    typeRocket text,
    generation int
);

create table FireAircraft (
    id int primary key,
    model text,
    producer text,
    maxDistance int,
    mission text,
    displacement int,
    sprayWight int
);

create table Agricultural (
    id int primary key,
    model text,
    producer text,
    maxDistance int,
    mission text,
    displacement int,
    sprayWight int
);

create table Freight (
    id int primary key,
    model text,
    producer text,
    maxDistance int,
    typeFlight text,
    maxWeight int
);

create table Passenger (
    id int primary key,
    model text,
    producer text,
    maxDistance int,
    typeFlight text,
    numPassenger int,
    service text
);
